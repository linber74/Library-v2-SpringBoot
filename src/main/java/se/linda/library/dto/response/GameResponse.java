package se.linda.library.dto.response;

import java.util.List;

public record GameResponse(Long id, String title, List<String> genre,
                           String language, String seriesName, String creator) {
}
