package com.example.Empty.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Setter
@Getter
@Entity
public class PersonEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String fName;
    private String lName;


}
