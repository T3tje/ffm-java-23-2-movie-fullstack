package de.neuefische.backend.repository;

import de.neuefische.backend.model.Movie;
import de.neuefische.backend.model.MovieDBResponse;
import de.neuefische.backend.model.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@NoArgsConstructor
@AllArgsConstructor
public class MovieRepository {

    private Map<String, Movie> mapOfMovies = new HashMap<>();

    public List<MovieDTO> getAllMovies(){
        MovieDBResponse response = Objects.requireNonNull(
                WebClient.create() //Erzeugt einen WebClient
                        .get()//Schickt eine GET-Anfrage...
                        .uri("https://moviesdatabase.p.rapidapi.com/titles")//... an diese Adresse...
                        .header("X-RapidAPI-Key", //... mit diesem Header Key-Value Pair
                                "33abc06cebmsh3cc718a4f2182e2p1e0135jsnf4ee4a01a69d")
                        .retrieve()//Sammelt das Ergegnis der Anfrage
                        .toEntity(MovieDBResponse.class)//Verwandelt es in das Gewünschte Objekt
                        .block()
        ).getBody(); //Gibt den Body der Anfrage aus
        return response.results()
                .stream()
                .map(movie -> new MovieDTO(movie.id(), movie.titleText().text()))
                .collect(Collectors.toList());
    }



}
