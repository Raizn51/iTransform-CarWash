package com.carwash.washer_service.model;

import com.carwash.washer_service.dto.WasherDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "washers")
@Schema(description = "Washer entity representing washer data stored in the database")
public class Washer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for washer", example = "1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Unique username of the washer", example = "john_wash")
    private String userName;

    @Column(nullable = false)
    @Schema(description = "Full name of the washer", example = "John Doe")
    private String fullName;

    @Column(nullable = false)
    @Schema(description = "Password for the washer account (should be encrypted)", example = "hashedPassword123")
    private String password;

    @Column(unique = true, nullable = false)
    @Schema(description = "Email address of the washer", example = "john@example.com")
    private String email;

    @Schema(description = "Phone number of the washer", example = "9876543210")
    private String phoneNumber;

    @Schema(description = "Profile image URL", example = "https://example.com/profile.jpg")
    private String profileImageUrl;

    @Schema(description = "Status of the washer account", example = "true")
    private boolean isActive = true;

    @ElementCollection
    @Schema(description = "List of assigned order IDs", example = "[101, 102, 103]")
    private List<Long> orderIds = new ArrayList<>();

    @Schema(description = "Average customer rating", example = "4.5")
    private Double averageRating = 0.0;

    @Schema(description = "Total number of ratings received", example = "20")
    private Integer totalRatings = 0;

    @Schema(description = "Creation timestamp", example = "2024-01-10T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "Last updated timestamp", example = "2024-02-20T15:45:00")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Washer(WasherDTO washerDTO) {
        this.userName = washerDTO.getUserName();
        this.fullName = washerDTO.getFullName();
        this.password = washerDTO.getPassword();
        this.email = washerDTO.getEmail();
        this.phoneNumber = washerDTO.getPhoneNumber();
        this.profileImageUrl = washerDTO.getProfileImageUrl();
    }
}
