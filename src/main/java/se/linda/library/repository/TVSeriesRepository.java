package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;

import java.util.List;

public interface TVSeriesRepository extends JpaRepository<TVSeries, Long> {

   List<TVSeries> findBySeason (Season season);
}
