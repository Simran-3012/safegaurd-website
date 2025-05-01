package com.mentalhealth.services.user;

import com.mentalhealth.dto.user.UserCreateDTO;
import com.mentalhealth.dto.user.UserDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IUserService {


    void registerUser(@Valid UserCreateDTO userCreateDTO);

    List<UserDTO> fetchAllUsers();
}
