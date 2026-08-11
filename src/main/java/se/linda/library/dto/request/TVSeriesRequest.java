package se.linda.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;

import java.util.List;

public record TVSeriesRequest (@NotBlank(message = "Title is required") String title,
                               List<String> genre,
                               @NotBlank (message = "Language is required") String language,
                               String seriesName,
                               @NotBlank (message = "Director is required (use 'Unknown' if not known)") String director,
                               List<String> actors,
                               @NotNull(message = "MediaFormat is required") MediaFormat mediaFormat,
                               TranslationInfo translationInfo) {
}
