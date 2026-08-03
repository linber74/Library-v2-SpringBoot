package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;
import se.linda.library.repository.SeasonRepository;
import se.linda.library.repository.TVSeriesRepository;

import java.util.List;

@Service
@Transactional
public class TvSeriesService {

    final TVSeriesRepository tvSeriesRepository;
    final SeasonRepository seasonRepository;

    public TvSeriesService(TVSeriesRepository tvSeriesRepository,
                           SeasonRepository seasonRepository) {

        this.tvSeriesRepository = tvSeriesRepository;
        this.seasonRepository = seasonRepository;
    }

    public TVSeries save(TVSeries tvSeries) {
        if (tvSeries == null) {

            // Todo Change exceptions later
            throw new ItemNotFoundException("TvSeries can't be null!");
        }
        return tvSeriesRepository.save(tvSeries);
    }

    public List<TVSeries> getAll() {
        return tvSeriesRepository.findAll();
    }

    public TVSeries getById(Long id) {

        return tvSeriesRepository.findById(id).orElseThrow(()

                // Todo Change exceptions later
            -> new ItemNotFoundException("TvSeries with id: " + id + " not found!"));
    }

    public void deleteById (Long id){

        if(!tvSeriesRepository.existsById(id)){

            // Todo Change exceptions later
            throw new IllegalArgumentException("TvSeries with id: " + id + " not found");
        }
        tvSeriesRepository.deleteById(id);
    }

    public Season addSeason (Long tvSeriesId, Season season) {

        TVSeries tvSeries = getById(tvSeriesId);

        season.setTvSeries(tvSeries);

        return seasonRepository.save(season);
    }

    public List<Season> searchBySeasonNumber (int number){

        if (number < 0){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Seasonnumber can't be 0 or less!");
        }
        return seasonRepository.findBySeasonNumber(number);
    }

    public List<Season> searchByTvSeries (TVSeries tvSeries){

        if (tvSeries == null){

            // Todo Change exceptions later
            throw new IllegalArgumentException("TvSeries can't be null!");
        }
        return seasonRepository.findByTvSeries(tvSeries);
    }
}
