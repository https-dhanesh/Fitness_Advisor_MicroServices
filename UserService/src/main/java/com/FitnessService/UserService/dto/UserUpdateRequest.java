package com.FitnessService.UserService.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String fitnessGoal;
    private Double weight;
    private Double height;
}
