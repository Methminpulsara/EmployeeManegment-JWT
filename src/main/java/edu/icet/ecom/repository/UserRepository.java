package edu.icet.ecom.repository;

import edu.icet.ecom.entity.UserEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntitiy,Long> {

    UserEntitiy findByUsername(String username);

}
