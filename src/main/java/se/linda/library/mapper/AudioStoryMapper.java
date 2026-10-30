package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.AudioStoryRequest;
import se.linda.library.dto.response.AudioStoryResponse;
import se.linda.library.model.detail.Universe;
import se.linda.library.model.entity.AudioStory;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.UniverseRepository;

@Component
public class AudioStoryMapper {

    private final UniverseRepository universeRepository;

    public AudioStoryMapper(UniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public AudioStory toEntity (AudioStoryRequest request) {

        Universe universe = null;
        if (request.universeName() != null){
            universe = universeRepository.findById(request.universeName())
                    .orElseGet(() -> universeRepository.save(new Universe(request.universeName())));
        }

        AudioStory audioStory = new AudioStory();
        audioStory.setTitle(request.title());
        audioStory.setLanguage(request.language());
        audioStory.setTropeGenre(request.tropeGenre());
        audioStory.setDurationSeconds(request.durationSeconds());
        audioStory.setItemType(ItemType.AUDIO_STORY);
        audioStory.setUniverse(universe);
        audioStory.setSynopsis(request.synopsis());

        return audioStory;
    }

    public AudioStoryResponse toResponse (AudioStory story) {

        String universe = story.getUniverse() != null
                ? story.getUniverse().getUniverseName()
                : null;

        return new AudioStoryResponse(
                story.getId(),
                story.getTitle(),
                story.getLanguage(),
                story.getTropeGenre(),
                story.getDurationSeconds(),
                universe,
                story.getSynopsis()
        );
    }
}
