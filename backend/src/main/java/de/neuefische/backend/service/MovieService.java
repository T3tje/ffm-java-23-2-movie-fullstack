package de.neuefische.backend.service;


import de.neuefische.backend.model.Movie;
import de.neuefische.backend.model.MovieDBResponse;
import de.neuefische.backend.model.MovieDTO;
import de.neuefische.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MovieService {
    @Value("${API_KEY}")
    private String apiKey;

    private final MovieRepository repo;

    public List<MovieDTO> getAllMovies(){
        return movieDbDTOResponse("https://moviesdatabase.p.rapidapi.com/titles");
    }

    public List<MovieDTO> findMoviesByTitle(String title) {
        return movieDbDTOResponse("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title);
    }

    public List<MovieDTO> findMoviesByKeyword(String keyword) {
        return movieDbDTOResponse("https://moviesdatabase.p.rapidapi.com/titles/search/keyword/" + keyword);
    }

    private MovieDBResponse movieDbGETResponse(String url){
        MovieDBResponse response = Objects.requireNonNull(
                WebClient.create()//Creates WebClient
                        .get()//Sends GET Request to...
                        .uri(url)//...this URL with the following header:
                        .header("X-RapidAPI-Key", apiKey) //header of the GET-Request
                        .retrieve()//Retrieve Data from the Response
                        .toEntity(MovieDBResponse.class) //Turn it into the desired Datatype
                        .block()
        ).getBody(); // Get the Body of the Response
        assert response != null;
        return response;
    }
    private List<MovieDTO> movieDbDTOResponse(String url){
        return  movieDbGETResponse(url)
                .results()//List of Movies
                .stream()
                .map(movie -> new MovieDTO(movie.id(),movie.titleText().text())) //Turn each Movie into MovieDTO
                .toList(); //Turn the Stream back to a List
    }

    public List<MovieDTO> getAllMovies(String url, int amount,int limit) {
        if(amount<=limit){
            return movieDbDTOResponse(url +"?limit="+amount);
        }

        int currentAmountofEntries = 0;
        String baseUrl = "https://moviesdatabase.p.rapidapi.com/titles";
        String currentUrl = url;

        while(currentAmountofEntries<amount && currentUrl!=null ) {
            if(amount-currentAmountofEntries<=limit){
                MovieDBResponse response = movieDbGETResponse(currentUrl + "&limit=" + amount);
                response.results().forEach(movie -> repo.getMapOfMovies().put(movie.id(), movie));
                currentAmountofEntries += amount;
            }
            else {
                MovieDBResponse response = movieDbGETResponse(currentUrl +"&limit=" + limit);
                response.results().forEach(movie -> repo.getMapOfMovies().put(movie.id(), movie));
                currentUrl = baseUrl + response.next();
                currentAmountofEntries += limit;
            }
        }

        return repo.getMapOfMovies()
                .values()
                .stream()
                .map(movie -> new MovieDTO(movie.id(), movie.titleText().text()))
                .toList();
    }
}
