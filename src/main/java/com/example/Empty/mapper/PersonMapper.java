package com.example.Empty.mapper;

import com.example.Empty.model.domain.Person;
import com.example.Empty.model.dto.CreatePersonRequestRecord;
import com.example.Empty.model.dto.UpdatePersonRequest;
import com.example.Empty.persistence.entity.PersonEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person entityToDomain(PersonEntity personEntity){
        Person domain=new Person();
        BeanUtils.copyProperties(personEntity,domain);
        return domain;
    }

    public PersonEntity domainToEntity(CreatePersonRequestRecord person){
        PersonEntity entity=new PersonEntity();
        BeanUtils.copyProperties(person,entity);
        return entity;
    }

    public PersonEntity updateRequestToEntity(UpdatePersonRequest request, PersonEntity entity) {
        entity.setLName(request.lName());
        return entity;
    }
}
