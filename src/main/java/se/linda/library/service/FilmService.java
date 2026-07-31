package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.model.entity.Film;
import se.linda.library.model.entity.VisualMedia;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;
import se.linda.library.repository.FilmRepository;
import se.linda.library.repository.VisualMediaRepository;

import java.util.List;

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
            // Todo Change exceptions later
            throw new IllegalArgumentException("Film can't be null");
        }

        return filmRepository.save(film);
    }

    public List<Film> getAll () {
        return filmRepository.findAll();
    }

    public Film getById (Long id) {
        return filmRepository.findById(id).orElseThrow(()
                // Todo Change exceptions later
            -> new RuntimeException("Film with id: " + id + " not found!"));
    }

    public void deleteById (Long id){

        if (!filmRepository.existsById(id)){

            // Todo Change exceptions later
            throw new RuntimeException("Film with id: " + id + " not found!");
        }
        filmRepository.deleteById(id);
    }

    public List<VisualMedia> searchByDirector (String director){
        if (director == null || director.isBlank()){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Director can't be null or blank!");
        }
        return visualMediaRepository.findByDirectorContainingIgnoreCase(director);
    }

    public List<VisualMedia> searchByActor (String actor){

        if (actor == null || actor.isBlank()){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Actor can't be null or blank!");
        }
        return visualMediaRepository.findByActorsContainingIgnoreCase(actor);
    }

    public List<VisualMedia> searchByMediaFormat (MediaFormat format) {
        if (format == null){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Media format can't be null!");
        }

        return visualMediaRepository.findByMediaFormat(format);
    }

    public List <VisualMedia> searchByTranslationsInfo (TranslationInfo info){
        if (info == null){

            // Todo Change exceptions later
            throw new IllegalArgumentException("Translation info can't be null!");
        }

        return visualMediaRepository.findByTranslationInfo(info);
    }
}
