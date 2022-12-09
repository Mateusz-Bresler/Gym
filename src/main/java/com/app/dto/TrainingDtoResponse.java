package com.app.dto;

import com.app.model.training.TrainingTypes;

import java.time.LocalDateTime;

public record TrainingDtoResponse(Long id, TrainingTypes type , LocalDateTime time) {
}
