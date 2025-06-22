package com.example.Empty.controller.rest;
import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequestRecord;
import com.example.Empty.model.dto.UpdatePersonRequest;
import com.example.Empty.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonRestController {

    @Autowired
    PersonService personService;

    @Tag(name = "Get Person List", description = "Get all the person list at a time")
    @GetMapping("api/persons")
    public List<Person> getAllPerson(@ParameterObject Pageable pageable) {
        return personService.getAllPerson(pageable);
    }

    @Tag(name = "Create person", description = "Create a new Project")
    @PostMapping("api/persons")
    public void createPerson(@RequestBody CreatePersonRequestRecord personDTO) {
          personService.createPerson(personDTO);
    }

    @Tag(name = "Search By id", description = "Get a person by search id")
    @GetMapping("api/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){

        Person byId;
        byId = personService.getById(id);

        return ResponseEntity.ok(byId);
    }


     @Tag(name = "Update person", description = "Update a person search by Id")
     @PutMapping("api/persons/{id}")
     public Person updatePersonById(@PathVariable Long id, @RequestBody UpdatePersonRequest updatePersonRequest) {
        return personService.updateById(id, updatePersonRequest);
    }

    @Tag(name = "Delete person", description = "Delete a person by Id")
    @DeleteMapping("api/persons/{id}")
    public String deletePersonById(@PathVariable Long id) {
         personService.deletePerson(id);
         return "Deleted person with id: " + id;
    }


}
