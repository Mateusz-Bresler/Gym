package com.app.controller;

import com.app.dto.PersonDtoRequest;
import com.app.dto.TrainingDtoResponse;
import com.app.dto.UpdatePersonDto;
import com.app.model.person.Person;
import com.app.model.training.Training;
import com.app.service.PersonService;
import com.app.service.TrainingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.app.mapper.TrainingDtoMapper.mapTrainingToTrainingDto;

@RequiredArgsConstructor
@Getter

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final TrainingService trainingService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("people", personService.getAll());
        return "person/listPerson";
    }

    @GetMapping("/list/training/{id}")
    public String trainingList(@PathVariable Long id, Model model) {
        Person person = personService.findById(id);
        List<TrainingDtoResponse> trainingListDto = mapTrainingToTrainingDto(person.getTrainingList());
        model.addAttribute("person", person);
        model.addAttribute("trainings", trainingListDto);
        return "person/listTraining";
    }


    @GetMapping("create/form")
    public String createForm() {
        return "person/addPerson";
    }

    @GetMapping("/update/form/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "person/updatePerson";
    }

    @PostMapping("/save")
    public String save(@Valid PersonDtoRequest personDto) {
        Person person = new Person();
        person.setName(personDto.name());
        person.setSurname(personDto.surname());
        person.setEmail(personDto.email());
        person.setPesel(personDto.pesel());
        person.setTrainingList(new ArrayList<>());

        personService.save(person);
        return "index";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        personService.deleteById(id);
        return "redirect:/person/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, UpdatePersonDto dto) {
        Person person = personService.findById(id);
        person.setId(id);
        person.setSurname(dto.surname());
        person.setEmail(dto.email());
        personService.save(person);
        return "redirect:/person/list";
    }

    @DeleteMapping("/delete/training/{id}/{trainingId}")
    public String deleteTraining(@PathVariable Long id, @PathVariable Long trainingId) {
        Person person = personService.findById(id);
        Training training = trainingService.findById(trainingId);
        trainingService.deletePerson(training, person);
        return "redirect:/person/list/training/{id}";
    }
}
