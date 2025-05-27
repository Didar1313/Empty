package com.example.Empty.controller.web;
import com.example.Empty.config.ResumeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {

    @Autowired
    ResumeConfig resumeConfig;

    @Value("${site.title}")
    String siteTitle;

    @Value("${site.description}")
    String siteDescription;

    private static Logger logger = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public String index(Model model) {
        logger.info("Index page is called");
        model.addAttribute("siteTitle", siteTitle);
        model.addAttribute("siteDescription", siteDescription);

        model.addAttribute("personalInfo", resumeConfig.getPersonalInfo());
        model.addAttribute("education", resumeConfig.getEducation());
        model.addAttribute("experience", resumeConfig.getExperience());
        model.addAttribute("skills", resumeConfig.getSkills());
        logger.info("Index page is rendered");

        return "index";
    }

}
