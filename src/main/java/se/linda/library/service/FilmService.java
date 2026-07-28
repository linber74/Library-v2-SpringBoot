package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.model.entity.Film;
import se.linda.library.repository.FilmRepository;
import se.linda.library.repository.VisualMediaRepository;

import java.util.List;
import java.util.Optional;


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
            -> {return new RuntimeException("Film with id: " + id + " not found!");
        });
    }

    public void deleteById (Long id){

        if (!filmRepository.existsById(id)){

            // Todo Change exceptions later
            throw new RuntimeException("Film with id: " + id + " not found");
        }
        filmRepository.deleteById(id);
    }

}
