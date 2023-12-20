package de.neuefische.backend.model;

import java.util.Optional;

public record MovieExtendedInfo(
        String id,
        Optional<Image> primaryImage,
        Optional<RatingsSummary> ratingsSummary,
        MovieType titleType,
        TitleText titleText,
        ReleaseYear releaseYear

) {
}
