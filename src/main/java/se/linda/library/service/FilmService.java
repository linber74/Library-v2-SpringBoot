package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Film;
import se.linda.library.model.entity.VisualMedia;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;
import se.linda.library.repository.FilmRepository;
import se.linda.library.repository.VisualMediaRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
public class FilmService {

    final FilmRepository filmRepository;
    final VisualMediaRepository visualMediaRepository;


    public FilmService (FilmRepository filmRepository,
                        VisualMediaRepository visualMediaRepository){

        this.filmRepository = filmRepository;
        this.visualMediaRepository = visualMediaRepository;
    }

    public Film save (Film film){
        if (film == null) {
            log.warn("Film can't be null");
            throw new IllegalArgumentException("Film can't be null");
        }
        log.info("Saving Film: {}",film);
        return filmRepository.save(film);
    }

    public List<Film> getAll () {
        return filmRepository.findAll();
    }

    public Film getById (Long id) {
        return filmRepository.findById(id).orElseThrow(()

            ->{ log.warn("Film not found with id: {}", id);
               return new ItemNotFoundException("Film with id: " + id + " not found!");});
    }

    public void deleteById (Long id){

        if (!filmRepository.existsById(id)){

            log.warn("Film not found with id: {}", id);
            throw new ItemNotFoundException("Film with id: " + id + " not found!");
        }
        log.info("Deleted Film with id: {}", id);
        filmRepository.deleteById(id);
    }

    public List<VisualMedia> searchByDirector (String director){
        if (director == null || director.isBlank()){

            log.warn("Director can't be null or blank!");
            throw new IllegalArgumentException("Director can't be null or blank!");
        }
        log.info("Found by Director: {}", director);
        return visualMediaRepository.findByDirectorContainingIgnoreCase(director);
    }

    public List<VisualMedia> searchByActor (String actor){

        if (actor == null || actor.isBlank()){

            log.warn("Actor can't be null or blank!");
            throw new IllegalArgumentException("Actor can't be null or blank!");
        }
        log.info("Found by Actor: {}", actor);
        return visualMediaRepository.findByActorsContainingIgnoreCase(actor);
    }

    public List<VisualMedia> searchByMediaFormat (MediaFormat format) {
        if (format == null){

            log.warn("Media format can't be null!");
            throw new IllegalArgumentException("Media format can't be null!");
        }
        log.info("Found by Media format: {}", format);
        return visualMediaRepository.findByMediaFormat(format);
    }

    public List <VisualMedia> searchByTranslationsInfo (TranslationInfo info){
        if (info == null){
            log.warn("Translation info can't be null!");
            throw new IllegalArgumentException("Translation info can't be null!");
        }
        log.info("Found by translation: {}", info);
        return visualMediaRepository.findByTranslationInfo(info);
    }
}
