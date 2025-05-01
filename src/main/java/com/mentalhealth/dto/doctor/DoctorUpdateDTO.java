package com.mentalhealth.dto.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DoctorUpdateDTO {

    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String firstName;

    @Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
    private String lastName;

    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", message = "Password must contain at least one letter, one number, and one special character")
    private String password;

    @Size(min = 3, max = 50, message = "Licence type must be between 3 and 50 characters")
    private String licenceType;

    @Size(min = 3, max = 50, message = "Licence number must be between 3 and 50 characters")
    private String licenceNumber;

    @Size(min = 3, max = 30, message = "State must be between 3 and 30 characters")
    private String state;

}
