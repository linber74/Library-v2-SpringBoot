package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.detail.SeriesInfo;

public interface SeriesInfoRepository extends JpaRepository<SeriesInfo, Long> {
}
