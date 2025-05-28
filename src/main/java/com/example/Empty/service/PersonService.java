package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequestRecord;
import com.example.Empty.model.dto.UpdatePersonRequest;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PersonService {

    @Autowired
     PersonRepository personRepository;


    public List<Person>getAllPerson(){
        List<PersonEntity> personEntities= personRepository.findAll();
        return personEntities.stream().map(personEntity -> {
            Person person = new Person(personEntity.getId(),personEntity.getfName(),personEntity.getlName());
            return person;
        }).toList();
    }

    public Person createPerson(CreatePersonRequestRecord createPersonRequest){


        PersonEntity entity = new PersonEntity();
        entity.setfName(createPersonRequest.fName());
        entity.setlName(createPersonRequest.lName());

        PersonEntity savedEntity = personRepository.save(entity);

        Long id = savedEntity.getId();
        String fName = savedEntity.getfName();
        String lName = savedEntity.getlName();
        return new Person(id, fName, lName);
    }

    public Person getById(Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(()-> new NotFoundException("Person not found"));
        return new Person(personEntity.getId(), personEntity.getfName(), personEntity.getlName());
    }

    public Person updateById(Long id, UpdatePersonRequest updatePersonRequest) {
        PersonEntity entity = personRepository.findById(id).orElseThrow(()-> new NotFoundException("Person not found"));
        entity.setfName(updatePersonRequest.getfName());
        PersonEntity savedEntity = personRepository.save(entity);
        return new Person(savedEntity.getId(), savedEntity.getfName(), savedEntity.getlName());
    }

    public void deletePerson(Long id) {
        PersonEntity entity = personRepository.findById(id).orElseThrow(()-> new RuntimeException("Person not found"));
        personRepository.delete(entity);
    }
}
