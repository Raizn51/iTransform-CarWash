package com.carwash.washer_service.dto;

import com.carwash.washer_service.model.Washer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Washer")
public class WasherDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, message = "Username must be at least 4 characters long")
    @Schema(description = "Washer's unique username", example = "john_wash", required = true)
    private String userName;

    @NotBlank(message = "Full name is required")
    @Schema(description = "Full name of the washer", example = "John Doe", required = true)
    private String fullName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Schema(description = "Password for washer account", example = "securePass123", required = true)
    private String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Schema(description = "Email address of the washer", example = "john@example.com", required = true)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Schema(description = "Phone number of the washer", example = "9876543210")
    private String phoneNumber;

    @Schema(description = "URL to the washer's profile image", example = "https://example.com/image.jpg")
    private String profileImageUrl;

    public WasherDTO(Washer washer)
    {
        this.userName=washer.getUserName();
        this.email=washer.getEmail();
        this.fullName=washer.getFullName();
        this.phoneNumber=washer.getPhoneNumber();
        this.profileImageUrl=washer.getProfileImageUrl();
    }
}
