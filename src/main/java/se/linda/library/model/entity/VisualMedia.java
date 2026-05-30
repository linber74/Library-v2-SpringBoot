package se.linda.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.enums.ItemType;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;

import java.util.List;

@Entity
@Table (name = "visualmedia")
@NoArgsConstructor
@Getter
@Setter
public abstract class VisualMedia extends LibraryItem {


    private String director;

    @ElementCollection
    @CollectionTable(name = "visualmedia_actors", joinColumns = @JoinColumn(name = "visualmediaId"))
    @Column (name = "actorName")
    private List<String> actors;

    @Enumerated(EnumType.STRING)
    private MediaFormat mediaFormat;

    @Enumerated(EnumType.STRING)
    private TranslationInfo translationInfo;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");

        if (director != null) {
            sb.append("Director: ").append(director).append("\n");
        } else {
            sb.append("Director: Unknown\n");
        }

        if (!getActors().isEmpty()){sb.append("Actors: ").append(getActors()).append("\n");}

        sb.append("Genre: ").append(getGenre()).append("\n");

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        sb.append("Type: ").append(getItemType()).append("\n");

        sb.append("Format: ").append(getMediaFormat()).append("\n");

        sb.append("Language: ").append(getLanguage()).append("\n");

        if (getTranslationInfo() != null) {
            sb.append("TranslationInfo: ").append(getTranslationInfo()).append("\n");
        }

        if (getPublishYear() != null) {
            sb.append("PublishYear: ").append(getPublishYear()).append("\n");
        }
        return sb.toString();
    }
}
