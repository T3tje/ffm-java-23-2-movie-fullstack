package de.neuefische.backend.model;

import org.apache.logging.log4j.util.PropertySource;

public record MovieSortDTO(
        String id,
        String title,
        double rating,
        int releaseYear,
        String url
) {
}
