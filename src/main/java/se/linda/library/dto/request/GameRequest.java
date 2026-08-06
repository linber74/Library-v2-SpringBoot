package se.linda.library.dto.request;

import java.util.List;

public record GameRequest(String title, List<String> genre, String language,
                          String seriesName, String creator) {
}
