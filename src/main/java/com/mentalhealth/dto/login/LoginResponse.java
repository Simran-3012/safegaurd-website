package com.mentalhealth.dto.login;

import com.mentalhealth.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private Role role;
    private String message;

}
