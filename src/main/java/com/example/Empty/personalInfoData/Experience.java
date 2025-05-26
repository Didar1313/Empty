package com.example.Empty.personalInfoData;

public class Experience {
    private String title;
    private String company;
    private String years;
    private String description;

    public Experience(String title, String company, String years, String description) {
        this.title = title;
        this.company = company;
        this.years = years;
        this.description = description;
    }

    // Getters
    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getYears() { return years; }
    public String getDescription() { return description; }
}
