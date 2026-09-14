package se.linda.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AudioStoryRequest(@NotBlank(message = "Title is required") String title,
                                @NotBlank (message = "Language is required") String language,
                                @NotNull(message = "Trope genre list must not be null") List<String> tropeGenre,
                                @NotNull(message = "Please provide the duration in seconds (e.g. 1800 for 30 minutes)")Integer durationSeconds)
{}
