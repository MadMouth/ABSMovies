package ABSMovies.service;

import ABSMovies.Repository.UserRepository;
import ABSMovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean doesTheUserExist(User user) {


        return Optional.ofNullable(userRepository.findByUsername(user.getUsername())).isPresent();

    }
}
