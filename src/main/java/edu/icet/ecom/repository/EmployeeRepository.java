package edu.icet.ecom.repository;


import edu.icet.ecom.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    EmployeeEntity findByName(String name);
}
