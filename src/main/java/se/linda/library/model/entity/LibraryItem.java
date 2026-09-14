package se.linda.library.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.linda.library.model.detail.SeriesInfo;
import se.linda.library.model.enums.ItemType;

import java.util.List;

@Entity
@Table (name= "libraryitem")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter  @Setter
public abstract class LibraryItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;

    @Enumerated(EnumType.STRING)
    private  ItemType itemType;

    @NotBlank
    private String title;

    @ElementCollection
    @CollectionTable(name = "item_genres", joinColumns =  @JoinColumn (name = "itemId"))
    @Column (name = "genre")
    private List<String> genre;

    private  String language;

    @Column(length = 1000)
    @Size(max=1000)
    private String synopsis;

    private Integer publishYear;

    @ManyToOne
    @JoinColumn (name = "seriesName")
    private SeriesInfo seriesInfo;

    @Override
    public String toString(){
    // kort, UI-vänlig sammanfattning
        String base = title + " | " + "Type: " + itemType + " | " + "(" + language + ")";
        if(synopsis != null && !synopsis.isEmpty()){
            String shortSynopsis = synopsis.length() > 50
                    ? synopsis.substring(0, 50) + "..."
                    : synopsis;
            base += " | " + shortSynopsis;
        }
        if (publishYear != null) {
            base += ", " + publishYear;
        }
        if (seriesInfo != null) {
            base += " - " + seriesInfo;
        }
        return base;
    }
}
