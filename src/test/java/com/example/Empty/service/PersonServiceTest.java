package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.mapper.PersonMapper;
import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequestRecord;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Test
    void deleteProject_deletes_when_project_exists() {
        // given
        Long projectId = 1L;
        PersonEntity entity = new PersonEntity();
        entity.setId(projectId);
        entity.setFName("Test Project");
        entity.setLName("Test Description");

        when(personRepository.findById(projectId)).thenReturn(Optional.of(entity));

        // when
        personService.deletePerson(projectId);

        // then
        Mockito.verify(personRepository).delete(entity);
        Mockito.verify(personRepository).findById(projectId);
    }

    @Test
    void deletePerson_throws_Exception_when_person_not_found() {
        // given
        Long invalidPersonId = 99L;

        when(personRepository.findById(invalidPersonId)).thenReturn(Optional.empty());

        // then
        Assertions.assertThrows(RuntimeException.class, () -> personService.deletePerson(invalidPersonId));

        // verify
        Mockito.verify(personRepository).findById(invalidPersonId);
        Mockito.verify(personRepository, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    void createPerson_saves_and_returns_saved_person_when_valid_request() {
        // given
        CreatePersonRequestRecord createPersonRequest = new CreatePersonRequestRecord("First", "Last");

        PersonEntity incomingEntity = new PersonEntity();
        incomingEntity.setFName(createPersonRequest.fName());
        incomingEntity.setLName(createPersonRequest.lName());

        PersonEntity savedEntity = new PersonEntity();
        savedEntity.setId(1L);
        savedEntity.setFName("First");
        savedEntity.setLName("Last");

        Person expectedPerson = new Person(1L, "First", "Last");

        // Mock the mapper
        when(personMapper.domainToEntity(createPersonRequest)).thenReturn(incomingEntity);

        // Mock the repository
        when(personRepository.save(incomingEntity)).thenReturn(savedEntity);

        // Mock the mapper again for entity to domain conversion
        when(personMapper.entityToDomain(savedEntity)).thenReturn(expectedPerson);

        // when
        Person createdPerson = personService.createPerson(createPersonRequest);

        // then
        Assertions.assertEquals(expectedPerson.getId(), createdPerson.getId());
        Assertions.assertEquals(expectedPerson.getFName(), createdPerson.getFName());
        Assertions.assertEquals(expectedPerson.getLName(), createdPerson.getLName());
    }



}