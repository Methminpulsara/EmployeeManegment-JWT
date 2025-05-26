package edu.icet.ecom.controller;

import edu.icet.ecom.dto.LoginRequestDto;
import edu.icet.ecom.dto.LogingResponseDto;
import edu.icet.ecom.dto.RegisterRequestDto;
import edu.icet.ecom.dto.RegisterResponseDto;
import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public UserEntitiy createUser(@RequestBody RegisterRequestDto user){
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LogingResponseDto> login(@RequestBody LoginRequestDto loginRequest){
       LogingResponseDto res = authService.login(loginRequest);
       if(res.getError()!=null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
       }
       return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto req){
        RegisterResponseDto res = authService.register(req);
        if(res.getError()!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



}
