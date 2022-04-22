package ABSMovies.controller;

import ABSMovies.payload.request.FavoriteRequest;
import ABSMovies.payload.response.FavoriteListResponse;
import ABSMovies.security.CustomUserDetails;
import ABSMovies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/favorite")
    public Boolean addFavorite(@RequestBody FavoriteRequest favoriteRequest){
        CustomUserDetails user = userService.getUserFromSecurityContextHolder();
        return userService.addFavorite(user, favoriteRequest.getKinopoiskId());
    }

    @DeleteMapping("/favorite")
    public Boolean deleteFavorite(@RequestBody FavoriteRequest favoriteRequest){
        CustomUserDetails user = userService.getUserFromSecurityContextHolder();
        return userService.deleteFavorite(user, favoriteRequest.getKinopoiskId());
    }

    @GetMapping("/favorite")
    public FavoriteListResponse getFavorite(){
        CustomUserDetails user = userService.getUserFromSecurityContextHolder();
        Set<Long> favorite = userService.getFavorite(user);
        return new FavoriteListResponse(favorite);
    }
}
