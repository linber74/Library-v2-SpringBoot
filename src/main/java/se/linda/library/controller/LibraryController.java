package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.response.LibraryItemResponse;
import se.linda.library.mapper.LibraryItemMapper;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.LibraryItem;
import se.linda.library.model.enums.ItemType;
import se.linda.library.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    final LibraryService libraryService;
    final LibraryItemMapper libraryItemMapper;

    public LibraryController(LibraryService libraryService,
                             LibraryItemMapper libraryItemMapper) {
        this.libraryService = libraryService;
        this.libraryItemMapper = libraryItemMapper;
    }

    @GetMapping
    public List<LibraryItemResponse> getAll(){
        return libraryService.getAll()
                .stream()
                .map(libraryItemMapper :: toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public LibraryItemResponse getById(@PathVariable Long id){
        LibraryItem libraryItem = libraryService.getById(id);
        return libraryItemMapper.toResponse(libraryItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        libraryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/type")
    public List<LibraryItemResponse> searchByType(@RequestParam ItemType itemType){
        return libraryService.getAllByType(itemType)
                .stream()
                .map(libraryItemMapper :: toResponse)
                .toList();
    }

    @GetMapping ("/search/language")
    public List<LibraryItemResponse> searchByLanguage (@RequestParam String language){
        return libraryService.getAllByLanguage(language)
                .stream()
                .map(libraryItemMapper :: toResponse)
                .toList();
    }

    @GetMapping("/search/title")
    public List<LibraryItemResponse> searchByTitle (@RequestParam String title){
        return libraryService.getAllByTitle(title)
                .stream()
                .map(libraryItemMapper :: toResponse)
                .toList();
    }

    @GetMapping("/search/year")
    public List<LibraryItemResponse> searchByYear (@RequestParam Integer year){
        return libraryService.getAllByPublishYear(year)
                .stream()
                .map(libraryItemMapper :: toResponse)
                .toList();
    }

    @GetMapping ("/series")
    public List<SeriesInfo> getAllSeriesInfo(){
        return libraryService.getAllSeriesInfo();
    }
}
