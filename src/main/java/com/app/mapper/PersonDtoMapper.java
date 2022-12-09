package com.app.mapper;

import com.app.dto.PersonDtoResponse;
import com.app.model.person.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDtoMapper {
    public static  List<PersonDtoResponse> mapPersonToPersonDto(List<Person> list) {
        return   list.stream()
                .map(person -> new PersonDtoResponse(person.getId(), person.getName(), person.getSurname()))
                .collect(Collectors.toList());

    }
}
