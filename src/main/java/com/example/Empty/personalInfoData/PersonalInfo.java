package com.example.Empty.personalInfoData;

public class PersonalInfo {

    private String name;
    private String email;
    private String linkedinUrl;
    private String githubUrl;

    public PersonalInfo(String name, String email, String linkedinUrl, String githubUrl) {
        this.name = name;
        this.email = email;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
