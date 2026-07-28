package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.repository.FilmRepository;
import se.linda.library.repository.VisualMediaRepository;


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



}
