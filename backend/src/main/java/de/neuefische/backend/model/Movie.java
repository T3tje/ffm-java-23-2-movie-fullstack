package de.neuefische.backend.model;
//Titles Objekt von der API/Datenbank
public record Movie(String id,
                    Image primaryImage,
                    MovieType titleType,
                    TitleText titleText,
                    TitleText originalTitleText,
                    ReleaseYear releaseYear,
                    String releaseDate) {
}
