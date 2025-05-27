package edu.icet.ecom.fillter;

import edu.icet.ecom.entity.UserEntitiy;
import edu.icet.ecom.repository.UserRepository;
import edu.icet.ecom.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
       String authorization = request.getHeader("Authorization");

       if(authorization==null){
           filterChain.doFilter(request,response);
           return;
       }

       //ena token eka start wenne mehemd blnw
       if (! authorization.startsWith("Bearer ")){
           filterChain.doFilter(request,response);
           return;
       }

       String jwt_token = authorization.split(" ")[1];
       String userName = jwtService.getUserName(jwt_token);

       //user name ek null unoth anith fillers wlt ynn kiynw
       if(userName==null) {
           filterChain.doFilter(request,response);
           return;
       }

        UserEntitiy userData = userRepository.findByUsername(userName).orElse(null);

        //user data null unoth anith fillter ekt
        if(userData==null) {
            filterChain.doFilter(request,response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication()!=null) {
            filterChain.doFilter(request,response);
            return;
        }

        assert userData != null;
        UserDetails userDetails = User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .build();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(token);
         filterChain.doFilter(
                request,response
        );
    }
}
