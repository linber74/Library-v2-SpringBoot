package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.ShortDramaRequest;
import se.linda.library.dto.response.ShortDramaResponse;
import se.linda.library.model.entity.ShortDrama;

@Component
public class ShortDramaMapper {

    public ShortDrama toEntity (ShortDramaRequest request) {

        ShortDrama shortDrama = new ShortDrama();
        shortDrama.setTitle(request.title());
        shortDrama.setLanguage(request.language());
        shortDrama.setTropeGenre(request.tropeGenre());
        shortDrama.setDurationSeconds(request.durationSeconds());

        return shortDrama;
    }

    public ShortDramaResponse toResponse (ShortDrama drama) {

        return new ShortDramaResponse(
                drama.getId(),
                drama.getTitle(),
                drama.getLanguage(),
                drama.getTropeGenre(),
                drama.getDurationSeconds()
        );
    }
}
