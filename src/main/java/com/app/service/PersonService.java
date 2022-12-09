package com.app.service;

import com.app.model.person.Person;
import com.app.repository.PersonRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(Person person) {
        personRepository.save(person);
        return person;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person findPersonByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }


}
