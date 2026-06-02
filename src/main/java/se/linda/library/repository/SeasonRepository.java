package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findBySeasonNumber (int seasonNumber);
    List<Season> findByTvSeries (TVSeries tvSeries);
}
