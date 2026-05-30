package se.linda.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "episode")
@NoArgsConstructor
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Season season;

    private int episodeNumber;
    private String episodeName;


    @Override
    public String toString() {
        if (episodeName == null || episodeName.isBlank()) {
            return "Episode " +  episodeNumber;
        }
        return "Episode " + getEpisodeNumber() + " - " + getEpisodeName();
    }
}
