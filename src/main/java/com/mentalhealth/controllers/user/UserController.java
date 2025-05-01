package com.mentalhealth.controllers.user;

import com.mentalhealth.constants.Constants;
import com.mentalhealth.dto.response.ResponseDTO;
import com.mentalhealth.dto.user.UserCreateDTO;
import com.mentalhealth.dto.user.UserDTO;
import com.mentalhealth.enums.Role;
import com.mentalhealth.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class UserController {


    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<?>> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        userService.registerUser(userCreateDTO);
        UserDTO userDTO = new UserDTO(userCreateDTO.getFirstName(),
                userCreateDTO.getLastName(),
                userCreateDTO.getEmail(),
                Role.USER);

        ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder()
                .statusCode(Constants.SUCCESS_CODE)
                .statusMessage(Constants.SUCCESS_MESSAGE)
                .data(userDTO)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<ResponseDTO<?>> fetchAllUsers() {
        List<UserDTO> userDTOList = userService.fetchAllUsers();
        ResponseDTO<List<UserDTO>> response = ResponseDTO.<List<UserDTO>>builder()
                .statusCode(Constants.SUCCESS_CODE)
                .statusMessage(Constants.SUCCESS_MESSAGE)
                .data(userDTOList)
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


}
