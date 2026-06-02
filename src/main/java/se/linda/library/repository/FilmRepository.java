package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.Film;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
