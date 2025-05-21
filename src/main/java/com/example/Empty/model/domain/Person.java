package com.example.Empty.model.domain;

public class Person {
    private String fName;
    private String lName;

    public Person(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getName() {
        return fName;
    }

    public void setName(String name) {
        this.fName = name;
    }

    public String getAge() {
        return lName;
    }

    public String setAge(String lName) {
        return this.lName = lName;
    }
}
