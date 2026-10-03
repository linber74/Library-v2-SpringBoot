package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.LibraryItem;
import se.linda.library.model.enums.ItemType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {

    List<LibraryItem> findByItemType(ItemType itemType);
    List<LibraryItem> findByLanguage(String language);
    List<LibraryItem> findByTitleContainingIgnoreCase(String title);
    List<LibraryItem> findByPublishYear(Integer year);

    @Query(value = "SELECT DISTINCT genre FROM item_genres ORDER BY genre", nativeQuery = true)
    List<String> findDistinctGenres();
}
