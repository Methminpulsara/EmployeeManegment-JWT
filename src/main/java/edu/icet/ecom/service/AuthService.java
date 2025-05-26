package edu.icet.ecom.service;

import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


  public List<UserEntitiy> getAllUsers(){
      return userRepository.findAll();
  }

    public UserEntitiy createUser(UserEntitiy userData){
      String encodePassword = encoder.encode(userData.getPassword());
      userData.setPassword(encodePassword);
     return userRepository.save(userData);
    }


}
