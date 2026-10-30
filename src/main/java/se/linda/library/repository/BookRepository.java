package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;

import java.util.List;

public interface BookRepository  extends JpaRepository<Book, Long> {

    List <Book> findByAuthorContainingIgnoreCase (String author);

    @Query(value = "SELECT DISTINCT author_name FROM book_authors WHERE author_name " +
            "IS NOT NULL ORDER BY author_name", nativeQuery = true)
    List<String> findDistinctAuthors();

    List <Book> findByBookFormat (BookFormat bookFormat);
    List <Book> findByFanficType (FanficType fanficType);
    List <Book> findByFandomContainingIgnoreCase (String fandom);

    @Query(value = "SELECT DISTINCT fandom FROM book_fandoms WHERE fandom " +
            "IS NOT NULL ORDER BY fandom", nativeQuery = true)
    List<String> findDistinctFandoms();
}
