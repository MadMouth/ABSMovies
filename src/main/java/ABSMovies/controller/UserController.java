package ABSMovies.controller;

import ABSMovies.entity.Favorite;
import ABSMovies.entity.User;
import ABSMovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

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
    public Set<Long> getFavorite(@RequestParam String email){
        User user = new User();
        user.setEmail(email);
        User userFromDB = userService.getUserFromDB(user);

        Set<Long> favorites = userService.getFavorite(userFromDB);
        return favorites;
    }
}
