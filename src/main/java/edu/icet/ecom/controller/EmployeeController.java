package edu.icet.ecom.controller;

import edu.icet.ecom.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class EmployeeController {

    private final JWTService jwtService;

    @GetMapping
    public String getEmployee(){
        return "have";
    }

    @PostMapping("/login")
    public String login(){
        return jwtService.getJWTToken();
    }

    @GetMapping("/username")
    public String getUserName(@RequestParam String token){
        return jwtService.getUserName(token);
    }



}
