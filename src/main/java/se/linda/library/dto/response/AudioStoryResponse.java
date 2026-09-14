package se.linda.library.dto.response;

import java.util.List;

public record AudioStoryResponse(Long id, String title, String language, List<String> tropeGenre,
                                 Integer durationSeconds, String synopsis) {
}
