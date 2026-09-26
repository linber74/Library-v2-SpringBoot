package se.linda.library.dto.response;

import java.util.List;

public record GameResponse(Long id, String title, List<String> genre, String language,
                           String seriesName, Integer seriesPartNumber, String creator, String synopsis) {
}
