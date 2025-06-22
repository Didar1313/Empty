package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.mapper.PersonMapper;
import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequestRecord;
import com.example.Empty.model.dto.UpdatePersonRequest;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PersonService {

    @Autowired
     PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;



    public List<Person> getAllPerson(Pageable pageable) {
        List<PersonEntity> entityList = personRepository.findAll(pageable).getContent();
        return entityList.stream().map(entity -> {
            return personMapper.entityToDomain(entity);
        }).toList();
    }

    public Person createPerson(CreatePersonRequestRecord createPersonRequest){

        PersonEntity entity = personMapper.domainToEntity(createPersonRequest);
        PersonEntity savedEntity = personRepository.save(entity);
        return personMapper.entityToDomain(savedEntity);
    }

    /**
     * Retrieves a `Person` by their unique identifier.
     *
     * @param id the unique identifier of the person to be retrieved
     * @return the `Person` corresponding to the given id
     * @throws NotFoundException if no person is found with the specified id
     */
    public Person getById(Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(()-> new NotFoundException("Person not found"));
        return personMapper.entityToDomain(personEntity);
    }

    public Person updateById(Long id, UpdatePersonRequest updatePersonRequest) {
        PersonEntity entity = personRepository.findById(id).orElseThrow(()-> new NotFoundException("Person not found"));
        entity.setLName(updatePersonRequest.lName());
        PersonEntity savedEntity = personRepository.save(entity);
        return new Person(savedEntity.getId(), savedEntity.getFName(), savedEntity.getLName());
    }

    public void deletePerson(Long id) {
        PersonEntity entity = personRepository.findById(id).orElseThrow(()-> new RuntimeException("Person not found"));
        personRepository.delete(entity);
    }
}
