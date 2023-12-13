package de.neuefische.backend.controller;

import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private final MovieService service;

    @GetMapping
    public List<MovieDTO> getAllMovies(){
        return service.getAllMovies();
    }
}
