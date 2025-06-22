package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.mapper.PersonMapper;
import com.example.Empty.model.domain.Person;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  //t automatically initializes all the @Mock and @InjectMocks fields before each test method runs.
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Test
    void getAllProjects_returns_project_list() {
        // given
        Pageable pageable = Pageable.unpaged();
        PersonEntity entity1 = new PersonEntity();
        entity1.setId(1L);
        entity1.setFName("Project 1");
        entity1.setLName("Description 1");

        PersonEntity entity2 = new PersonEntity();
        entity2.setId(2L);
        entity2.setFName("Project 2");
        entity2.setLName("Description 2");

        List<PersonEntity> entities = List.of(entity1, entity2);

        // Mock the repository to return a Page with the entities
        when(personRepository.findAll(pageable)).thenReturn(new PageImpl<>(entities));
        when(personMapper.entityToDomain(entity1)).thenReturn(new Person(1L, "Project 1", "Description 1"));
        when(personMapper.entityToDomain(entity2)).thenReturn(new Person(2L, "Project 2", "Description 2"));

        // when
        List<Person> result = personService.getAllPerson(pageable);

        // then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1L, result.get(0).getId());
        Assertions.assertEquals("Project 1", result.get(0).getFName());
        Assertions.assertEquals("Description 1", result.get(0).getLName());
        Assertions.assertEquals(2L, result.get(1).getId());
        Assertions.assertEquals("Project 2", result.get(1).getFName());
        Assertions.assertEquals("Description 2", result.get(1).getLName());
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