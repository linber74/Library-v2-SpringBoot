package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.FilmRequest;
import se.linda.library.dto.response.FilmResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.Film;
import se.linda.library.model.enums.ItemType;

@Component
public class FilmMapper {

    public Film toEntity(FilmRequest request){
        SeriesInfo seriesInfo = null;
        if (request.seriesName() != null){
            seriesInfo = new SeriesInfo();
            seriesInfo.setSeriesName(request.seriesName());
        }

        Film film = new Film();
        film.setTitle(request.title());
        film.setGenre(request.genre());
        film.setLanguage(request.language());
        film.setSeriesInfo(seriesInfo);
        film.setDirector(request.director());
        film.setActors(request.actors());
        film.setMediaFormat(request.mediaFormat());
        film.setTranslationInfo(request.translationInfo());
        film.setItemType(ItemType.FILM);

        return film;
    }

    public FilmResponse toResponse(Film film){
        String seriesName = film.getSeriesInfo() != null
                ? film.getSeriesInfo().getSeriesName()
                : null;

        return new FilmResponse(film.getId(), film.getTitle(),
                film.getGenre(), film.getLanguage(), seriesName,
                film.getDirector(), film.getActors(),
                film.getMediaFormat(), film.getTranslationInfo());
    }
}
