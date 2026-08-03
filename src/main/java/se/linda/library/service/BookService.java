package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;
import se.linda.library.repository.BookRepository;

import java.util.List;


@Service
@Transactional
@Slf4j
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save (Book book){
        if (book == null){

            log.warn("Attempted to save null book");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Book cannot be null");
        }
        log.info("Saving book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    public List <Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getById (Long id){
        return bookRepository.findById(id).orElseThrow(()

            ->{ log.warn("Book not found with id: {}", id);
               return new ItemNotFoundException("Book with id " + id + " not found");});
    }

    public void deleteById (Long id){
        if (!bookRepository.existsById(id)) {

            log.warn("Book not found with id: {}", id);
            throw new ItemNotFoundException("Book with id " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public List<Book> searchByAuthor (String author){

        if (author == null || author.isBlank()){
            log.warn("Author cannot be null or blank");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        log.info("Getting Author: {}", author);
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchByBookFormat (BookFormat format) {
        if (format == null){
            log.warn("BookFormat can't be null");
            // Todo Change exceptions later
            throw new IllegalArgumentException("BookFormat can't be null");
        }
        log.info("Getting book format: {}", format);
        return bookRepository.findByBookFormat(format);
    }

    public List<Book>  searchByFanficType (FanficType type){
        if (type == null){

            log.warn("FanficType can't be null");
            // Todo Change exceptions later
            throw new IllegalArgumentException("FanficType can't be null");
        }
        log.info("Getting fanfic type: {}", type);
        return bookRepository.findByFanficType(type);
    }

    public List<Book> searchByFandom (String fandom){

        if (fandom == null || fandom.isBlank()){
            log.warn("Fandom can't be null or blank");
            throw new IllegalArgumentException("Fandom can't be null or blank");
        }
        log.info("Getting fandom: {}", fandom);
        return bookRepository.findByFandomContainingIgnoreCase(fandom);
    }
}
