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


    @GetMapping("/username/{token}")
    public String getUserName(@PathVariable String token){
        return jwtService.getUserName(token);
    }



}
