package de.neuefische.backend.controller;

import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search/{title}")
    public List<MovieDTO> findMoviesByTitle(@PathVariable String title){
        return service.findMoviesByTitle(title);

    }
    @GetMapping("/search")
    public List<MovieDTO> findMoviesByKeyword(@RequestParam String keyword){
        return service.findMoviesByKeyword(keyword);
    }
    @GetMapping("/next")
    public List<MovieDTO> getNext10Movies(){
        return service.getNext10Movies();
    }
}
