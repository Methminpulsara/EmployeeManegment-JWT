package edu.icet.ecom.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class EmployeeController {

    @GetMapping
    public String getEmployee(){
        return "have";
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }
}
