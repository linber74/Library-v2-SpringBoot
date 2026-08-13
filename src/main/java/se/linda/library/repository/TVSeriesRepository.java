package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.TVSeries;

public interface TVSeriesRepository extends JpaRepository<TVSeries, Long> {
}
