package edu.icet.ecom.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequestDto { //User Dto

    private String name;
    private String email;
    private String username;
    private String password;
}
