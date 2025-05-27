package edu.icet.ecom.service;

import edu.icet.ecom.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto search(String name);
    EmployeeDto update (Long employeeId , EmployeeDto employeeDto);
    void delete(Long employeeId);
    List<EmployeeDto> getAllEmployees();

}
