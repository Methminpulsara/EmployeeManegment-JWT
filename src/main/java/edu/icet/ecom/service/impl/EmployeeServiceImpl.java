package edu.icet.ecom.service.impl;

import edu.icet.ecom.dto.EmployeeDto;
import edu.icet.ecom.entity.EmployeeEntity;
import edu.icet.ecom.repository.EmployeeRepository;
import edu.icet.ecom.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
       EmployeeEntity employeeEntity = employeeRepository.save(mapper.map(employeeDto, EmployeeEntity.class));
       employeeEntity = employeeRepository.save(employeeEntity);
       return mapper.map(employeeEntity, EmployeeDto.class);
    }

    @Override
    public EmployeeDto search(String name) {
        return mapper.map(employeeRepository.findByName(name),EmployeeDto.class);
    }

    @Override
    public EmployeeDto update(Long employeeId, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new RuntimeException("Employee not found"));

        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setDepartment(employeeDto.getDepartment());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setModifiedDate(employeeDto.getModifiedDate());

        employeeRepository.save(employeeEntity);

        EmployeeEntity updatedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("Employee Not found after Update"));

        return mapper.map(updatedEmployee,EmployeeDto.class);
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<EmployeeDto> employeeList = new ArrayList<>();
        List<EmployeeEntity> all = employeeRepository.findAll();
        all.forEach(e->employeeList.add(mapper.map(e, EmployeeDto.class)));
        return employeeList;
    }
}
