package com.app.service;

import com.app.exceptions.MaxPeopleInGroupException;
import com.app.model.training.Training;
import com.app.model.person.Person;
import com.app.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;


    public Training addPerson(Training training, Person person) {
        if (training.getPeopleList().size() >= training.getMaxPeople()) {
            throw new MaxPeopleInGroupException();
        }
        training.getPeopleList().add(person);
        person.getTrainingList().add(training);
        return training;
    }

    public Training save(Training training) {
        trainingRepository.save(training);
        return training;
    }

    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    public void deleteById(Long id) {
        trainingRepository.deleteById(id);
    }

    public Training findById(Long id) {
        return trainingRepository.findById(id).orElse(new Training());
    }

    public void deletePerson(Training training, Person person){
        training.getPeopleList().remove(person);
        save(training);
    }


}
