package ABSMovies.service;

import ABSMovies.entity.Favorite;
import ABSMovies.entity.User;
import ABSMovies.exception.ApiUserException;
import ABSMovies.repository.FavoriteRepository;
import ABSMovies.repository.UserRepository;
import ABSMovies.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, FavoriteRepository favoriteRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserFromDB(CustomUserDetails user){
        return userRepository.getUserByEmail(user.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with email " + user.getUsername() + " doesn't exist!"));
    }

    public Boolean addUser(User user){
        userRepository.getUserByEmail(user.getEmail())
                .ifPresent(userFromDB ->
                        {throw new ApiUserException("User with email " + userFromDB.getEmail() + " is already exist!");});

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Boolean addFavorite(CustomUserDetails user, Long film_id){

        User userFromDB = getUserFromDB(user);

        userFromDB.getFavorites()
                .stream()
                .filter(favorite -> favorite.getFilmId() == film_id)
                .findFirst().ifPresent(favorite ->
                    {throw new ApiUserException("Movie with ID " + favorite.getFilmId() + " has already been added to the user's list!");});

        if (favoriteRepository.getFavoriteByFilmId(film_id).isEmpty()){
           Favorite favorite = new Favorite();
           favorite.setFilmId(film_id);
           favoriteRepository.save(favorite);
       }

       Favorite favoriteFromDB = favoriteRepository.getFavoriteByFilmId(film_id).get();
       userFromDB.setFavorite(favoriteFromDB);
       userRepository.save(userFromDB);
       return true;
    }

    public Boolean deleteFavorite(CustomUserDetails user, Long film_id){

        User userFromDB = getUserFromDB(user);

        Favorite favoriteFromDB = userFromDB.getFavorites()
                .stream()
                .filter(favorite -> favorite.getFilmId() == film_id)
                .findFirst().orElseThrow(() -> new ApiUserException("The user doesn't have this movie!"));

        userFromDB.getFavorites().remove(favoriteFromDB);

        userFromDB.setFavorites(userFromDB.getFavorites());
        userRepository.save(userFromDB);
        return true;
    }

    public Set<Long> getFavorite(CustomUserDetails user){

        Set<Favorite> favorites = userRepository.getUserByEmail(user.getUsername())
                .map(User::getFavorites).orElseThrow();

        Set<Long> setOfFavorites = new HashSet<>();
        favorites.forEach(favorite -> setOfFavorites.add(favorite.getFilmId()));
        return setOfFavorites;
    }

    public CustomUserDetails getUserFromSecurityContextHolder() {
        SecurityContext context = SecurityContextHolder.getContext();
        CustomUserDetails user = (CustomUserDetails) context.getAuthentication().getPrincipal();
        return user;
    }
}
