package com.app.mapper;

import com.app.dto.TrainingDtoResponse;
import com.app.model.training.Training;

import java.util.List;
import java.util.stream.Collectors;

public class TrainingDtoMapper {
    public static List<TrainingDtoResponse> mapTrainingToTrainingDto(List<Training> list) {
        return list.stream()
                .map(training -> new TrainingDtoResponse(training.getId(), training.getType(), training.getTime()))
                .collect(Collectors.toList());

    }
}
