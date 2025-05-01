package com.mentalhealth.services.user;

import com.mentalhealth.config.SecurityConfig;
import com.mentalhealth.dto.user.UserCreateDTO;
import com.mentalhealth.dto.user.UserDTO;
import com.mentalhealth.enums.Role;
import com.mentalhealth.exceptions.ResourceNotFoundException;
import com.mentalhealth.models.Users;
import com.mentalhealth.repositories.user.UserRepository;
import com.mentalhealth.services.validations.EmailValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final EmailValidation emailValidation;


    @Override
    public void registerUser(UserCreateDTO userCreateDTO) {
        emailValidation.checkIfEmailExists(userCreateDTO.getEmail());
        Users users = Users.builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .email(userCreateDTO.getEmail())
                .password(securityConfig.passwordEncoder().encode(userCreateDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(users);
    }

    @Override
    public List<UserDTO> fetchAllUsers() {
        List<Users> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Users not found.");
        }
        return users.stream().map(user -> new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole())).toList();
    }
}
