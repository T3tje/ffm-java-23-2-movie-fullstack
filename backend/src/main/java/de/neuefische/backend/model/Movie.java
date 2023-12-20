package de.neuefische.backend.model;


import java.util.Optional;

//Titles Objekt von der API/Datenbank
public record Movie(String id,
                    Optional<Image> primaryImage,
                    MovieType titleType,
                    TitleText titleText,
                    TitleText originalTitleText,
                    ReleaseYear releaseYear
) {
}
