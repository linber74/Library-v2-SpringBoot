package se.linda.library.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.request.FilmRequest;
import se.linda.library.dto.response.FilmResponse;
import se.linda.library.mapper.FilmMapper;
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
    final FilmMapper filmMapper;

    public FilmController(FilmService filmService, FilmMapper filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @GetMapping
    public List<FilmResponse> getAll(){
        return filmService.getAll()
                .stream()
                .map(filmMapper ::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public FilmResponse getById (@PathVariable Long id){
        Film film = filmService.getById(id);
        return filmMapper.toResponse(film);
    }

    @PostMapping
    public FilmResponse save (@Valid @RequestBody FilmRequest request){
        Film film = filmMapper.toEntity(request);
        Film saved = filmService.save(film);
        return filmMapper.toResponse(saved);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        filmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/search/director")
    public List<FilmResponse> searchByDirector(@RequestParam String director){
        return filmService.searchByDirector(director)
                .stream()
                .map(vm -> filmMapper.toResponse((Film)vm))
                .toList();
    }

    @GetMapping ("/search/actor")
    public List<FilmResponse> searchByActor (@RequestParam String actor){
        return filmService.searchByActor(actor)
                .stream()
                .map(vm -> filmMapper.toResponse((Film)vm))
                .toList();
    }

    @GetMapping ("/search/format")
    public List<FilmResponse> searchByMediaFormat (@RequestParam MediaFormat format){
        return filmService.searchByMediaFormat(format)
                .stream()
                .map(vm -> filmMapper.toResponse((Film)vm))
                .toList();
    }

    @GetMapping ("/search/translation")
    public List<FilmResponse> searchByTranslation(@RequestParam TranslationInfo info){
        return filmService.searchByTranslationsInfo(info)
                .stream()
                .map(vm -> filmMapper.toResponse((Film)vm))
                .toList();
    }
}
