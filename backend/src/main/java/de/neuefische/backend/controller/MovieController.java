package de.neuefische.backend.controller;

import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    public List<MovieDTO> getAllMovies(){
        return service.getAllMovies();
    }

    @GetMapping("/search")
    public List<MovieDTO> findMoviesByTitle(@RequestParam String title){
        return service.findMoviesByTitle(title);

    }
}
