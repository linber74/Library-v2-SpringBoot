package se.linda.library.model.detail;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "universe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Universe {

    @Id
    private String universeName;

    @Override
    public String toString() {
        return "Universe: " + universeName;
    }
}
