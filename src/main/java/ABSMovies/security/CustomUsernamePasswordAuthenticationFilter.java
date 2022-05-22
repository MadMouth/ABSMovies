package ABSMovies.security;

import ABSMovies.payload.request.LoginRequest;
import ABSMovies.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    } catch (IOException e){
        System.out.println("Error when reading the Json body: " + e.getMessage());
    }
    return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        JwtUtils jwtUtils = new JwtUtils();
        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();
        String access_token = jwtUtils.generateJwtToken(user.getUsername());
        Map<String, String> token = new HashMap<>();
        token.put("token", access_token);
        new ObjectMapper().writeValue(response.getOutputStream(), token);
    }

}
