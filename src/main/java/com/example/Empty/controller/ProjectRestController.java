package com.example.Empty.controller;

import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectRestController {

    public List<Person> personList=new ArrayList<>();



    @GetMapping("api/persons")
    public List<Person> getAllPerson() {
        return personList;
    }

    @PostMapping("api/persons")
    public Person createPerson(@RequestBody CreatePersonRequest personDTO) {
        Person person=new Person(personDTO.getfName(), personDTO.getlName());
        personList.add(person);
        return person;
    }

}
