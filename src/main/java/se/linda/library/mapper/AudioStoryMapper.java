package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.AudioStoryRequest;
import se.linda.library.dto.response.AudioStoryResponse;
import se.linda.library.model.entity.AudioStory;

@Component
public class AudioStoryMapper {

    public AudioStory toEntity (AudioStoryRequest request) {

        AudioStory audioStory = new AudioStory();
        audioStory.setTitle(request.title());
        audioStory.setLanguage(request.language());
        audioStory.setTropeGenre(request.tropeGenre());
        audioStory.setDurationSeconds(request.durationSeconds());

        return audioStory;
    }

    public AudioStoryResponse toResponse (AudioStory story) {

        return new AudioStoryResponse(
                story.getId(),
                story.getTitle(),
                story.getLanguage(),
                story.getTropeGenre(),
                story.getDurationSeconds()
        );
    }
}
