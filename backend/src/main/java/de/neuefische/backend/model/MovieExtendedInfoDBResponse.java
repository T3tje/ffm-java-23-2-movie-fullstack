package de.neuefische.backend.model;

import java.util.List;

public record MovieExtendedInfoDBResponse(int page, //Auf welcher Seite wir uns aktuell befinden
                                          String next, //URL zur nächsten Seite
                                          int entries, //Anzahl der Einträge auf dieser Seite default = 10
                                          List<MovieExtendedInfo> results) {
}
