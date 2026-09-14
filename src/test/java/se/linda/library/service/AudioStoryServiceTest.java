package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.AudioStory;
import se.linda.library.repository.AudioStoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AudioStoryServiceTest {

    @Mock
    private AudioStoryRepository repository;

    @InjectMocks
    private AudioStoryService service;

    private AudioStory testAudioStory;
    private AudioStory testAudioStory2;

    @BeforeEach
    public void setup() {
        testAudioStory = new AudioStory();
        testAudioStory.setId(1L);
        testAudioStory.setTitle("Whispers in the Dark");
        testAudioStory.setTropeGenre(List.of("Slow Burn", "Enemies to Lovers"));
        testAudioStory.setDurationSeconds(1800);

        testAudioStory2 = new AudioStory();
        testAudioStory2.setId(2L);
        testAudioStory2.setTitle("The Last Signal");
        testAudioStory2.setTropeGenre(List.of("Found Family"));
        testAudioStory2.setDurationSeconds(5400);
    }

    @Test
    public void save_ShouldReturnSavedAudioStory() {
        when(repository.save(any(AudioStory.class))).thenReturn(testAudioStory);

        AudioStory result = service.save(testAudioStory);

        assertEquals(testAudioStory.getTitle(), result.getTitle());
        verify(repository).save(testAudioStory);
    }

    @Test
    public void save_shouldThrowException_whenAudioStoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.save(null));
    }

    @Test
    public void getAudioStoryById_shouldReturnAudioStory_whenAudioStoryExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(testAudioStory));
        AudioStory result = service.getById(1L);
        assertEquals(testAudioStory.getTitle(), result.getTitle());
    }

    @Test
    public void getAudioStoryById_shouldThrowException_whenAudioStoryNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    public void deleteById_shouldDeleteAudioStory_whenAudioStoryExists() {
        when(repository.existsById(1L)).thenReturn(true);
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenAudioStoryNotFound() {
        when(repository.existsById(99L)).thenReturn(false);
        assertThrows(ItemNotFoundException.class, () -> service.deleteById(99L));
        verify(repository, never()).deleteById(any());
    }

    @Test
    public void searchByDuration_shouldReturnAudioStories_whenRangeIsValid() {
        when(repository.findByDurationSecondsBetween(500, 700))
                .thenReturn(List.of(testAudioStory));
        List<AudioStory> result = service.searchByDuration(500, 700);
        assertEquals(1, result.size());
    }

    @Test
    public void searchByDuration_shouldThrowException_whenMinOrMaxIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                service.searchByDuration(null, 700));
    }

    @Test
    public void searchByDuration_shouldThrowException_whenDurationIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                service.searchByDuration(500, -1));
    }

    @Test
    public void searchByDuration_shouldThrowException_whenMinIsGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () ->
                service.searchByDuration(1000, 500));
    }
}
