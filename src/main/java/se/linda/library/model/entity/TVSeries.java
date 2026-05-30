package se.linda.library.model.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tvseries")
@NoArgsConstructor
@Getter
@Setter
public class TVSeries extends VisualMedia {

    @OneToMany (mappedBy = "tvSeries", cascade = CascadeType.ALL)
    private List<Season> seasons;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");

        if (getDirector() != null) {
            sb.append("Director: ").append(getDirector()).append("\n");
        } else {
            sb.append("Director: Unknown\n");
        }

        if (!getActors().isEmpty()){sb.append("Actors: ").append(getActors()).append("\n");}

        sb.append("Genre: ").append(getGenre()).append("\n");

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        for (Season season : getSeasons()) {
            sb.append("Season: ")
                    .append(season.getSeasonNumber()).append("\n");
            for (Episode episode : season.getEpisodes()) {
                sb.append(" ").append(episode).append("\n");
            }
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

