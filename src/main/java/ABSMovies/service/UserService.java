package ABSMovies.service;

import ABSMovies.entity.Favorite;
import ABSMovies.entity.User;
import ABSMovies.exception.ApiUserException;
import ABSMovies.repository.FavoriteRepository;
import ABSMovies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public UserService(UserRepository userRepository, FavoriteRepository favoriteRepository) {
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
    }

    public  User getUserFromDB(User user){
        return userRepository.getUserByEmail(user.getEmail())
                .orElseThrow(() -> new ApiUserException("User with email " + user.getEmail() + " doesn't exist!"));
    }

    public Boolean addUser(User user){
        userRepository.getUserByEmail(user.getEmail())
                .ifPresent(userFromDB -> {throw new ApiUserException("user with email " + userFromDB.getEmail() + " is already exist!");});

        userRepository.save(user);
        return true;
    }

    public Boolean addFavorite(User user, Long film_id){

        User userFromDB = getUserFromDB(user);
        //Переписать
        Optional<Set<Favorite>> optionalFavorites = Optional.ofNullable(userFromDB.getFavorites());
            Stream<Favorite> streamFavorites = optionalFavorites
                    .map(favorites -> favorites.stream()
                            .filter(favorite -> favorite.getFilmId() == film_id)).get();
            if (streamFavorites.findFirst().isPresent()){
                throw new ApiUserException("This movie has already been added to the user's list!");
            }


        if (favoriteRepository.getFavoriteByFilmId(film_id).isEmpty()){
           Favorite favorite = new Favorite();
           favorite.setFilmId(film_id);
           favoriteRepository.save(favorite);
       }

       Favorite favorite = favoriteRepository.getFavoriteByFilmId(film_id).get();
       userFromDB.setFavorite(favorite);
       userRepository.save(userFromDB);
       return true;
    }

    public Boolean deleteFavorite(User user, Long film_id){

        User userFromDB = getUserFromDB(user);
        //Переписать
        Set<Favorite> favoritesFromDB = userFromDB.getFavorites();
        Optional<Favorite> favoriteRemove = favoriteRepository.getFavoriteByFilmId(film_id);
        favoritesFromDB.remove(favoriteRemove.orElseThrow(()
                -> new ApiUserException("This user doesn't have this movie!")));
        userFromDB.setFavorites(favoritesFromDB);
        return true;
    }

    public Set<Favorite> getFavorite(User user){
        Set<Favorite> favorites = userRepository.getUserByEmail(user.getEmail())
                .map(User::getFavorites).orElseThrow();

        return favorites;
    }
}
