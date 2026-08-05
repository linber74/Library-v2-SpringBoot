package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.LibraryItem;
import se.linda.library.model.enums.ItemType;
import se.linda.library.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<LibraryItem> getAll(){
        return libraryService.getAll();
    }

    @GetMapping("/{id}")
    public LibraryItem getById(@PathVariable Long id){
        return libraryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        libraryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/type")
    public List<LibraryItem> searchByType(@RequestParam ItemType itemType){
        return libraryService.getAllByType(itemType);
    }

    @GetMapping ("/search/language")
    public List<LibraryItem> searchByLanguage (@RequestParam String language){
        return libraryService.getAllByLanguage(language);
    }

    @GetMapping("/search/title")
    public List<LibraryItem> searchByTitle (@RequestParam String title){
        return libraryService.getAllByTitle(title);
    }

    @GetMapping("/search/year")
    public List<LibraryItem> searchByYear (@RequestParam Integer year){
        return libraryService.getAllByPublishYear(year);
    }

    @GetMapping ("/series")
    public List<SeriesInfo> getAllSeriesInfo(){
        return libraryService.getAllSeriesInfo();
    }
}
