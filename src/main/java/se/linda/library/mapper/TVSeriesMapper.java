package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.TVSeriesRequest;
import se.linda.library.dto.response.TVSeriesResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.detail.Universe;
import se.linda.library.model.entity.TVSeries;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.SeriesInfoRepository;
import se.linda.library.repository.UniverseRepository;

@Component
public class TVSeriesMapper {

    private final SeriesInfoRepository seriesInfoRepository;
    private final UniverseRepository universeRepository;

    public TVSeriesMapper(SeriesInfoRepository seriesInfoRepository, UniverseRepository universeRepository) {
        this.seriesInfoRepository = seriesInfoRepository;
        this.universeRepository = universeRepository;
    }


    public TVSeries toEntity (TVSeriesRequest request) {
        SeriesInfo seriesInfo = null;
        if (request.seriesName() != null) {
            seriesInfo = seriesInfoRepository.findById(request.seriesName())
                    .orElseGet(() -> seriesInfoRepository.save(new SeriesInfo(request.seriesName())));
        }

        Universe universe = null;
        if (request.universeName() != null){
            universe = universeRepository.findById(request.universeName())
                    .orElseGet(() -> universeRepository.save(new Universe(request.universeName())));
        }

        TVSeries tvSeries = new TVSeries();
        tvSeries.setTitle(request.title());
        tvSeries.setGenre(request.genre());
        tvSeries.setLanguage(request.language());
        tvSeries.setSeriesInfo(seriesInfo);
        tvSeries.setPartNumber(request.seriesPartNumber());
        tvSeries.setDirector(request.director());
        tvSeries.setActors(request.actors());
        tvSeries.setMediaFormat(request.mediaFormat());
        tvSeries.setTranslationInfo(request.translationInfo());
        tvSeries.setUniverse(universe);
        tvSeries.setItemType(ItemType.TV_SERIES);
        tvSeries.setSynopsis(request.synopsis());

        return tvSeries;
    }

    public TVSeriesResponse toResponse (TVSeries tvSeries) {
        String seriesName = tvSeries.getSeriesInfo() != null
                ? tvSeries.getSeriesInfo().getSeriesName()
                : null;
        Integer seriesPartNumber = tvSeries.getPartNumber();

        String universe = tvSeries.getUniverse() != null
                ? tvSeries.getUniverse().getUniverseName()
                : null;
        return new TVSeriesResponse(tvSeries.getId(), tvSeries.getTitle(),
                tvSeries.getGenre(), tvSeries.getLanguage(), seriesName, seriesPartNumber,
                tvSeries.getDirector(), tvSeries.getActors(),
                tvSeries.getMediaFormat(), universe, tvSeries.getTranslationInfo(), tvSeries.getSynopsis());
    }
}
