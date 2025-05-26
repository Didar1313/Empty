package com.example.Empty.controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {  // HttpServletRequest contains all the information about an incoming HTTP request
        Integer statusCode= (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); // request.getAttribute(...) gets the error status code (like 404 or 500).
        String errorMessage= switch (statusCode!=null ? statusCode : 500){
            case 403 -> "Access denied: You are not authorized to access this page";
            case 404 -> "Page not found : The page you are looking for does not exist";
            case 500 -> "Internal Server Error";
            case 503 -> "Service Unavailable";
            case 504 -> "Gateway Timeout";
            default -> "Internal Server Error";
        };
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("errorCode", statusCode != null ? statusCode : "Unknown");
        return "error";
    }
}
