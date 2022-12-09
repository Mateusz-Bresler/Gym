package com.app.dto;

import lombok.Builder;

@Builder
public record PersonDtoResponse(Long id, String name, String surname) {
}
