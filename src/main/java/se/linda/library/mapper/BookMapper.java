package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.request.BookRequest;
import se.linda.library.dto.response.BookResponse;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.ItemType;

@Component
public class BookMapper {

    public Book toEntity (BookRequest request){
        SeriesInfo seriesInfo = null;
        if (request.seriesName() != null){
            seriesInfo = new SeriesInfo();
            seriesInfo.setSeriesName(request.seriesName());
        }

       Book book = new Book();
        book.setTitle(request.title());
        book.setGenre(request.genre());
        book.setLanguage(request.language());
        book.setSeriesInfo(seriesInfo);
        book.setAuthor(request.author());
        book.setBookFormat(request.bookFormat());
        book.setFanficType(request.fanficType());
        book.setFandom(request.fandom());
        book.setItemType(ItemType.BOOK);

        return book;
    }

    public BookResponse toResponse (Book book){
        String seriesName = book.getSeriesInfo() != null
                ? book.getSeriesInfo().getSeriesName()
                :null;

        return new BookResponse(book.getId(), book.getTitle(),
                book.getGenre(), book.getLanguage(), seriesName, book.getAuthor(),
                book.getBookFormat(), book.getFanficType(), book.getFandom());
    }

}
