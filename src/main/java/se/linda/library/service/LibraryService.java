package se.linda.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.linda.library.model.entity.LibraryItem;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.LibraryItemRepository;
import se.linda.library.repository.SeasonRepository;

import java.util.List;

@Service
@Transactional
public class LibraryService {

    final BookService bookService;
    final FilmService filmService;
    final TvSeriesService tvSeriesService;
    final GameService gameService;
    final LibraryItemRepository libraryItemRepository;
    final SeasonRepository seasonRepository;

    public LibraryService(BookService bookService, FilmService filmService,
                          TvSeriesService tvSeriesService, GameService gameService,
                          LibraryItemRepository libraryItemRepository,
                          SeasonRepository seasonRepository) {
        this.bookService = bookService;
        this.filmService = filmService;
        this.tvSeriesService = tvSeriesService;
        this.gameService = gameService;
        this.libraryItemRepository = libraryItemRepository;
        this.seasonRepository = seasonRepository;
    }

    public List<LibraryItem> getAll(){
        return libraryItemRepository.findAll();
    }

    public LibraryItem getById (Long id){

        return libraryItemRepository.findById(id).orElseThrow(()

                // Todo Change exceptions later
            -> new RuntimeException("Object with id: " + id + " not found!"));
    }

    public List<LibraryItem> getAllByType (ItemType itemType){

        if (itemType == null){
            // Todo Change exceptions later
            throw new IllegalArgumentException("Itemtype can't be null!");
        }

        return libraryItemRepository.findByItemType(itemType);
    }

    public List<LibraryItem> getAllByLanguage (String language){

        if (language == null || language.isBlank()){
            // Todo Change exceptions later
            throw new IllegalArgumentException("Language can't be null or blank");
        }

        return libraryItemRepository.findByLanguage(language);
    }

    public List<LibraryItem> getAllByTitle (String title){

        if (title == null || title.isBlank()){
            // Todo Change exceptions later
            throw new IllegalArgumentException("Title can't be null or blank");
        }

        return libraryItemRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<LibraryItem> getAllByPublishYear (Integer year){

        if (year < 0){
            // Todo Change exceptions later
            throw new IllegalArgumentException("Publishingyear can't be 0 or lower");
        }

        return libraryItemRepository.findByPublishYear(year);
    }
}
