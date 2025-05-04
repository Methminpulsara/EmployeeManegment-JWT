package edu.icet.ecom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {



    private final PasswordEncoder passwordEncoder;



    //userwa load krana method eka (userwa hoya gnnne methanin)
    // provider eken methna username eka pass krnawa

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = User.builder()
                .username("methmin")
                .password(passwordEncoder.encode("methmin"))
                .build();
        return user;
    }
}
