package com.FitnessService.UserService.services;

import com.FitnessService.UserService.UserRepository;
import com.FitnessService.UserService.dto.RegisterRequest;
import com.FitnessService.UserService.dto.UserResponse;
import com.FitnessService.UserService.dto.UserUpdateRequest;
import com.FitnessService.UserService.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    
    public UserResponse register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            User existingUser = repository.findByEmail(request.getEmail());
            return UserResponse.from(existingUser);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setKeycloakId(request.getKeycloakId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);
        return UserResponse.from(savedUser);
    }

    public UserResponse getUserProfile(String userId){
        User user = repository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return UserResponse.from(user);
    }

    public Boolean existByUserId(String userId) {
        log.info("Calling user service for {}",userId);
        return repository.existsByKeycloakId(userId);
    }

    public UserResponse getCurrentUserProfile(String keycloakUserId) {
        User user = repository.findByKeycloakId(keycloakUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponse.from(user);
    }

    public UserResponse updateCurrentUserProfile(String keycloakUserId, UserUpdateRequest request) {
        User user = repository.findByKeycloakId(keycloakUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getFitnessGoal() != null) user.setFitnessGoal(request.getFitnessGoal());
        if (request.getWeight() != null) user.setWeight(request.getWeight());
        if (request.getHeight() != null) user.setHeight(request.getHeight());

        User saved = repository.save(user);
        return UserResponse.from(saved);
    }
}