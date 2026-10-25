package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.linda.library.model.detail.SeriesInfo;

import java.util.List;

public interface SeriesInfoRepository extends JpaRepository<SeriesInfo, String> {
    @Query(value = "SELECT DISTINCT series_name FROM seriesinfo ORDER BY series_name", nativeQuery = true)
    List<String> findDistinctSeriesNames();
}
