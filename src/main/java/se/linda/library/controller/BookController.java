package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;
import se.linda.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping ("/api/books")
public class BookController {

    final BookService bookService;

    public BookController (BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll (){
        return bookService.getAll();
    }

    @GetMapping ("/{id}")
    public Book getById (@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping
    public Book save (@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/author")
    public List<Book> searchByAuthor(@RequestParam String author){
        return bookService.searchByAuthor(author);
    }

    @GetMapping ("/search/format")
    public List<Book> searchByFormat(@RequestParam BookFormat bookFormat){
        return bookService.searchByBookFormat(bookFormat);
    }

    @GetMapping ("/search/fanfictype")
    public List<Book> searchByFanficType (@RequestParam FanficType fanficType){
        return bookService.searchByFanficType(fanficType);
    }

    @GetMapping ("/search/fandom")
    public List<Book> searchByFandom (@RequestParam String fandom){
        return bookService.searchByFandom(fandom);
    }
}
