package de.neuefische.backend.service;


import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repo;

    public List<MovieDTO> getAllMovies(){
        return repo.getAllMovies();
    }
}
