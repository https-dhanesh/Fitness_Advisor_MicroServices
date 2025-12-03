package com.FitnessService.ActivityService.controllers;

import com.FitnessService.ActivityService.dto.ActivityRequest;
import com.FitnessService.ActivityService.dto.ActivityResponse;
import com.FitnessService.ActivityService.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request , @RequestHeader("X-User-ID") String userId){
        request.setUserId(userId);
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivites(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivites(userId));
    }
}
