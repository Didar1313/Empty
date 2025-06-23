package com.example.Empty.controller.rest;
import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequestRecord;
import com.example.Empty.model.dto.UpdatePersonRequest;
import com.example.Empty.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(name = "Person Resource", description = "API for managing person")
@RestController
public class PersonRestController {

    @Autowired
    PersonService personService;

    @Operation(summary = "Get all the person list")
    @GetMapping("api/persons")
    public List<Person> getAllPerson(@ParameterObject Pageable pageable) {
        return personService.getAllPerson(pageable);
    }

    @Operation(summary = "Create person ")
    @PostMapping("api/persons")
    public void createPerson(@RequestBody CreatePersonRequestRecord personDTO) {
          personService.createPerson(personDTO);
    }

    @Operation(summary = "Get Person by Id")
    @GetMapping("api/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){

        Person byId;
        byId = personService.getById(id);

        return ResponseEntity.ok(byId);
    }


    @Operation(summary = "Update Person by ID")
     @PutMapping("api/persons/{id}")
     public void updatePersonById(@PathVariable Long id, @RequestBody UpdatePersonRequest updatePersonRequest) {
         personService.updateById(id, updatePersonRequest);
    }

    @Operation(summary = "Delete Person by ID")
    @DeleteMapping("api/persons/{id}")
    public String deletePersonById(@PathVariable Long id) {
         personService.deletePerson(id);
         return "Deleted person with id: " + id;
    }


}
