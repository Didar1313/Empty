package com.example.Empty.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter
@Entity
public class PersonEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Setter
    private String fName;
    @Setter
    private String lName;


}
