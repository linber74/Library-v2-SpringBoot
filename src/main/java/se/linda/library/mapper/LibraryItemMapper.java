package se.linda.library.mapper;

import org.springframework.stereotype.Component;
import se.linda.library.dto.response.LibraryItemResponse;
import se.linda.library.model.entity.LibraryItem;

@Component
public class LibraryItemMapper {
    public LibraryItemResponse toResponse(LibraryItem item) {
        String seriesName = item.getSeriesInfo() != null
                ? item.getSeriesInfo().getSeriesName()
                : null;

        return new LibraryItemResponse(item.getId(), item.getItemType(), item.getTitle(),
                item.getGenre(), item.getLanguage(), seriesName);
    }
}
