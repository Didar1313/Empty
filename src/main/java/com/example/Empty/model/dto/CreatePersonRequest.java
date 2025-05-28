package com.example.Empty.model.dto;

public final class CreatePersonRequest {
    private final String fName;
    private final String lName;

    public CreatePersonRequest(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

}
