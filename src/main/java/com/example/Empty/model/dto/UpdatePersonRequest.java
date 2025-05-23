package com.example.Empty.model.dto;

public class UpdatePersonRequest {
    private String fName;

    public UpdatePersonRequest(String fName) {
        this.fName = fName;

    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
