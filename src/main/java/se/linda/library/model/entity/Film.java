package se.linda.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film")
@NoArgsConstructor
public class Film extends VisualMedia {

    @Override
    public String toString() {
        return super.toString();
    }
}
