package se.linda.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GameRequest(@NotBlank (message = "Title is required") String title,
                          List<String> genre,
                          @NotBlank (message = "Language is required") String language,
                          String seriesName,
                          String creator,
                          @Size(max = 1000) String synopsis) {
}
