package se.linda.library.dto.response;

import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;

import java.util.List;

public record BookResponse(Long id, String title, List<String> genre, String language,
                           String seriesName, List <String> author,
                           BookFormat bookFormat, FanficType fanficType,
                           List <String> fandom) {}
