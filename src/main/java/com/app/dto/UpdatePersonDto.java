package com.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Builder

public record UpdatePersonDto(String surname, String email) {

}
