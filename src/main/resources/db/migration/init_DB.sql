CREATE TABLE user (
    id INT NOT NULL  AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE favorite (
    id INT NOT NULL AUTO_INCREMENT,
    film_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_favorite (
    user_id INT NOT NULL,
    favorite_id INT NOT NULL,
    PRIMARY KEY (user_id, favorite_id),
    FOREIGN KEY (favorite_id) REFERENCES favorite (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
