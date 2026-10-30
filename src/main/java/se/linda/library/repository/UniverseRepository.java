package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.linda.library.model.detail.Universe;

import java.util.List;

public interface UniverseRepository extends JpaRepository<Universe, String> {
    @Query(value = "SELECT DISTINCT universe_name FROM universe ORDER BY universe_name", nativeQuery = true)
    List<String> findDistinctUniverseNames();
}
