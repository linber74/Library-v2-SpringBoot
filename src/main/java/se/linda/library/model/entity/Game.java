package se.linda.library.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game")
@NoArgsConstructor
@Getter
@Setter
public class Game extends LibraryItem {

   private String creator;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(getTitle()).append("\n");

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        sb.append("Genre: ").append(getGenre()).append("\n");

        if (creator != null) {
            sb.append("Creator: ").append(creator).append("\n");
        } else {
            sb.append("Creator: Unknown\n");
        }

        sb.append("Type: ").append(getItemType()).append("\n");

        sb.append("Language: ").append(getLanguage()).append("\n");

        if (getPublishYear() != null) {
            sb.append("PublishYear: ").append(getPublishYear()).append("\n");
        }

        return sb.toString();
    }
}
