package de.neuefische.backend.model;

import lombok.With;

import java.util.List;
@With
public record MovieDBResponse(int page, //Auf welcher Seite wir uns aktuell befinden
                              String next, //URL zur nächsten Seite
                              int entries, //Anzahl der Einträge auf dieser Seite default = 10
                              List<Movie> results) {
}
