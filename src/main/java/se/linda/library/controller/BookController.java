package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.request.BookRequest;
import se.linda.library.dto.response.BookResponse;
import se.linda.library.mapper.BookMapper;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;
import se.linda.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping ("/api/books")
public class BookController {

    final BookService bookService;
    final BookMapper bookMapper;

    public BookController (BookService bookService, BookMapper bookMapper){
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public List<BookResponse> getAll (){
        return bookService.getAll()
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @GetMapping ("/{id}")
    public BookResponse getById (@PathVariable Long id){
        Book book = bookService.getById(id);
        return bookMapper.toResponse(book);
    }

    @PostMapping
    public BookResponse save (@RequestBody BookRequest request){
        Book book = bookMapper.toEntity(request);
        Book saved = bookService.save(book);
        return bookMapper.toResponse(saved);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/author")
    public List<BookResponse> searchByAuthor(@RequestParam String author){
        return bookService.searchByAuthor(author)
                .stream()
                .map(bookMapper ::toResponse)
                .toList();
    }

    @GetMapping ("/search/format")
    public List<BookResponse> searchByFormat(@RequestParam BookFormat bookFormat){
        return bookService.searchByBookFormat(bookFormat)
                .stream()
                .map(bookMapper :: toResponse)
                .toList();
    }

    @GetMapping ("/search/fanfictype")
    public List<BookResponse> searchByFanficType (@RequestParam FanficType fanficType){
        return bookService.searchByFanficType(fanficType)
                .stream()
                .map(bookMapper :: toResponse)
                .toList();
    }

    @GetMapping ("/search/fandom")
    public List<BookResponse> searchByFandom (@RequestParam String fandom){
        return bookService.searchByFandom(fandom)
                .stream()
                .map(bookMapper :: toResponse)
                .toList();
    }
}
