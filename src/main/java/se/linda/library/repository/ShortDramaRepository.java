package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.ShortDrama;

public interface ShortDramaRepository extends JpaRepository<ShortDrama, Long> {
}
