package com.mentalhealth.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginDTO {

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", message = "Password must contain at least one letter, one number, and one special character")
    private String password;


    public void setEmail(@Email(message = "Invalid email address") @NotBlank(message = "Email is required") String email) {
        this.email = (email != null) ? email.trim() : null;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", message = "Password must contain at least one letter, one number, and one special character") String password) {
        this.password = (password != null) ? password.trim() : null;
    }

}
