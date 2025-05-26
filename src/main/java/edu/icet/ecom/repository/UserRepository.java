package edu.icet.ecom.repository;

import edu.icet.ecom.entity.UserEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntitiy,Long> {

    Optional<UserEntitiy> findByUsername(String username);

}
