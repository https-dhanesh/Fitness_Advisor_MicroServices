package com.FitnessService.AIService.service;

import com.FitnessService.AIService.models.Recommendation;
import com.FitnessService.AIService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;


    public List<Recommendation> getUserRecommendations(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElse(null);
    }
}
