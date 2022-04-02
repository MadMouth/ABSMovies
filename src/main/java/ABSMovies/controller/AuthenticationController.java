package ABSMovies.controller;


import ABSMovies.service.AuthenticationService;
import ABSMovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userSerivce;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userSerivce) {
        this.authenticationService = authenticationService;
        this.userSerivce = userSerivce;
    }

//    @PostMapping()
//    public List<Favorite> login(@RequestBody User user){
//        if (authenticationService.doesTheUserExist(user)){
//            return userSerivce.
//        }
//    }

}

