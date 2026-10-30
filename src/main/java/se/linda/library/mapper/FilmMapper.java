package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.FilmRequest;
import se.linda.library.dto.response.FilmResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.detail.Universe;
import se.linda.library.model.entity.Film;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.SeriesInfoRepository;
import se.linda.library.repository.UniverseRepository;

@Component
public class FilmMapper {

    private final SeriesInfoRepository seriesInfoRepository;
    private final UniverseRepository universeRepository;

    public FilmMapper(SeriesInfoRepository seriesInfoRepository, UniverseRepository universeRepository) {
        this.seriesInfoRepository = seriesInfoRepository;
        this.universeRepository = universeRepository;
    }

    public Film toEntity(FilmRequest request){
        SeriesInfo seriesInfo = null;
        if (request.seriesName() != null){
            seriesInfo = seriesInfoRepository.findById(request.seriesName())
                    .orElseGet(() -> seriesInfoRepository.save(new SeriesInfo(request.seriesName())));
        }

        Universe universe = null;
        if (request.universeName() != null){
            universe = universeRepository.findById(request.universeName())
                    .orElseGet(() -> universeRepository.save(new Universe(request.universeName())));
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
        film.setUniverse(universe);
        film.setSynopsis(request.synopsis());

        return film;
    }

    public FilmResponse toResponse(Film film){
        String seriesName = film.getSeriesInfo() != null
                ? film.getSeriesInfo().getSeriesName()
                : null;
        Integer seriesPartNumber = film.getPartNumber();

        String universe = film.getUniverse() != null
                ? film.getUniverse().getUniverseName()
                : null;

        return new FilmResponse(film.getId(), film.getTitle(),
                film.getGenre(), film.getLanguage(), seriesName, seriesPartNumber,
                film.getDirector(), film.getActors(),
                film.getMediaFormat(), universe, film.getTranslationInfo(), film.getSynopsis());
    }
}
