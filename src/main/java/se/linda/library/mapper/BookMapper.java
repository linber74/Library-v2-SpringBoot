package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.BookRequest;
import se.linda.library.dto.response.BookResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.ItemType;
import se.linda.library.repository.SeriesInfoRepository;

@Component
public class BookMapper {

    private final SeriesInfoRepository seriesInfoRepository;

    public BookMapper(SeriesInfoRepository seriesInfoRepository) {
        this.seriesInfoRepository = seriesInfoRepository;
    }

    public Book toEntity (BookRequest request){
        SeriesInfo seriesInfo = null;

        if (request.seriesName() != null){

            seriesInfo = seriesInfoRepository.findById(request.seriesName())
                    .orElseGet(() -> seriesInfoRepository.save(new SeriesInfo(request.seriesName())));
        }

        Book book = new Book();
        book.setTitle(request.title());
        book.setGenre(request.genre());
        book.setLanguage(request.language());
        book.setSeriesInfo(seriesInfo);
        book.setPartNumber(request.seriesPartNumber());
        book.setAuthor(request.author());
        book.setBookFormat(request.bookFormat());
        book.setFanficType(request.fanficType());
        book.setFandom(request.fandom());
        book.setItemType(ItemType.BOOK);
        book.setSynopsis(request.synopsis());

        return book;
    }

    public BookResponse toResponse (Book book){
        String seriesName = book.getSeriesInfo() != null
                ? book.getSeriesInfo().getSeriesName()
                :null;
        Integer seriesPartNumber = book.getPartNumber();

        return new BookResponse(book.getId(), book.getTitle(),
                book.getGenre(), book.getLanguage(), seriesName, seriesPartNumber, book.getAuthor(),
                book.getBookFormat(), book.getFanficType(), book.getFandom(), book.getSynopsis());
    }
}
