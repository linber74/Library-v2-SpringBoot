package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.AudioStory;

import java.util.List;

public interface AudioStoryRepository extends JpaRepository<AudioStory, Long> {
    List<AudioStory> findByDurationSecondsBetween(Integer min, Integer max);
}
