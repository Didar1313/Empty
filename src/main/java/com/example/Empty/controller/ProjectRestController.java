package com.example.Empty.controller;

import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectRestController {

    public List<Person> personList=new ArrayList<>();


    @Tag(name = "Get Person List", description = "Get all the person list at a time")
    @GetMapping("api/persons")
    public List<Person> getAllPerson() {
        return personList;
    }

    @Tag(name = "Create a project", description = "Create a new Project")
    @PostMapping("api/persons")
    public Person createPerson(@RequestBody CreatePersonRequest personDTO) {
        Person person=new Person(personDTO.getfName(), personDTO.getlName());
        personList.add(person);
        return person;
    }

}
