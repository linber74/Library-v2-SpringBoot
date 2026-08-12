package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.Book;
import se.linda.library.model.entity.Game;
import se.linda.library.model.entity.LibraryItem;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.LibraryItemRepository;
import se.linda.library.repository.SeasonRepository;
import se.linda.library.repository.SeriesInfoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private BookService bookService;

    @Mock
    private FilmService filmService;

    @Mock
    private TVSeriesService tvSeriesService;

    @Mock
    private GameService gameService;

    @Mock
    private LibraryItemRepository libraryItemRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private SeriesInfoRepository seriesInfoRepository;

    @InjectMocks
    private LibraryService libraryService;

    LibraryItem testBook;
    LibraryItem testGame;
    List<LibraryItem> testAllItems;
    SeriesInfo testSeriesInfo;

    @BeforeEach
    public void setUp() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("The Hobbit");
        book.setLanguage("English");
        book.setItemType(ItemType.BOOK);

        testBook = book;

        Game game = new Game();
        game.setId(2L);
        game.setTitle("Skyrim");
        game.setLanguage("English");
        game.setItemType(ItemType.GAME);
        game.setPublishYear(2011);
        testGame = game;

        testAllItems = List.of(testBook, testGame);

        testSeriesInfo = new SeriesInfo();
        testSeriesInfo.setSeriesName("Middle Earth");
    }

    @Test
    public void getAll_shouldReturnAllItems(){
        when(libraryItemRepository.findAll()).thenReturn(testAllItems);

        List<LibraryItem> result = libraryService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void getById_shouldReturnItem_whenItemExists(){
        when(libraryItemRepository.findById(1L))
                .thenReturn(Optional.of(testBook));

        LibraryItem result = libraryService.getById(1L);

        assertEquals(testBook.getTitle(), result.getTitle());
    }

    @Test
    public void getById_shouldThrowException_whenItemNotFound(){
        when(libraryItemRepository.findById(99L))
            .thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                () -> libraryService.getById(99L));
    }

    @Test
    public void getAllByType_shouldReturnItems_whenTypeIsValid(){
        when(libraryItemRepository.findByItemType(ItemType.BOOK))
        .thenReturn(List.of(testBook));

        List<LibraryItem> result = libraryService.getAllByType(ItemType.BOOK);
        assertEquals(1, result.size());
    }

    @Test
    public void getAllByType_shouldThrowException_whenTypeIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> libraryService.getAllByType(null));
    }

    @Test
    public void getAllByLanguage_shouldReturnItems_whenLanguageIsValid(){
        when(libraryItemRepository.findByLanguage("English"))
                .thenReturn(testAllItems);

        List<LibraryItem> result = libraryService.getAllByLanguage("English");
        assertEquals(2, result.size());
    }

    @Test
    public void getAllByLanguage_shouldThrowException_whenLanguageIsBlank(){
        assertThrows(IllegalArgumentException.class,
                () -> libraryService.getAllByLanguage(""));
    }

    @Test
    public void getAllByTitle_shouldReturnItems_whenTitleIsValid(){
        when(libraryItemRepository.findByTitleContainingIgnoreCase("The Hobbit"))
                .thenReturn(List.of(testGame));

        List<LibraryItem> result = libraryService.getAllByTitle("The Hobbit");
        assertEquals(1, result.size());
    }

    @Test
    public void getAllByTitle_shouldThrowException_whenTitleIsBlank(){
        assertThrows(IllegalArgumentException.class,
                () -> libraryService.getAllByTitle(""));
    }

    @Test
    public void getAllByPublishYear_shouldReturnItems_whenYearIsValid(){
        when(libraryItemRepository.findByPublishYear(2011))
                .thenReturn(List.of(testBook));

        List<LibraryItem> result = libraryService.getAllByPublishYear(2011);
        assertEquals(1, result.size());
    }

    @Test
    public void getAllByPublishYear_shouldThrowException_whenYearIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> libraryService.getAllByPublishYear(null));
    }

    @Test
    public void deleteById_shouldDeleteItem_whenItemExists(){
        when(libraryItemRepository.existsById(1L))
            .thenReturn(true);

        libraryService.deleteById(1L);
        verify(libraryItemRepository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenItemNotFound(){
        when(libraryItemRepository.existsById(4L))
            .thenReturn(false);

        assertThrows(ItemNotFoundException.class,
                () -> libraryService.deleteById(4L));
        verify(libraryItemRepository, never()).deleteById(any());
    }

    @Test
    public void getOrCreateSeriesInfo_shouldReturnExisting_whenSeriesInfoAlreadyExists(){
        when(seriesInfoRepository.findById("Middle Earth"))
            .thenReturn(Optional.of(testSeriesInfo));

        SeriesInfo result = libraryService.getOrCreateSeriesInfo("Middle Earth", 1);

        assertEquals(testSeriesInfo, result);
        verify(seriesInfoRepository, never()).save(any());
    }

    @Test
    public void getOrCreateSeriesInfo_shouldCreateNew_whenSeriesInfoDoesNotExist(){
        when(seriesInfoRepository.findById("The Elder Scroll"))
            .thenReturn(Optional.empty());
        when(seriesInfoRepository.save(any(SeriesInfo.class)))
                .thenReturn(new SeriesInfo("The Elder Scroll", 5));

        SeriesInfo result = libraryService.getOrCreateSeriesInfo("The Elder Scroll", 5);

        assertEquals("The Elder Scroll", result.getSeriesName());
        verify(seriesInfoRepository).save(any(SeriesInfo.class));
    }

    @Test
    public void getAllSeriesInfo_shouldReturnAllSeriesInfo(){
        when(seriesInfoRepository.findAll())
        .thenReturn(List.of(testSeriesInfo));
        List<SeriesInfo> result = libraryService.getAllSeriesInfo();
        assertEquals(1, result.size());
    }
}
