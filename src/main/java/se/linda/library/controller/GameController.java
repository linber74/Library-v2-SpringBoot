package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.model.entity.Game;
import se.linda.library.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAll (){
        return gameService.getAll();
    }

    @GetMapping("/{id}")
    public Game getById (@PathVariable Long id){
        return gameService.getById(id);
    }

    @PostMapping
    public Game save (@RequestBody Game game){
        return gameService.save(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/creator")
    public List<Game> searchByCreator(@RequestParam String creator){
        return gameService.searchByCreator(creator);
    }
}
