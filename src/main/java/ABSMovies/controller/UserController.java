package ABSMovies.controller;

import ABSMovies.entity.Favorite;
import ABSMovies.entity.User;
import ABSMovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/favorite")
    public Boolean addFavorite(@RequestBody User user, @RequestParam Long film_id){

        return userService.addFavorite(user, film_id);
    }

    @DeleteMapping("/favorite")
    public Boolean deleteFavorite(@RequestBody User user, @RequestParam Long film_id){
        User userFromDB = userService.getUserFromDB(user);

        return userService.deleteFavorite(userFromDB, film_id);
    }

    @GetMapping("/favorite")
    public Set<Favorite> getFavorite(@RequestParam String email){
        User user = new User();
        user.setEmail(email);
        User userFromDB = userService.getUserFromDB(user);

        Set<Favorite> favorites = userService.getFavorite(userFromDB);
        return favorites;
    }

    }
