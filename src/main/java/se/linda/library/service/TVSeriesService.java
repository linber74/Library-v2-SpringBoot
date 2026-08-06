package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;
import se.linda.library.repository.SeasonRepository;
import se.linda.library.repository.TVSeriesRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TVSeriesService {

    final TVSeriesRepository tvSeriesRepository;
    final SeasonRepository seasonRepository;

    public TVSeriesService(TVSeriesRepository tvSeriesRepository,
                           SeasonRepository seasonRepository) {

        this.tvSeriesRepository = tvSeriesRepository;
        this.seasonRepository = seasonRepository;
    }

    public TVSeries save(TVSeries tvSeries) {
        if (tvSeries == null) {

            log.warn("TvSeries can't be null!");
            throw new IllegalArgumentException("TvSeries can't be null!");
        }
        log.info("Saved tv-series: {}", tvSeries);
        return tvSeriesRepository.save(tvSeries);
    }

    public List<TVSeries> getAll() {
        return tvSeriesRepository.findAll();
    }

    public TVSeries getById(Long id) {

        return tvSeriesRepository.findById(id).orElseThrow(()

            ->{ log.info("Tv-series not found with id: {}", id);
                return new ItemNotFoundException("TvSeries with id: " + id + " not found!");});
    }

    public void deleteById (Long id){

        if(!tvSeriesRepository.existsById(id)){

            log.warn("Tv-series not found with id: {}", id);
            throw new ItemNotFoundException("TvSeries with id: " + id + " not found");
        }
        log.info("Deleted tv-series with id: {}", id);
        tvSeriesRepository.deleteById(id);
    }

    public Season addSeason (Long tvSeriesId, Season season) {

        TVSeries tvSeries = getById(tvSeriesId);

        season.setTvSeries(tvSeries);
        log.info("Saved season: {}", season);
        return seasonRepository.save(season);
    }

    public List<Season> searchBySeasonNumber (int number){

        if (number < 0){

            log.warn("Seasonnumber can't be 0 or less!");
            throw new IllegalArgumentException("Seasonnumber can't be 0 or less!");
        }
        log.info("Found by seasonnr: {}", number);
        return seasonRepository.findBySeasonNumber(number);
    }

    public List<Season> searchByTvSeries (TVSeries tvSeries){

        if (tvSeries == null){

            log.warn("TvSeries can't be null!");
            throw new IllegalArgumentException("TvSeries can't be null!");
        }
        log.info("Found By tv-series: {}", tvSeries);
        return seasonRepository.findByTvSeries(tvSeries);
    }
}
