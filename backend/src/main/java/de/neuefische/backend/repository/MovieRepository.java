package de.neuefische.backend.repository;


import de.neuefische.backend.model.MovieDBResponse;
import de.neuefische.backend.model.MovieDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

import java.util.Objects;


@Repository
@NoArgsConstructor
public class MovieRepository {
    @Value("${API_KEY}")
    private String apiKey;

    //private Map<String, Movie> mapOfMovies = new HashMap<>();

    public List<MovieDTO> getAllMovies(){

        MovieDBResponse response = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://moviesdatabase.p.rapidapi.com/titles")
                        .header("X-RapidAPI-Key",
                                apiKey)
                        .retrieve()
                        .toEntity(MovieDBResponse.class)
                        .block()
        ).getBody();
        assert response!=null;
        return response.results()
                .stream()
                .map(movie -> new MovieDTO(movie.id(), movie.titleText().text()))
                .toList();
    }



}
