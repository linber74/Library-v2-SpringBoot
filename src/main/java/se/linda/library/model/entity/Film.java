package se.linda.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "film")
@NoArgsConstructor
@Getter
@Setter
public class Film extends VisualMedia {

    @Override
    public String toString() {
        return super.toString();
    }
}
