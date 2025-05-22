package com.example.Empty.service;

import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    public List<Person> personList=new ArrayList<>();

    public List<Person>getAllPerson(){
        return personList;
    }
    public Person createPerson(CreatePersonRequest createPersonRequest){
        Person person=new Person(createPersonRequest.getfName(),createPersonRequest.getlName());
        personList.add(person);
        return person;
    }
}
