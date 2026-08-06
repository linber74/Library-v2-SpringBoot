package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.GameRequest;
import se.linda.library.dto.response.GameResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.Game;
import se.linda.library.model.enums.ItemType;

@Component
public class GameMapper {

    public Game toEntity(GameRequest request) {
        SeriesInfo seriesInfo = null;
        if(request.seriesName() != null) {
            seriesInfo = new SeriesInfo();
            seriesInfo.setSeriesName(request.seriesName());
        }

        Game game = new Game();
        game.setTitle(request.title());
        game.setGenre(request.genre());
        game.setLanguage(request.language());
        game.setSeriesInfo(seriesInfo);
        game.setCreator(request.creator());
        game.setItemType(ItemType.GAME);

        return game;
    }

    public GameResponse toResponse(Game game) {
        String seriesName = game.getSeriesInfo()  != null
                ? game.getSeriesInfo().getSeriesName()
                : null;

        return new GameResponse(game.getId(), game.getTitle(),
                game.getGenre(), game.getLanguage(),
                seriesName, game.getCreator());
    }
}
