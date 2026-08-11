package se.linda.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;

import java.util.List;

public record BookRequest(@NotBlank(message = "Title is required") String title,
                          List <String> genre,
                          @NotBlank (message = "Language is required") String language,
                          String seriesName,
                          @NotEmpty(message ="At least one author is required") List <String> author,
                          @NotNull(message = "Book Format is required") BookFormat bookFormat,
                          FanficType fanficType,
                          List <String> fandom) {}
