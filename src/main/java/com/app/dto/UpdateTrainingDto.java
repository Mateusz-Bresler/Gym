package com.app.dto;

import com.app.model.training.TrainingTypes;

import java.time.LocalDateTime;

public record UpdateTrainingDto(TrainingTypes type , LocalDateTime time , int maxPeople) {
}
