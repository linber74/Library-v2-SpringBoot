package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.GameRequest;
import se.linda.library.dto.response.GameResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.detail.Universe;
import se.linda.library.model.entity.Game;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.SeriesInfoRepository;
import se.linda.library.repository.UniverseRepository;

@Component
public class GameMapper {

    private final SeriesInfoRepository seriesInfoRepository;
    private final UniverseRepository universeRepository;

    public GameMapper(SeriesInfoRepository seriesInfoRepository, UniverseRepository universeRepository) {
        this.seriesInfoRepository = seriesInfoRepository;
        this.universeRepository = universeRepository;
    }


    public Game toEntity(GameRequest request) {
        SeriesInfo seriesInfo = null;
        if(request.seriesName() != null) {
            seriesInfo = seriesInfoRepository.findById(request.seriesName())
                    .orElseGet(() -> seriesInfoRepository.save(new SeriesInfo(request.seriesName())));
        }

        Universe universe = null;
        if (request.universeName() != null){
            universe = universeRepository.findById(request.universeName())
                    .orElseGet(() -> universeRepository.save(new Universe(request.universeName())));
        }

        Game game = new Game();
        game.setTitle(request.title());
        game.setGenre(request.genre());
        game.setLanguage(request.language());
        game.setSeriesInfo(seriesInfo);
        game.setPartNumber(request.seriesPartNumber());
        game.setCreator(request.creator());
        game.setItemType(ItemType.GAME);
        game.setUniverse(universe);
        game.setSynopsis(request.synopsis());

        return game;
    }

    public GameResponse toResponse(Game game) {
        String seriesName = game.getSeriesInfo()  != null
                ? game.getSeriesInfo().getSeriesName()
                : null;
        Integer seriesPartNumber = game.getPartNumber();

        String universe = game.getUniverse() != null
                ? game.getUniverse().getUniverseName()
                : null;

        return new GameResponse(game.getId(), game.getTitle(),
                game.getGenre(), game.getLanguage(),
                seriesName, seriesPartNumber, game.getCreator(), universe, game.getSynopsis());
    }
}
