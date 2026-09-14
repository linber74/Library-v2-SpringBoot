package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.ShortDrama;

import java.util.List;

public interface ShortDramaRepository extends JpaRepository<ShortDrama, Long> {
    List<ShortDrama> findByDurationSecondsBetween(Integer min, Integer max);
}
