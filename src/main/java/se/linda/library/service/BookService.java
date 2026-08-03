package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;
import se.linda.library.repository.BookRepository;

import java.util.List;


@Service
@Transactional
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save (Book book){
        if (book == null){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Book cannot be null");
        }
        return bookRepository.save(book);
    }

    public List <Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getById (Long id){
        return bookRepository.findById(id).orElseThrow(()

            // Todo Change exceptions later
            -> new ItemNotFoundException("Book with id " + id + " not found"));
    }

    public void deleteById (Long id){
        if (!bookRepository.existsById(id)) {

            // Todo Change exceptions later
            throw new ItemNotFoundException("Book with id " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public List<Book> searchByAuthor (String author){

        if (author == null || author.isBlank()){
            // Todo Change exceptions later
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchByBookFormat (BookFormat format) {
        if (format == null){
            // Todo Change exceptions later
            throw new IllegalArgumentException("BookFormat cannot be null");
        }
        return bookRepository.findByBookFormat(format);
    }

    public List<Book>  searchByFanficType (FanficType type){
        if (type == null){
            // Todo Change exceptions later
            throw new IllegalArgumentException("FanficType cannot be null");
        }
        return bookRepository.findByFanficType(type);
    }

    public List<Book> searchByFandom (String fandom){

        if (fandom == null || fandom.isBlank()){
            throw new IllegalArgumentException(" Fandom can't be null or blank");
        }

        return bookRepository.findByFandomContainingIgnoreCase(fandom);
    }
}
