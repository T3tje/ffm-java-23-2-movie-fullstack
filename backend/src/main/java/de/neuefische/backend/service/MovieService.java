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
    private static String apiKey;

    private final MovieRepository repo;

    public List<MovieDTO> getAllMovies(){
        return movieDbGETResponse("https://moviesdatabase.p.rapidapi.com/titles");
    }

    public List<MovieDTO> findMoviesByTitle(String title) {
        return movieDbGETResponse("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title);
    }

    public List<MovieDTO> findMoviesByKeyword(String keyword) {
        return movieDbGETResponse("https://moviesdatabase.p.rapidapi.com/titles/search/keyword/" + keyword);
    }

    private static List<MovieDTO> movieDbGETResponse(String url){
        MovieDBResponse response = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri(url)
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