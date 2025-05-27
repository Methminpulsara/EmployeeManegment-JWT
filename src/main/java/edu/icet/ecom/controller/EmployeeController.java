package edu.icet.ecom.controller;

import edu.icet.ecom.dto.EmployeeDto;
import edu.icet.ecom.service.EmployeeService;
import edu.icet.ecom.service.impl.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final JWTService jwtService;
    private final EmployeeService service;

    @PostMapping("/add")
    public EmployeeDto add (@RequestBody EmployeeDto employee){
        employee.setEmployeeId(null);
        return service.addEmployee(employee);
    }

    @GetMapping("/all")
    public List<EmployeeDto> getAll(){
        return service.getAllEmployees();
    }

    @GetMapping("/search/{name}")
    public EmployeeDto search(@PathVariable String name){
        return service.search(name);
    }

    @PutMapping("/update/{id}")
    public EmployeeDto update (@PathVariable Long id , @RequestBody EmployeeDto employee){
        return service.update(id,employee);
    }

    @DeleteMapping("/delete/{id}")
    public void delete (@PathVariable Long id){
        service.delete(id);
    }

}
