package se.linda.library.dto.response;

import se.linda.library.model.enums.ItemType;

import java.util.List;

public record LibraryItemResponse(Long id, ItemType itemType, String title,
                                  List<String> genre, String language,
                                  String seriesName) {}
