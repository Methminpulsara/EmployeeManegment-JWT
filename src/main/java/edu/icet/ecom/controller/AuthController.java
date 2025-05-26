package edu.icet.ecom.controller;

import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public List<UserEntitiy> getAllUsers(){
        return authService.getAllUsers();
    }

    @PostMapping
    public UserEntitiy createUser(@RequestBody  UserEntitiy user){
        return authService.createUser(user);
    }


}
