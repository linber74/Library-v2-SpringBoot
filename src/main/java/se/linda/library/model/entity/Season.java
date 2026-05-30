package se.linda.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "season")
@NoArgsConstructor
@Getter
@Setter
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seasonNumber;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes;

    @ManyToOne
    private TVSeries tvSeries;

    @Override
    public String toString() {
        return "Säsong " + seasonNumber + " - " + episodes;
    }
}
