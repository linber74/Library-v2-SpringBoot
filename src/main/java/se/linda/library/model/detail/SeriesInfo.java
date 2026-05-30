package se.linda.library.model.detail;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "seriesinfo")
@NoArgsConstructor
@Getter
@Setter
public class SeriesInfo {

    @Id
    private String seriesName;

    private int partNumber;

    @Override
    public String toString() {
        return "Serie: " + this.seriesName + ", Del: " + this.partNumber;
    }
}
