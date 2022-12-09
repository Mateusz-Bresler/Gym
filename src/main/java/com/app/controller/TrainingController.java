package com.app.controller;

import com.app.dto.PersonDtoNameAndSurname;
import com.app.dto.PersonDtoResponse;
import com.app.dto.UpdateTrainingDto;
import com.app.model.training.Training;
import com.app.model.training.TrainingTypes;
import com.app.model.person.Person;
import com.app.service.TrainingService;
import com.app.service.PersonService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static com.app.mapper.PersonDtoMapper.mapPersonToPersonDto;

@RequiredArgsConstructor
@Getter

@Controller
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;
    private final PersonService personService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("trainings", trainingService.getAll());
        return "training/listTraining";
    }

    @GetMapping("/list/person/{id}")
    public String peopleList(Model model, @PathVariable Long id) {
        Training training = trainingService.findById(id);
        List<PersonDtoResponse> personDto = mapPersonToPersonDto(training.getPeopleList());
        model.addAttribute("training", training);
        model.addAttribute("personList", personDto);
        return "training/listPerson";
    }

    @GetMapping("/create/form")
    public String createForm(Model model) {
        model.addAttribute("types", TrainingTypes.values());
        return "training/addTraining";
    }

    @GetMapping("/update/form/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Training training = trainingService.findById(id);
        model.addAttribute("types", TrainingTypes.values());
        model.addAttribute("training", training);
        return "training/updateTraining";
    }

    @GetMapping("/add/person/form/{id}")
    public String addPersonForm(Model model, @PathVariable Long id) {
        Training training = trainingService.findById(id);
        model.addAttribute("training", training);
        return "training/addPersonForm";
    }

    @PostMapping("/save")
    public String save(Training training) {
        training.setPeopleList(new ArrayList<>());
        trainingService.save(training);
        return "index";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        trainingService.deleteById(id);
        return "redirect:/training/list";
    }

    @PostMapping("/update/save/{id}")
    public String update(@PathVariable Long id,  Training trainingDto) {
        Training training = new Training();
        training.setId(id);
        training.setType(trainingDto.getType());
        training.setTime(trainingDto.getTime());
        training.setMaxPeople(trainingDto.getMaxPeople());
        training.setPeopleList(new ArrayList<>());

        trainingService.save(training);
        return "redirect:/training/list";
    }

    @DeleteMapping("/delete/person/{id}/{personId}")
    public String deletePerson(@PathVariable Long id, @PathVariable Long personId) {
        Training training = trainingService.findById(id);
        Person person = personService.findById(personId);
        trainingService.deletePerson(training, person);
        return "redirect:/training/list/person/{id}";
    }

    @PostMapping("/add/person/{trainingId}")
    public String addPerson(@PathVariable Long trainingId, PersonDtoNameAndSurname personDtoNameAndSurname) {
        Training training = trainingService.findById(trainingId);
        Person person = personService.findPersonByNameAndSurname( personDtoNameAndSurname.name(), personDtoNameAndSurname.surname());

        trainingService.addPerson(training, person);
        personService.save(person);
        trainingService.save(training);
        return "redirect:/training/list";
    }


}
