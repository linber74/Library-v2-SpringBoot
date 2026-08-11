package se.linda.library.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GameRequest(@NotBlank (message = "Title is required") String title,
                          List<String> genre,
                          @NotBlank (message = "Language is required") String language,
                          String seriesName,
                          String creator) {
}
