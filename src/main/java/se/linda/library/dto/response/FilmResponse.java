package se.linda.library.dto.response;

import jakarta.validation.constraints.Size;
import se.linda.library.model.enums.MediaFormat;
import se.linda.library.model.enums.TranslationInfo;

import java.util.List;

public record FilmResponse(Long id, String title, List<String> genre,
                           String language, String seriesName,
                           String director, List<String> actors,
                           MediaFormat mediaFormat, TranslationInfo translationInfo,
                           String synopsis) {}
