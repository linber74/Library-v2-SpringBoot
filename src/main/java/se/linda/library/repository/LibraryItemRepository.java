package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.LibraryItem;

public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {
}
