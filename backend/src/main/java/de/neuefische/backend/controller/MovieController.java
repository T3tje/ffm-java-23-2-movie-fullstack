package de.neuefische.backend.controller;

import de.neuefische.backend.model.Movie;
import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    public List<MovieDTO> getAllMovies(@RequestParam(name = "entries", required = false, defaultValue = "10") int entries, @RequestParam(name = "limit", required = false, defaultValue = "10") int limit ){
        if(entries == 10){
            return service.getAllMovies();
        }
        return service.getAllMovies("https://moviesdatabase.p.rapidapi.com",entries,limit);
    }

    @GetMapping("/search/{title}")
    public List<MovieDTO> findMoviesByTitle(@PathVariable String title){
        return service.findMoviesByTitle(title);

    }
    @GetMapping("/search")
    public List<MovieDTO> findMoviesByKeyword(@RequestParam String keyword){
        return service.findMoviesByKeyword(keyword);
    }

    @GetMapping("/favorites")
    public List<MovieDTO> getFavorites() {
        return service.getFavorites();
    }

    @PostMapping("/addfavorit")
    public MovieDTO addMovie(@RequestBody MovieDTO movie){
        return service.addMovie(movie);
    }

    @DeleteMapping("/deletefavorit/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable String id) {
        service.removeMovie(id);
        return ResponseEntity.noContent().build();
    }

}
