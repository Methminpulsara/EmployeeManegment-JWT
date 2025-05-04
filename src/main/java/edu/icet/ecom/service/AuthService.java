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
    private final PasswordEncoder passwordEncoder;

    public List<UserEntitiy> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntitiy createNewUser(UserEntitiy user){
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepository.save(user);
    }

}
