package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.LibraryItem;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.LibraryItemRepository;
import se.linda.library.repository.SeasonRepository;
import se.linda.library.repository.SeriesInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class LibraryService {

    final BookService bookService;
    final FilmService filmService;
    final TvSeriesService tvSeriesService;
    final GameService gameService;
    final LibraryItemRepository libraryItemRepository;
    final SeasonRepository seasonRepository;
    private final SeriesInfoRepository seriesInfoRepository;

    public LibraryService(BookService bookService, FilmService filmService,
                          TvSeriesService tvSeriesService, GameService gameService,
                          LibraryItemRepository libraryItemRepository,
                          SeasonRepository seasonRepository, SeriesInfoRepository seriesInfoRepository) {
        this.bookService = bookService;
        this.filmService = filmService;
        this.tvSeriesService = tvSeriesService;
        this.gameService = gameService;
        this.libraryItemRepository = libraryItemRepository;
        this.seasonRepository = seasonRepository;
        this.seriesInfoRepository = seriesInfoRepository;
    }

    public List<LibraryItem> getAll(){
        return libraryItemRepository.findAll();
    }

    public LibraryItem getById (Long id){

        return libraryItemRepository.findById(id).orElseThrow(()


            ->{ log.warn("Object not found with id: {}", id);
                return new ItemNotFoundException("Object with id: " + id + " not found!");});
    }

    public List<LibraryItem> getAllByType (ItemType itemType){

        if (itemType == null){
            log.warn("Item type can't be null!");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Itemtype can't be null!");
        }
        log.info("Getting item type: {}", itemType);
        return libraryItemRepository.findByItemType(itemType);
    }

    public List<LibraryItem> getAllByLanguage (String language){

        if (language == null || language.isBlank()){

            log.warn("Language can't be null or blank");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Language can't be null or blank");
        }
        log.info("Getting by Language: {}", language);
        return libraryItemRepository.findByLanguage(language);
    }

    public List<LibraryItem> getAllByTitle (String title){

        if (title == null || title.isBlank()){
            log.warn("Title can't be null or blank");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Title can't be null or blank");
        }
        log.info("Getting Title: {}", title);
        return libraryItemRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<LibraryItem> getAllByPublishYear (Integer year){

        if (year < 0){
            log.warn("Publishingyear can't be 0 or lower");
            // Todo Change exceptions later
            throw new IllegalArgumentException("Publishingyear can't be 0 or lower");
        }
        log.info("Getting from publishing year: {}", year);
        return libraryItemRepository.findByPublishYear(year);
    }

    public void deleteById (Long id){

        if (!libraryItemRepository.existsById(id)){
            log.warn(" Object not found with id: {}", id);
            // Todo Change exceptions later
            throw new IllegalArgumentException("Object with id: " + id + " not found");
        }
        log.info("Deleted object with id: {}", id);
        libraryItemRepository.deleteById(id);
    }

    public SeriesInfo getOrCreateSeriesInfo(String name, int partNumber){

        Optional<SeriesInfo> existing =  seriesInfoRepository.findById(name);

        if(existing.isPresent()){
            return existing.get();
        }else {
            SeriesInfo newSeriesInfo = new SeriesInfo(name, partNumber);
            return seriesInfoRepository.save(newSeriesInfo);
        }
    }

    public List<SeriesInfo> getAllSeriesInfo(){
        return seriesInfoRepository.findAll();
    }
}
