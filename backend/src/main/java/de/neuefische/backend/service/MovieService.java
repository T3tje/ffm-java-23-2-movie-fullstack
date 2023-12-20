package de.neuefische.backend.service;


import de.neuefische.backend.model.*;
import de.neuefische.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MovieService {
    @Value("${API_KEY}")
    private String apiKey;

    private final MovieRepository repo;




    public List<MovieSortDTO> findMoviesByTitle(String title,  int entries, int limit) {
        repo.setMapOfMoviesExtendedForSearch(new HashMap<String,MovieExtendedInfo>());

        int currentAmountofEntries = 0;
        if(entries<limit){
            MovieExtendedInfoDBResponse response = movieExtendedInfoDbGETResponse("https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title);
            response.results().
                    forEach(movieExtendedInfo -> repo.getMapOfMoviesExtendedForSearch().put(
                            movieExtendedInfo.id(),
                            movieExtendedInfo));
            currentAmountofEntries += entries;


        }


        String baseUrl = "https://moviesdatabase.p.rapidapi.com";
        String currentUrl = "https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title;

        while(currentAmountofEntries<entries && currentUrl!=null ) {

            if(currentAmountofEntries == 0){

                currentUrl = moviePutInListSearch(baseUrl,"https://moviesdatabase.p.rapidapi.com/titles/search/title/" + title);
                currentAmountofEntries += limit;

            }

            if(entries-currentAmountofEntries<limit){

                currentUrl=moviePutInListSearch(baseUrl,currentUrl);
                currentAmountofEntries += limit;
            }
            else {

                currentUrl = moviePutInListSearch(baseUrl,currentUrl);
                currentAmountofEntries += limit;
            }
        }

        List<MovieSortDTO> list = new java.util.ArrayList<>(repo.getMapOfMoviesExtendedForSearch()
                .values()
                .stream()
                .map(movie -> new MovieSortDTO(
                        movie.id(),
                        movie.titleText().text(),
                        movie.ratingsSummary().orElse(
                                new RatingsSummary(0.0,0)
                        ).aggregateRating(),
                        movie.releaseYear().year(),
                        movie.primaryImage().orElse(new Image(
                                " ",0,0,"",new Caption(""))).url()
                ))
                .toList());
        sortingMovieSortDTOList(list);
        return list;
    }

    public List<MovieSortDTO> findMoviesByKeyword(String keyword) {

        return movieSortDTOResponse("https://moviesdatabase.p.rapidapi.com/titles/search/keyword/" + keyword);
    }

    private MovieExtendedInfoDBResponse movieExtendedInfoDbGETResponse(String url){
        MovieExtendedInfoDBResponse response = Objects.requireNonNull(
                WebClient.create()  //Creates WebClient.
                        .get()  //Sends GET Request to...
                        .uri(url)   //...this URL with the following header:
                        .header("X-RapidAPI-Key", apiKey) //header of the GET-Request.
                        .retrieve() //Retrieve Data from the Response.
                        .toEntity(MovieExtendedInfoDBResponse.class) //Turns it into the desired Datatype.
                        .block()
        ).getBody(); // Get the Body of the Response.
        assert response != null;
        return response;
    }

    private List<MovieSortDTO> movieSortDTOResponse(String url){
        return  movieExtendedInfoDbGETResponse(url)
                .results()  //List of MovieExtendedInfo Objects
                .stream()
                .map(movieExtendedInfo -> new MovieSortDTO(
                        movieExtendedInfo.id(),
                        movieExtendedInfo.titleText().text(),
                        movieExtendedInfo.ratingsSummary().orElse(
                                new RatingsSummary(0.0,0)
                        ).aggregateRating(),
                        movieExtendedInfo.releaseYear().year(),
                        movieExtendedInfo.primaryImage().orElse(new Image(
                                " ",0,0,"",new Caption(""))).url()))   //Turn each Movie into MovieSortDTO.
                .toList(); //Turn the Stream back to a List.
    }

    public List<MovieSortDTO> getAllMovies(String url, int entries, int limit) {

        int currentAmountofEntries = 0;
        if(entries<limit){
            MovieExtendedInfoDBResponse response = movieExtendedInfoDbGETResponse(url + "/titles?limit=" + entries +"&info=base_info");
            response.results().
                    forEach(movieExtendedInfo -> repo.getMapOfMoviesWithExtendedInfo().put(
                    movieExtendedInfo.id(),
                    movieExtendedInfo));
            currentAmountofEntries += entries;


        }


        String baseUrl = "https://moviesdatabase.p.rapidapi.com";
        String currentUrl = url;

        while(currentAmountofEntries<entries && currentUrl!=null ) {

            if(currentAmountofEntries == 0){

                currentUrl = moviePutInList(baseUrl,baseUrl + "/titles?limit=" + limit +"&info=base_info");
                currentAmountofEntries += limit;

            }

            if(entries-currentAmountofEntries<limit){

                currentUrl=moviePutInList(baseUrl,currentUrl);
                currentAmountofEntries += limit;
            }
            else {

                currentUrl = moviePutInList(baseUrl,currentUrl);
                currentAmountofEntries += limit;
            }
        }

        List<MovieSortDTO> list = new java.util.ArrayList<>(repo.getMapOfMoviesWithExtendedInfo()
                .values()
                .stream()
                .map(movie -> new MovieSortDTO(
                        movie.id(),
                        movie.titleText().text(),
                        movie.ratingsSummary().orElse(
                                new RatingsSummary(0.0,0)
                        ).aggregateRating(),
                        movie.releaseYear().year(),
                        movie.primaryImage().orElse(new Image(
                                " ",0,0,"",new Caption(""))).url()
                ))
                .toList());
        sortingMovieSortDTOList(list);
        return list;
    }

    public static void sortingMovieSortDTOList(List<MovieSortDTO> list){
        list.sort((o1, o2) -> {
            if(o1.rating()>o2.rating()){
                return -1;
            } else if (o2.rating()>o1.rating()) {
                return 1;

            }
            return 0;
        });

    }

    public  String moviePutInList(String baseUrl, String currentUrl){
        MovieExtendedInfoDBResponse response = movieExtendedInfoDbGETResponse(currentUrl);
        response
                .results()
                .forEach(movie -> repo.getMapOfMoviesWithExtendedInfo().put(movie.id(), movie));
        currentUrl = baseUrl + response.next();
        return currentUrl;
    }

    public String moviePutInListSearch(String baseUrl, String currentUrl){
        MovieExtendedInfoDBResponse response = movieExtendedInfoDbGETResponse(currentUrl);
        response
                .results()
                .forEach(movie -> repo.getMapOfMoviesExtendedForSearch().put(movie.id(), movie));
        currentUrl = baseUrl + response.next();
        return currentUrl;
    }
}
