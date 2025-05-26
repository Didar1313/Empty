package com.example.Empty.controller.web;

import com.example.Empty.personalInfoData.Education;
import com.example.Empty.personalInfoData.Experience;
import com.example.Empty.personalInfoData.PersonalInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class RootController {

    @GetMapping("/")
    public String index(Model model) {

        //Personal Info
        PersonalInfo personalInfo=new PersonalInfo(
                "Didar Bhuiyan", "didarbhuiyan1313@gmail.com",
                "https://www.linkedin.com/in/md-didar-bhuiyan/",
                "https://github.com/Didar1313");
        model.addAttribute("personalInfo", personalInfo);

        //Education
        List<Education> education= Arrays.asList(
                new Education(
                        "BSc in CSE", "Green University of Bangladesh",
                        "2021 - 2025", "Working as a Backend Developer"));
        model.addAttribute("education", education);

        //Experience
        List<Experience> experience= Arrays.asList(
                new Experience(
                        "Software Engineer", "Empty",
                        "2025 - Present",
                        "Working as a Backend Developer"
                ),
                new Experience(
                        "Junior Developer", "Wizard Tech Banglaesh Ltd",
                        "2024", "Working as a ..."
                )
        );
        model.addAttribute("experience", experience);

        // Skills
        List<String> skills = Arrays.asList("Java", "Spring Boot", "Thymeleaf");
        model.addAttribute("skills", skills);

        return "index";
    }

}
