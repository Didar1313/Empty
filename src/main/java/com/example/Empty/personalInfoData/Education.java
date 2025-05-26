package com.example.Empty.personalInfoData;

public class Education {

    private String degree;
    private String institution;
    private String years;
    private String description;

    public Education(String degree, String institution, String years, String description) {
        this.degree = degree;
        this.institution = institution;
        this.years = years;
        this.description = description;
    }

    public String getDegree() {
        return degree;
    }

    public String getInstitution() {
        return institution;
    }

    public String getYears() {
        return years;
    }

    public String getDescription() {
        return description;
    }
}
