package com.app.repository;

import com.app.model.training.Training;
import com.app.model.training.TrainingTypes;
import com.app.model.person.Person;
import com.app.service.TrainingService;
import com.app.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DbInit  {

   private final PersonService personService;
   private final TrainingService trainingService;


    @PostConstruct
    public void initDateBase() {
        Training training = Training.builder()
                .type(TrainingTypes.BOX)
                .time(LocalDateTime.of(2022,6,12,13,45))
                .maxPeople(2)
                .peopleList(new ArrayList<>())
                .build();
        Training training2 = Training.builder()
                .type(TrainingTypes.CROSSFIT)
                .time(LocalDateTime.now())
                .peopleList(new ArrayList<>())
                .maxPeople(10)
                .build();

        Person person1 = Person.builder()
                .name("Mateusz")
                .surname("Bresler")
                .email("mbresler@wp.pl")
                .pesel("97062304030")
                .trainingList(new ArrayList<>())
                .build();

        Person person2 = Person.builder()
                .name("Jan")
                .surname("Nowak")
                .email("jnowak@wp.pl")
                .pesel("97062304123")
                .trainingList(new ArrayList<>())
                .build();

        trainingService.addPerson(training,person1);
        trainingService.addPerson(training,person2);
        trainingService.addPerson(training2,person1);
       personService.save(person1);
       personService.save(person2);
        trainingService.save(training);
        trainingService.save(training2);


    }


}
