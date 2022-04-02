package ABSMovies.service;

import ABSMovies.Repository.UserRepository;
import ABSMovies.entity.Favorite;
import ABSMovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(User user){
       User userFromDb =  userRepository.findByUsername(user.getUsername());
       if (userFromDb != null){
           return false;
       }

       userRepository.saveAndFlush(user);
       return true;
    }

//    public List<Favorite> getAllFavorites(){
//
//    }

}
