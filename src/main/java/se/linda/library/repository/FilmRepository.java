package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
