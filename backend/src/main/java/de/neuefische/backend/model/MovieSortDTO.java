package de.neuefische.backend.model;

public record MovieSortDTO(
        String id,
        String title,
        double rating,
        int releaseYear
) {
}
