package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.AudioStory;

public interface AudioStoryRepository extends JpaRepository<AudioStory, Long> {
}
