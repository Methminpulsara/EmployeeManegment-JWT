package edu.icet.ecom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.bridge.IMessage;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table (name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false )
    private String name;

    @Column(nullable = false )
    private String email;

    @Column(nullable = false )
    private String department;

    @Column(nullable = false )
    private String createdDate;

    @Column(nullable = false)
    private String modifiedDate;


}
