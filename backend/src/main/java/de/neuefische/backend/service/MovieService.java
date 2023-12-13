package de.neuefische.backend.service;

import de.neuefische.backend.model.Movie;
import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class MovieService {
    @Autowired
    private final MovieRepository repo;
    @Autowired
    private final IdService idService;

    public List<MovieDTO> getAllMovies(){
        return repo.getAllMovies();
    }
}
