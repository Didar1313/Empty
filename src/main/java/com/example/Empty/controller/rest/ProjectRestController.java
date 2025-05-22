package com.example.Empty.controller.rest;

import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequest;
import com.example.Empty.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectRestController {

    @Autowired
    PersonService personService;

    @Tag(name = "Get Person List", description = "Get all the person list at a time")
    @GetMapping("api/persons")
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @Tag(name = "Create a person", description = "Create a new Project")
    @PostMapping("api/persons")
    public Person createPerson(@RequestBody CreatePersonRequest personDTO) {
        return  personService.createPerson(personDTO);
    }

    @Tag(name = "Search By id", description = "Get a person by search id")
    @GetMapping("api/persons/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getById(id);
    }

//    @Tag(name = "Update a person", description = "Update a person search by Id")
//    @GetMapping("api/persons/{sid}")
//    public Person UpdatePersonById(@PathVariable Long sid, @RequestBody CreatePersonRequest createPersonRequest) {
//        return personService.updateById(sid, createPersonRequest);
//    }



}
