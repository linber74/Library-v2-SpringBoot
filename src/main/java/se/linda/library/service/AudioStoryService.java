package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.AudioStory;
import se.linda.library.repository.AudioStoryRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
public class AudioStoryService {

    final AudioStoryRepository audioStoryRepository;

    public AudioStoryService(AudioStoryRepository audioStoryRepository) {
        this.audioStoryRepository = audioStoryRepository;
    }

    public AudioStory save (AudioStory audioStory) {
        if(audioStory == null) {
            log.warn("Attempted to save null AudioStory");
            throw new IllegalArgumentException("AudioStory cannot be null");
        }

        log.info("Saving audio story {}", audioStory);
        return audioStoryRepository.save(audioStory);
    }

    public List<AudioStory> getAll(){
        return audioStoryRepository.findAll();
    }

    public AudioStory getById (Long id) {
        return audioStoryRepository.findById(id).orElseThrow(()
                ->{
            log.warn("AudioStory with id {} was not found", id);
            throw new ItemNotFoundException("AudioStory with id " + id + " was not found");
        });
    }

    public void deleteById (Long id) {
        if(!audioStoryRepository.existsById(id)) {
            log.warn("AudioStory with id {} was not found", id);
            throw new ItemNotFoundException("AudioStory with id " + id + " was not found");
        }

        log.info("Deleting audio story with id {}", id);
        audioStoryRepository.deleteById(id);
    }

    public List<AudioStory> searchByDuration(Integer min, Integer max) {

        if(min == null || max == null) {
            log.warn("Min and max duration can't be null");
            throw new IllegalArgumentException("Min and max duration can't be null");
        }

        if(min < 0 || max < 0) {
            log.warn("Duration values can't be negative");
            throw new IllegalArgumentException("Duration values can't be negative");
        }

        if(min > max) {
            log.warn("Min and max duration can't be greater than max");
            throw new IllegalArgumentException("Min and max duration can't be greater than max");
        }
        log.info("Found by duration between: {} and {}", min, max);
        return audioStoryRepository.findByDurationSecondsBetween(min, max);
    }
}
