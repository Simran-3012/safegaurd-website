package com.mentalhealth.dto.doctor;

import com.mentalhealth.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 40, message = "Last name must be between 3 and 40 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Licence type is required")
    @Size(min = 3, max = 50, message = "Licence type must be between 3 and 50 characters")
    private String licenceType;

    @NotBlank(message = "Licence number is required")
    @Size(min = 3, max = 50, message = "Licence number must be between 3 and 50 characters")
    private String licenceNumber;

    @NotBlank(message = "State is required")
    @Size(min = 3, max = 30, message = "State must be between 3 and 30 characters")
    private String state;

    private Role role;

}
