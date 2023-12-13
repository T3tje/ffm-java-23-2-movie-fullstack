package de.neuefische.backend.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
@AllArgsConstructor
public class MovieRepository {

    private Map<String, Movie> mapOfMovies;



}
