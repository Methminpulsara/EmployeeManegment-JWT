package edu.icet.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class UserEntitiy {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String username;
    private String password;

}
