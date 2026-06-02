package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.VisualMedia;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;

import java.util.List;

public interface VisualMediaRepository extends JpaRepository<VisualMedia, Long> {

    List<VisualMedia> findByDirectorContainingIgnoreCase(String director);
    List<VisualMedia> findByActorsContainingIgnoreCase(String actor);
    List<VisualMedia> findByMediaFormat(MediaFormat mediaFormat);
    List<VisualMedia> findByTranslationInfo(TranslationInfo translationInfo);
}
