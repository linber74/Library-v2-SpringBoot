package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Game;
import se.linda.library.repository.GameRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
public class GameService {

    final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game save (Game game){
        if (game == null){

            log.warn("Game can't be null!");
            throw new ItemNotFoundException("Game can't be null!");
        }
        log.info("Saved Game: {}", game);
        return gameRepository.save(game);
    }

    public List<Game> getAll (){
        return gameRepository.findAll();
    }

    public Game getById (Long id){

        return gameRepository.findById(id).orElseThrow(()

        -> { log.warn("Game not found With íd: {}", id);
        return new ItemNotFoundException("Game with id: " + id + " not found!");});
    }

    public void deleteById (Long id){

        if(!gameRepository.existsById(id)){

            log.warn("Game not found with id: {}", id);
            // Todo Change exceptions later
            throw new IllegalArgumentException("Game with id: " + id + " not found!");
        }
        log.info("Deleted Game with id: {}",id);
        gameRepository.deleteById(id);
    }

    public List<Game> searchByCreator (String creator){

        if (creator == null){
            log.warn("Creator can't be found!");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Creator can't be found!");
        }
        log.info("Getting creator: {}", creator);
        return gameRepository.findByCreator(creator);
    }
}
