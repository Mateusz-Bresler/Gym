package com.app.dto;

import lombok.Builder;


@Builder

public record PersonDtoNameAndSurname(String name, String surname) {
}
