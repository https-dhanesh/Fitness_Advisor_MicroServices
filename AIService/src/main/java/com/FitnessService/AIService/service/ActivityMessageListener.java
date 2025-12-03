package com.FitnessService.AIService.service;

import com.FitnessService.AIService.models.Activity;
import com.FitnessService.AIService.models.Recommendation;
import com.FitnessService.AIService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService activityAIService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics= "${kafka.topic.name}" , groupId = "activity-processor-group")
    public void processActivity(Activity activity){
        log.info("Received Activity for processing: {}",activity.getUserId());
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}
