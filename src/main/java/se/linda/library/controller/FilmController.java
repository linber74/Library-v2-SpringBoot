package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.model.entity.Film;
import se.linda.library.model.entity.VisualMedia;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;
import se.linda.library.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getAll(){
        return filmService.getAll();
    }

    @GetMapping("/{id}")
    public Film getById (@PathVariable Long id){
        return filmService.getById(id);
    }

    @PostMapping
    public Film save (@RequestBody Film film){
        return filmService.save(film);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        filmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/director")
    public List<VisualMedia> searchByDirector(@RequestParam String director){
        return filmService.searchByDirector(director);
    }

    @GetMapping ("/search/actor")
    public List<VisualMedia> searchByActor (@RequestParam String actor){
        return filmService.searchByActor(actor);
    }

    @GetMapping ("/search/format")
    public List<VisualMedia> searchByMediaFormat (@RequestParam MediaFormat format){
        return filmService.searchByMediaFormat(format);
    }

    @GetMapping ("/search/translation")
    public List<VisualMedia> searchByTranslation(@RequestParam TranslationInfo info){
        return filmService.searchByTranslationsInfo(info);
    }
}
