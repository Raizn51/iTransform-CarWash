package com.carwash.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotBlank
    private String userName;

    @NotBlank
    private String fullName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    private String phoneNumber;

    private String profileImageUrl;

    
}