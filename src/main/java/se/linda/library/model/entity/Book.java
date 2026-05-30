package se.linda.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;


import java.util.List;
@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter @Setter
public class Book extends LibraryItem {

    @ElementCollection
    @CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "bookId"))
    @Column (name = "authorName")
    private List<String> author;

    @Enumerated(EnumType.STRING)
    private BookFormat bookFormat;

    @ElementCollection
    @CollectionTable(name = "book_fandoms", joinColumns = @JoinColumn(name = "bookId"))
    @Column (name = "fandom")
    private List <String> fandom;

    @Enumerated(EnumType.STRING)
    private FanficType fanficType;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(getTitle()).append("\n");

        sb.append("Author: ").append(author).append("\n");

        sb.append("Genre: ").append(getGenre()).append("\n");

        sb.append("Book Format: ").append(bookFormat).append("\n");

        if (getFandom() != null && !getFandom().isEmpty()) {
            sb.append("Fandom: ").append(String.join(", ", getFandom())).append("\n");
        }

        if (fanficType != null) {
            sb.append("FanficType: ").append(fanficType).append("\n");
        }

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        sb.append("Type: ").append(getItemType()).append("\n");

        sb.append("Language: ").append(getLanguage()).append("\n");

        if (getPublishYear() != null) {
            sb.append("PublishYear: ").append(getPublishYear()).append("\n");
        }

        return sb.toString();
    }
}

