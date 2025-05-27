package edu.icet.ecom.service.impl;

import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {



    private final UserRepository userRepository;



    //userwa load krana method eka (userwa hoya gnnne methanin)
    // provider eken methna username eka pass krnawa

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntitiy userData = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .build();
    }

}
