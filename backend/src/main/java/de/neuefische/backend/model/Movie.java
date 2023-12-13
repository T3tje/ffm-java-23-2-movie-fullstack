package de.neuefische.backend.model;
//Titles Objekt von der API/Datenbank
public record Movie(String id,
                    MovieType titleType,
                    TitleText titleText,
                    TitleText originalTitleText,
                    ReleaseYear releaseYear) {
}
