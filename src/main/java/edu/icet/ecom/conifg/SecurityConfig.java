package edu.icet.ecom.conifg;

import edu.icet.ecom.fillter.JwtFilter;
import edu.icet.ecom.repository.UserRepository;
import edu.icet.ecom.service.impl.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final UserRepository repository;
    private final JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
               .csrf(c->c.disable())
               .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(r->
                r.requestMatchers("/login","/api/auth/login","/api/auth/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
               .authenticationProvider(authenticationProvider())
               // .httpBasic(Customizer.withDefaults())
               .build();
    }

    //dao authentication provider overide
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12); //strength = sequrity eke vadi wenn gnnwa
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService(repository);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }


}
