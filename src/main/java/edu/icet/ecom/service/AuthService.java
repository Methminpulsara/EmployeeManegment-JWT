package edu.icet.ecom.service;

import edu.icet.ecom.dto.LoginRequestDto;
import edu.icet.ecom.dto.LogingResponseDto;
import edu.icet.ecom.dto.RegisterRequestDto;
import edu.icet.ecom.dto.RegisterResponseDto;
import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ModelMapper mapper;


  public List<UserEntitiy> getAllUsers(){
      return userRepository.findAll();
  }

    public UserEntitiy createUser(RegisterRequestDto userData){
      String encodedPassword = encoder.encode(userData.getPassword());
      userData.setPassword(encodedPassword);
       return userRepository.save(mapper.map(userData,UserEntitiy.class));
    }

    public LogingResponseDto login(LoginRequestDto loginRequest){
//      Boolean userPresent = isUserExist(loginRequest.getUsername());
//      if(!userPresent){
//          return new LogingResponseDto(null,null, "User not found", "if error");
//      }
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),loginRequest.getPassword()));

        }catch (Exception e){
            return new LogingResponseDto(
                    null,null, "User not found", "if error");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("role","User");
        claims.put("email","Company@gmail.com");

        String token = jwtService.getJWTToken(loginRequest.getUsername(),claims);

        System.out.println(jwtService.getFieldFromToken(token,"role"));

       return new LogingResponseDto(
               token, LocalDateTime.now(),null,"token  genarated success !");
    }

    public RegisterResponseDto register(RegisterRequestDto req){
      if(isUserExist(req.getUsername())){
          return new RegisterResponseDto(null,"User already exits the System");
      }
        var userData = this.createUser(req);
        if (userData.getUserId()==null){
            return new RegisterResponseDto(null,"System error");
        }

        return new RegisterResponseDto(String.format("User Created",userData.getUserId()),null);

    }

    private Boolean isUserExist(String username) {
          return userRepository.findByUsername(username).isPresent();
    }
}
