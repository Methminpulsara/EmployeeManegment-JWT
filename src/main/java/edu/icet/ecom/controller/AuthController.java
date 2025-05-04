package edu.icet.ecom.controller;


import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public List<UserEntitiy> get(){
        return authService.getAllUsers();
    }

    @PostMapping
    public UserEntitiy createUser(@RequestBody UserEntitiy userEntitiy){
        return authService.createNewUser(userEntitiy);
    }

}
