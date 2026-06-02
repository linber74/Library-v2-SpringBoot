package se.linda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;

import java.util.List;

public interface BookRepository  extends JpaRepository<Book, Long> {

    List <Book> findByAuthor (String author);
    List <Book> findByBookFormat (BookFormat bookFormat);
    List <Book> finbByFanficType (FanficType fanficType);
    List <Book> finbByFandom (String fandom);

}
