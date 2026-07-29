package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.model.entity.Game;
import se.linda.library.repository.GameRepository;

import java.util.List;

@Service
@Transactional
public class GameService {

    final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game save (Game game){
        if (game == null){

            // Todo Change exceptions later
            throw new RuntimeException("Game can't be null!");
        }
        return gameRepository.save(game);
    }

    public List<Game> getAll (){
        return gameRepository.findAll();
    }

    public Game getById (Long id){

        return gameRepository.findById(id).orElseThrow(()

                // Todo Change exceptions later
        -> new RuntimeException("Game with id: " + id + " not found!"));
    }

    public void deleteById (Long id){

        if(!gameRepository.existsById(id)){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Game with id: " + id + " not found!");
        }
        gameRepository.deleteById(id);
    }

    public List<Game> searchByCreator (String creator){

        if (creator == null){
            throw new IllegalArgumentException("Creator can't be found!");
        }
        return gameRepository.findByCreator(creator);
    }
}
