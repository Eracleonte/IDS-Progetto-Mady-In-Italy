package it.unicam.cs.ids.api.dto.input;

import it.unicam.cs.ids.api.model.contents.ContentType;

/**
 * A simple filter used to find content.
 */
public record ContentSearchFilterDTO(String contentName,
                                     ContentType contentType,
                                     String authorName) {
}
