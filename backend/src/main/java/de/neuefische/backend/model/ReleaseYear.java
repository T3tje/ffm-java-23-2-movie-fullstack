package de.neuefische.backend.model;
//Da Serien ein Anfangs- und Endyear haben
public record ReleaseYear(int year,
                          int endYear) {
}
