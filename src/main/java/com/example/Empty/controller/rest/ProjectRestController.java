package com.example.Empty.controller.rest;

import com.example.Empty.exception.NotFoundException;
import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequest;
import com.example.Empty.model.dto.UpdatePersonRequest;
import com.example.Empty.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person byId;
        try {
             byId = personService.getById(id);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(byId);
    }


     @Tag(name = "Update a person", description = "Update a person search by Id")
     @PutMapping("api/persons/{id}")
     public Person updatePersonById(@PathVariable Long id, @RequestBody UpdatePersonRequest updatePersonRequest) {
        return personService.updateById(id, updatePersonRequest);
    }
    @Tag(name = "Update a person", description = "Update a person search by Id")
    @DeleteMapping("api/persons/{id}")
    public String deletePersonById(@PathVariable Long id) {
         personService.deletePerson(id);
         return "Deleted person with id: " + id;
    }


}
