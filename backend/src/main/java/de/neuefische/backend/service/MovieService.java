package de.neuefische.backend.service;

import de.neuefische.backend.model.Movie;
import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class MovieService {

    private final MovieRepository repo;
    private final IdService idService;

    public List<MovieDTO> getAllMovies(){
        return repo.getAllMovies();
    }
}
