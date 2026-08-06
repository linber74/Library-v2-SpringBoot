package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.request.GameRequest;
import se.linda.library.dto.response.GameResponse;
import se.linda.library.mapper.GameMapper;
import se.linda.library.model.entity.Game;
import se.linda.library.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    final GameService gameService;
    final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping
    public List<GameResponse> getAll (){
        return gameService.getAll()
                .stream()
                .map(gameMapper :: toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public GameResponse getById (@PathVariable Long id){
        Game game = gameService.getById(id);
        return gameMapper.toResponse(game);
    }

    @PostMapping
    public GameResponse save (@RequestBody GameRequest request){
        Game game = gameMapper.toEntity(request);
        Game saved = gameService.save(game);
        return gameMapper.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/creator")
    public List<GameResponse> searchByCreator(@RequestParam String creator){
        return gameService.searchByCreator(creator)
                .stream()
                .map(gameMapper :: toResponse)
                .toList();
    }
}
