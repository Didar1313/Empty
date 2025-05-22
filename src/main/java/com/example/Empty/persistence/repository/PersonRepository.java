package com.example.Empty.persistence.repository;

import com.example.Empty.model.domain.Person;
import com.example.Empty.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
