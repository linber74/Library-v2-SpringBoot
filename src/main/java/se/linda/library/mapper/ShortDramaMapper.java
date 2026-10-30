package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.ShortDramaRequest;
import se.linda.library.dto.response.ShortDramaResponse;
import se.linda.library.model.detail.Universe;
import se.linda.library.model.entity.ShortDrama;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.UniverseRepository;

@Component
public class ShortDramaMapper {

    private final UniverseRepository universeRepository;

    public ShortDramaMapper(UniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public ShortDrama toEntity (ShortDramaRequest request) {

        Universe universe = null;
        if (request.universeName() != null){
            universe = universeRepository.findById(request.universeName())
                    .orElseGet(() -> universeRepository.save(new Universe(request.universeName())));
        }

        ShortDrama shortDrama = new ShortDrama();
        shortDrama.setTitle(request.title());
        shortDrama.setLanguage(request.language());
        shortDrama.setTropeGenre(request.tropeGenre());
        shortDrama.setDurationSeconds(request.durationSeconds());
        shortDrama.setItemType(ItemType.SHORT_DRAMA);
        shortDrama.setUniverse(universe);
        shortDrama.setSynopsis(request.synopsis());

        return shortDrama;
    }

    public ShortDramaResponse toResponse (ShortDrama drama) {

        String universe = drama.getUniverse() != null
                ? drama.getUniverse().getUniverseName()
                : null;

        return new ShortDramaResponse(
                drama.getId(),
                drama.getTitle(),
                drama.getLanguage(),
                drama.getTropeGenre(),
                drama.getDurationSeconds(),
                universe,
                drama.getSynopsis()
        );
    }
}
