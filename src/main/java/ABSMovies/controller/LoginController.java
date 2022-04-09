package ABSMovies.controller;

import ABSMovies.entity.User;
import ABSMovies.exception.ApiUserException;
import ABSMovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //Переписать
    @PostMapping
    public Boolean LoginUser(@RequestBody User user){
            User userFromDB = userService.getUserFromDB(user);
        return Objects.equals(userFromDB.getPassword(), user.getPassword());
    }

}
