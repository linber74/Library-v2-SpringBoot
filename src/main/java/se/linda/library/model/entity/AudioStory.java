package se.linda.library.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "audiostory")
@NoArgsConstructor
@Getter
@Setter
public class AudioStory extends LibraryItem{

    @ElementCollection
    @CollectionTable(name= "audiostory_tropegenres", joinColumns = @JoinColumn(name= "itemId"))
    @Column(name = "tropeGenre")
    private List<String> tropeGenre;

    private Integer durationSeconds;
}
