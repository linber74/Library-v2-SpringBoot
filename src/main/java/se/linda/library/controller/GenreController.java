package se.linda.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.linda.library.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    final LibraryService service;

    public GenreController(LibraryService service){
        this.service = service;
    }

    @GetMapping
    public List<String> getAllGenres(){
        return service.getAllGenres();
    }
}
