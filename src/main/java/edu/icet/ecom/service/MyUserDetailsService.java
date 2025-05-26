package edu.icet.ecom.service;

import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {



    private final UserRepository userRepository;



    //userwa load krana method eka (userwa hoya gnnne methanin)
    // provider eken methna username eka pass krnawa

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntitiy> userdata = userRepository.findByUsername(username);
        if (userdata.isEmpty())throw new UsernameNotFoundException("user not found");

        UserDetails user = User.builder()
                .username(userdata.get().getUsername())
                .password(userdata.get().getPassword())
                .build();
        return user;
    }
}
