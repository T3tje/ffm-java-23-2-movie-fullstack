package de.neuefische.backend.service;


import de.neuefische.backend.model.MovieDBResponse;
import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MovieService {
    @Value("${API_KEY}")
    private String apiKey;

    private final MovieRepository repo;

    public List<MovieDTO> getAllMovies(){
        return repo.getAllMovies();
    }

    public List<MovieDTO> findMoviesByTitle(String title) {
        MovieDBResponse response = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title)
                        .header("X-RapidApi-Key", apiKey)
                        .retrieve()
                        .toEntity(MovieDBResponse.class)
                        .block()
        ).getBody();
        assert response != null;
        return response.results()
                .stream()
                .map(movie -> new MovieDTO(movie.id(),movie.titleText().text()))
                .toList();
    }
}
