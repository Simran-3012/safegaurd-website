package com.mentalhealth.controllers;



import com.mentalhealth.constants.Constants;
import com.mentalhealth.dto.login.LoginDTO;
import com.mentalhealth.dto.login.LoginResponse;
import com.mentalhealth.dto.response.ResponseDTO;
import com.mentalhealth.security.CustomUserDetails;
import com.mentalhealth.security.CustomUserDetailsService;
import com.mentalhealth.security.jwt.JwtBlacklistService;
import com.mentalhealth.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtBlacklistService jwtBlacklistService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, JwtBlacklistService jwtBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponse>> createAuthenticationToken(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
        final CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(loginDTO.getEmail());
        final String jwt = jwtUtil.generateToken(customUserDetails);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwt);
        loginResponse.setRole(customUserDetails.getRole());
        loginResponse.setMessage("Login successful");

        ResponseDTO<LoginResponse> response = ResponseDTO.<LoginResponse>builder()
                .statusCode(Constants.SUCCESS_CODE)
                .statusMessage(Constants.SUCCESS_MESSAGE)
                .data(loginResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO<String>> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            jwtBlacklistService.blacklistToken(jwt);
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                    .statusCode(Constants.SUCCESS_CODE)
                    .statusMessage(Constants.SUCCESS_MESSAGE)
                    .data("Logout successful")
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(Constants.BAD_REQUEST_CODE, Constants.BAD_REQUEST_MESSAGE, "Invalid token"));
    }
}
