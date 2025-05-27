package edu.icet.ecom.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {

    private Long employeeId;
    private String name;
    private String email;
    private String department;
    private String createdDate;
    private String modifiedDate;


}
