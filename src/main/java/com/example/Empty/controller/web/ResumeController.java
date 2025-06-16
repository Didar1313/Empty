package com.example.Empty.controller.web;

import com.example.Empty.config.ResumeConfig;
import com.example.Empty.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/resume")
@Controller
public class ResumeController {
    @Autowired
    ResumeConfig resumeConfig;

    @Autowired
    PersonService personService;


    @Value("${site.title}")
    String siteTitle;

    @Value("${site.description}")
    String siteDescription;

    private static Logger logger = LoggerFactory.getLogger(RootController.class);

    @GetMapping
    public String indexPage(Model model) {

        logger.info("Index page is called");

        model.addAttribute("siteTitle", siteTitle);
        model.addAttribute("siteDescription", siteDescription);
        model.addAttribute("personalInfo", resumeConfig.getPersonalInfo());
        model.addAttribute("education", resumeConfig.getEducation());
        model.addAttribute("experience", resumeConfig.getExperience());
        model.addAttribute("skills", resumeConfig.getSkills());
        model.addAttribute("persons", personService.getAllPerson(Pageable.unpaged()));

        logger.info("Index page is rendered");

        return "resume/index";
    }
}