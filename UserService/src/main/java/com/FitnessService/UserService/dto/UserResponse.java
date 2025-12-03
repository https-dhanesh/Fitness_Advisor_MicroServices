package com.FitnessService.UserService.dto;

import lombok.Data;
import com.FitnessService.UserService.models.User;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;
    private String keycloakId;
    private String email;
    // private String password;
    private String firstName;
    private String lastName;

    private String fitnessGoal;
    private Double weight;
    private Double height;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponse from(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setKeycloakId(user.getKeycloakId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setFitnessGoal(user.getFitnessGoal());
        response.setWeight(user.getWeight());
        response.setHeight(user.getHeight());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
