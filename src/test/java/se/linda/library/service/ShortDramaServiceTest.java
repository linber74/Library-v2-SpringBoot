package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.ShortDrama;
import se.linda.library.repository.ShortDramaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShortDramaServiceTest {

    @Mock
    private ShortDramaRepository repository;

    @InjectMocks
    private ShortDramaService service;

    private ShortDrama testShortDrama;
    private ShortDrama testShortDrama2;

    @BeforeEach
    public void setup() {
        testShortDrama = new ShortDrama();
        testShortDrama.setId(1L);
        testShortDrama.setTitle("Revenge of the CEO");
        testShortDrama.setTropeGenre(List.of("Revenge", "CEO Romance"));
        testShortDrama.setDurationSeconds(600);

        testShortDrama2 = new ShortDrama();
        testShortDrama2.setId(2L);
        testShortDrama2.setTitle("Amnesia Love");
        testShortDrama2.setTropeGenre(List.of("Amnesia Plot Twist"));
        testShortDrama2.setDurationSeconds(3600);
    }

    @Test
    public void save_ShouldReturnSavedShortDrama() {
        when(repository.save(any(ShortDrama.class))).thenReturn(testShortDrama);

        ShortDrama result = service.save(testShortDrama);

        assertEquals(testShortDrama.getTitle(), result.getTitle());
        verify(repository).save(testShortDrama);
    }

    @Test
    public void save_shouldThrowException_whenShortDramaIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.save(null));
    }

    @Test
    public void getShortDramaById_shouldReturnShortDrama_whenShortDramaExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(testShortDrama));
        ShortDrama result = service.getById(1L);
        assertEquals(testShortDrama.getTitle(), result.getTitle());
    }

    @Test
    public void getShortDramaById_shouldThrowException_whenShortDramaNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    public void deleteById_shouldDeleteShortDrama_whenShortDramaExists() {
        when(repository.existsById(1L)).thenReturn(true);
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenShortDramaNotFound() {
        when(repository.existsById(99L)).thenReturn(false);
        assertThrows(ItemNotFoundException.class, () -> service.deleteById(99L));
        verify(repository, never()).deleteById(any());
    }

    @Test
    public void searchByDuration_shouldReturnShortDramas_whenRangeIsValid() {
        when(repository.findByDurationSecondsBetween(500, 700))
                .thenReturn(List.of(testShortDrama));
        List<ShortDrama> result = service.searchByDuration(500, 700);
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
