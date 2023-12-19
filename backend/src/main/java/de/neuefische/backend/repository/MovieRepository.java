package de.neuefische.backend.repository;


import de.neuefische.backend.model.Movie;

import de.neuefische.backend.model.MovieExtendedInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
@NoArgsConstructor
@Data
public class MovieRepository {
    @Value("${API_KEY}")
    private String apiKey;

    private  Map<String, Movie> mapOfMovies = new HashMap<>();
    private  Map<String, MovieExtendedInfo> mapOfMoviesWithExtendedInfo = new HashMap<>();




}
