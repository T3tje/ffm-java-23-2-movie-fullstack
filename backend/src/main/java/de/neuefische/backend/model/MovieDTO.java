package de.neuefische.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

//Objekte die dem Frontend übergeben werden
@Document("movies")
public record MovieDTO (String id,
                        String title,
                        int year,
                        String url){

}
