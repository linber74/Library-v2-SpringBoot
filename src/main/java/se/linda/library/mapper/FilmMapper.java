package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.FilmRequest;
import se.linda.library.dto.response.FilmResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.Film;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.SeriesInfoRepository;

@Component
public class FilmMapper {

    private final SeriesInfoRepository seriesInfoRepository;

    public FilmMapper(SeriesInfoRepository seriesInfoRepository) {
        this.seriesInfoRepository = seriesInfoRepository;
    }

    public Film toEntity(FilmRequest request){
        SeriesInfo seriesInfo = null;
        if (request.seriesName() != null){
            seriesInfo = seriesInfoRepository.findById(request.seriesName())
                    .orElseGet(() -> seriesInfoRepository.save(new SeriesInfo(request.seriesName())));
        }

        Film film = new Film();
        film.setTitle(request.title());
        film.setGenre(request.genre());
        film.setLanguage(request.language());
        film.setSeriesInfo(seriesInfo);
        film.setPartNumber(request.seriesPartNumber());
        film.setDirector(request.director());
        film.setActors(request.actors());
        film.setMediaFormat(request.mediaFormat());
        film.setTranslationInfo(request.translationInfo());
        film.setItemType(ItemType.FILM);
        film.setSynopsis(request.synopsis());

        return film;
    }

    public FilmResponse toResponse(Film film){
        String seriesName = film.getSeriesInfo() != null
                ? film.getSeriesInfo().getSeriesName()
                : null;
        Integer seriesPartNumber = film.getPartNumber();

        return new FilmResponse(film.getId(), film.getTitle(),
                film.getGenre(), film.getLanguage(), seriesName, seriesPartNumber,
                film.getDirector(), film.getActors(),
                film.getMediaFormat(), film.getTranslationInfo(), film.getSynopsis());
    }
}
