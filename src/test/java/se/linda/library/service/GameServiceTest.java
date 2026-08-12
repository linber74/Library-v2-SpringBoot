package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Game;
import se.linda.library.repository.GameRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    Game testGame;
    Game testGame2;

    @BeforeEach
    public void setUp() {
        testGame = new Game();
        testGame.setId(1L);
        testGame.setTitle("The Witcher 3");
        testGame.setCreator("CD Projekt Red");

        testGame2 = new Game();
        testGame2.setId(2L);
        testGame2.setTitle("Skyrim");
        testGame2.setCreator("Bethesda");
    }

    @Test
    public void save_shouldReturnSaveGame() {
        when(gameRepository.save(any(Game.class)))
                .thenReturn(testGame);

        Game result = gameService.save(testGame);

        assertEquals(testGame.getTitle(), result.getTitle());
        verify(gameRepository).save(testGame);
    }

    @Test
    public void save_shouldThrowException_whenGameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> gameService.save(null));
    }

    @Test
    public void getById_shouldReturnGame_whenGameExists(){
        when(gameRepository.findById(1L))
            .thenReturn(Optional.of(testGame));

        Game result = gameService.getById(1L);
        assertEquals(testGame.getTitle(), result.getTitle());
    }

    @Test
    public void getById_shouldThrowException_whenGameNotFound(){
        when(gameRepository.findById(29L))
            .thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                () -> gameService.getById(29L));
    }

    @Test
    public void deleteById_shouldDeleteGame_whenGameExists(){
        when(gameRepository.existsById(1L))
            .thenReturn(true);

        gameService.deleteById(1L);
        verify(gameRepository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenGameNotFound(){
        when(gameRepository.existsById(99L))
        .thenReturn(false);

        assertThrows(ItemNotFoundException.class,
                () -> gameService.deleteById(99L));

        verify(gameRepository, never()).deleteById(any());
    }

    @Test
    public void searchByCreator_shouldReturnGames_whenCreatorIsValid(){
        when(gameRepository.findByCreator("CD Projekt Red"))
            .thenReturn(List.of(testGame));

        List<Game> result = gameService.searchByCreator("CD Projekt Red");

        assertEquals(1, result.size());
    }

    @Test
    public void searchByCreator_shouldThrowException_whenCreatorIsBlank(){
        assertThrows(IllegalArgumentException.class,
                () -> gameService.searchByCreator(""));
    }
}
