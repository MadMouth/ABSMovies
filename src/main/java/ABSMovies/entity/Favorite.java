package ABSMovies.entity;

import javax.persistence.*;


@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "film_id")
    private  Long filmId;


    public Favorite(Long id, Long filmId) {
        this.id = id;
        this.filmId = filmId;
    }

    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }
}
