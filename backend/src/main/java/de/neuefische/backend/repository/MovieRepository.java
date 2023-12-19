package de.neuefische.backend.repository;

import de.neuefische.backend.model.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Repository
@NoArgsConstructor
@Data
public class MovieRepository {
    @Value("${API_KEY}")
    private String apiKey;

    private Map<String, Movie> mapOfMovies = new HashMap<>();

    private MovieMongoRepository movieMongoRepository;
    @Autowired
    public MovieRepository(MovieMongoRepository movieMongoRepository) {
        this.movieMongoRepository = movieMongoRepository;
    }
}
