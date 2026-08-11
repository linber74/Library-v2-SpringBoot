package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.repository.SeasonRepository;
import se.linda.library.repository.TVSeriesRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TVSeriesServiceTest {

    @Mock
    private TVSeriesRepository tvSeriesRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private TVSeriesService tvSeriesService;

    TVSeries testTvSeries;
    TVSeries testTvSeries2;
    Season testSeason;
    List<TVSeries> testAllTvSeries;

    @BeforeEach
    public void setUp() {
        testTvSeries = new TVSeries();
        testTvSeries.setId(1L);
        testTvSeries.setTitle("Breaking Bad");
        testTvSeries.setDirector("Vince Gilligan");
        testTvSeries.setActors(List.of("Bryan Cranston"));
        testTvSeries.setMediaFormat(MediaFormat.DIGITAL);

        testTvSeries2 = new TVSeries();
        testTvSeries2.setId(2L);
        testTvSeries2.setTitle("Stranger Things");
        testTvSeries2.setDirector("The Duffer Brothers");
        testTvSeries2.setActors(List.of("Millie Bobby Brown"));
        testTvSeries2.setMediaFormat(MediaFormat.DIGITAL);

        testSeason = new Season();
        testSeason.setId(1L);
        testSeason.setSeasonNumber(1);

        testAllTvSeries = List.of(testTvSeries, testTvSeries2);
    }

    @Test
    public void save_shouldReturnSavedTvSeries(){
        when(tvSeriesRepository.save(any(TVSeries.class)))
                .thenReturn(testTvSeries);

        TVSeries result = tvSeriesService.save(testTvSeries);

        assertEquals(testTvSeries.getTitle(), result.getTitle());
        verify(tvSeriesRepository).save(testTvSeries);
    }

    @Test
    public void save_shouldThrowException_whenTvSeriesIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> tvSeriesService.save(null));
    }

    @Test
    public void getById_shouldReturnTVSeries_whenTvSeriesIsExists(){
        when(tvSeriesRepository.findById(1L)).thenReturn(Optional.of(testTvSeries));

        TVSeries result = tvSeriesService.getById(1L);
        assertEquals(testTvSeries.getTitle(), result.getTitle());
    }

    @Test
    public void getById_shouldThrowException_whenTvSeriesNotFound(){
        when(tvSeriesRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                () -> tvSeriesService.getById(99L));
    }

    @Test
    public void deleteById_shouldDeleteTVSeries_whenTvSeriesIsExists(){
        when(tvSeriesRepository.existsById(1L)).thenReturn(true);

        tvSeriesService.deleteById(1L);
        verify(tvSeriesRepository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenTvSeriesNotFound(){
        when(tvSeriesRepository.existsById(99L)).thenReturn(false);

        assertThrows(ItemNotFoundException.class,
                () -> tvSeriesService.deleteById(99L));

        verify(tvSeriesRepository, never()).deleteById(any());
    }

    @Test
    public void addSeason_shouldAddSeasonToExistingTVSeries(){
        when(tvSeriesRepository.findById(1L)).thenReturn(Optional.of(testTvSeries));
        when(seasonRepository.save(any(Season.class))).thenReturn(testSeason);

        Season result = tvSeriesService.addSeason(1L, testSeason);

        assertEquals(testSeason, result);
        assertEquals(testTvSeries, testSeason.getTvSeries());
        verify(seasonRepository).save(testSeason);
    }

    @Test
    public void searchBySeasonNumber_shouldReturnTVSeries_whenSeasonNumberIsValid(){
        when(seasonRepository.findBySeasonNumber(1))
            .thenReturn(List.of(testSeason));

        List<Season> result = tvSeriesService.searchBySeasonNumber(1);

        assertEquals(1, result.size());
    }

    @Test
    public void searchBySeasonNumber_shouldThrowException_whenSeasonNumberIsInvalid(){
        assertThrows(IllegalArgumentException.class,
                () -> tvSeriesService.searchBySeasonNumber(-99));
    }

    @Test
    public void searchByTvSeries_shouldReturnTVSeries_whenTvSeriesIsValid(){
        when(seasonRepository.findByTvSeries(testTvSeries))
            .thenReturn(List.of(testSeason));

        List<Season> result = tvSeriesService.searchByTvSeries(testTvSeries);
        assertEquals(1, result.size());
    }

    @Test
    public void searchByTvSeries_shouldThrowException_whenTvSeriesIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> tvSeriesService.searchByTvSeries(null));
    }
}
