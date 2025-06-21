package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.mapper.PersonMapper;
import com.example.Empty.model.domain.Person;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_valid_id_return_a_person() throws NotFoundException {
        // given/setup
        long expectedId = 1L;
        String expectedFirstName = "Test Project";
        String expectedLastName = "Test Description";

        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(expectedId);
        personEntity.setFName(expectedFirstName);
        personEntity.setLName(expectedLastName);

        // Mock Repository Layer
        when(personRepository.findById(expectedId)).thenReturn(Optional.of(personEntity));

        // Mock Mapper Layer
        Person expectedPerson = new Person(expectedId, expectedFirstName, expectedLastName);
        when(personMapper.entityToDomain(personEntity)).thenReturn(expectedPerson);

        // when
        Person actualPerson = personService.getById(expectedId);

        // then/verify
        Assertions.assertEquals(expectedId, actualPerson.getId());
        Assertions.assertEquals(expectedFirstName, actualPerson.getFName());
        Assertions.assertEquals(expectedLastName, actualPerson.getLName());
    }
}