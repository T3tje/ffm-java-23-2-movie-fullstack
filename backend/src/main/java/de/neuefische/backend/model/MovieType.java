package de.neuefische.backend.model;

//Objekt für das Objekt TitleType von der API
public record MovieType(String text,
                        String id,
                        boolean isSeries,
                        boolean isEpisode) {
}
