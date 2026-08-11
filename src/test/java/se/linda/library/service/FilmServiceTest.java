package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Film;
import se.linda.library.model.entity.VisualMedia;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;
import se.linda.library.repository.FilmRepository;
import se.linda.library.repository.VisualMediaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private VisualMediaRepository visualMediaRepository;

    @InjectMocks
    private FilmService filmService;

    Film testFilm;
    Film testFilm2;
    Film testFilm3;
    List<Film> testAllFilms;

    @BeforeEach
    public void setUp() {
        testFilm = new Film();
        testFilm.setId(1L);
        testFilm.setTitle("Inception");
        testFilm.setDirector("Christopher Nolan");
        testFilm.setActors(List.of("Leonardo DiCaprio"));
        testFilm.setMediaFormat(MediaFormat.DIGITAL);

        testFilm2 = new Film();
        testFilm2.setId(2L);
        testFilm2.setTitle("The Matrix");
        testFilm2.setDirector("Lana Wachowski");
        testFilm2.setActors(List.of("Keanu Reeves"));
        testFilm2.setMediaFormat(MediaFormat.BLURAY);

        testFilm3 = new Film();
        testFilm3.setId(3L);
        testFilm3.setTitle("Amélie");
        testFilm3.setDirector("Jean-Pierre Jeunet");
        testFilm3.setActors(List.of("Audrey Tautou"));
        testFilm3.setMediaFormat(MediaFormat.DVD);
        testFilm3.setTranslationInfo(TranslationInfo.ENGLISH);

        testAllFilms = List.of(testFilm, testFilm2, testFilm3);
    }

    @Test
    public void save_shouldSavedFilm() {
        when(filmRepository.save(any(Film.class)))
                .thenReturn(testFilm);

        Film result = filmService.save(testFilm);

        assertEquals(testFilm.getTitle(), result.getTitle());
        verify(filmRepository).save(testFilm);
    }

    @Test
    public void save_shouldThrowException_whenFilmIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> filmService.save(null));
    }

    @Test
    public void getById_shouldReturnFilm_whenFilmExists() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(testFilm));

        Film result = filmService.getById(1L);
        assertEquals(testFilm.getTitle(), result.getTitle());
    }

    @Test
    public void getById_shouldThrowException_whenFilmNotFound(){
        when(filmRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                () -> filmService.getById(99L));
    }

    @Test
    public void deleteById_shouldDeleteFilm_whenFilmExists(){
        when(filmRepository.existsById(1L)).thenReturn(true);

        filmService.deleteById(1L);
        verify(filmRepository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenFilmNotFound() {
        when(filmRepository.existsById(99L)).thenReturn(false);

        assertThrows(ItemNotFoundException.class,
                () -> filmService.deleteById(99L));
        verify(filmRepository, never()).deleteById(any());
    }

    @Test
    public void searchByDirector_shouldReturnFilms_whenDirectorIsValid(){
        when(visualMediaRepository.findByDirectorContainingIgnoreCase("Christopher Nolan"))
            .thenReturn(List.of(testFilm));

        List<VisualMedia> result = filmService.searchByDirector("Christopher Nolan");

        assertEquals(1, result.size());
    }

    @Test
    public void searchByDirector_shouldThrowException_whenDirectorIsBlank(){
        assertThrows(IllegalArgumentException.class,
                () -> filmService.searchByDirector(""));
    }

    @Test
    public void searchByActors_shouldReturnFilms_whenActorsIsValid(){
        when(visualMediaRepository.findByActorsContainingIgnoreCase("Keanu Reeves"))
        .thenReturn(List.of(testFilm2));

        List<VisualMedia> result = filmService.searchByActor("Keanu Reeves");

        assertEquals(1, result.size());
    }

    @Test
    public void searchByActors_shouldThrowException_whenActorsIsBlank(){
        assertThrows(IllegalArgumentException.class,
                () -> filmService.searchByActor(""));
    }

    @Test
    public void searchByMediaFormat_shouldReturnFilms_whenMediaFormatIsValid(){
        when(visualMediaRepository.findByMediaFormat(MediaFormat.DIGITAL))
            .thenReturn(List.of(testFilm));

        List<VisualMedia> result = filmService.searchByMediaFormat(MediaFormat.DIGITAL);
        assertEquals(1, result.size());
    }

    @Test
    public void searchByMediaFormat_shouldThrowException_whenMediaFormatIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> filmService.searchByMediaFormat(null));
    }

    @Test
    public void searchByTranslationInfo_shouldReturnFilms_whenTranslationInfoIsValid(){
        when(visualMediaRepository.findByTranslationInfo(TranslationInfo.ENGLISH))
            .thenReturn(List.of(testFilm));

        List<VisualMedia> result = filmService.searchByTranslationsInfo(TranslationInfo.ENGLISH);
        assertEquals(1, result.size());
    }

    @Test
    public void searchByTranslationInfo_shouldThrowException_whenTranslationInfoIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> filmService.searchByTranslationsInfo(null));
    }
}
