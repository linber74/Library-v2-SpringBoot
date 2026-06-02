package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.Film;
import se.linda.library.model.entity.VisualMedia;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;

import java.util.List;

public interface VisualMediaRepository extends JpaRepository<VisualMedia, Long> {

    List<Film> findByDirectorContainingIgnoreCase(String director);
    List<Film> findByActorsContainingIgnoreCase(String actor);
    List<Film> findByMediaFormatContainingIgnoreCase(MediaFormat mediaFormat);
    List<Film> findByTranslationInfoContainingIgnoreCase(TranslationInfo translationInfo);
}
