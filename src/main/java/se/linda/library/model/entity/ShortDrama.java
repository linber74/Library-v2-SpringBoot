package se.linda.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name= "shortdrama")
@NoArgsConstructor
@Getter
@Setter
public class ShortDrama extends LibraryItem{

    @ElementCollection
    @CollectionTable(name = "shortdrama_tropegenres", joinColumns = @JoinColumn(name = "itemId"))
    @Column(name = "tropeGenre")
    private List<String> tropeGenre;

    private Integer durationSeconds;

}
