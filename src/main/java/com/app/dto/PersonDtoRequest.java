package com.app.dto;

import org.hibernate.validator.constraints.Length;

public record PersonDtoRequest(
        @Length (min = 4) String name, String surname, String email, String pesel) {

}
