package de.neuefische.backend.model;

public record MovieExtendedInfo(
        String id,
        RatingsSummary ratingsSummary,
        Image primaryImage,
        MovieType titleType,

        Genre[] genres,
        TitleText titleText,
        TitleText originalTitleText,
        ReleaseYear releaseYear

) {
}
