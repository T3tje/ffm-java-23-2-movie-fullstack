package de.neuefische.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/titles")
public class MovieController {

    private final MovieService service;

    @GetMapping
    public List<MovieDTO> getAllMovies(){
        return service.getAllMovies();
    }
}
