package ABSMovies.repository;

import ABSMovies.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
        public Optional<Favorite> getFavoriteByFilmId(Long film_id);
}
