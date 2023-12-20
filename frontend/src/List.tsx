// List.tsx
import React, { useState } from "react";
import "./List.css";
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";
import MoviePopup from "./MoviePopup.tsx";
import { Dispatch, SetStateAction } from "react";
import axios from "axios";

type ListProps = {
    movies: Movie[];
    setMovies: Dispatch<SetStateAction<Movie[]>>;
    increaseListLengthBy10: () => void;
};

const List: React.FC<ListProps> = ({ movies, setMovies, increaseListLengthBy10 }) => {
    const [input, setInput] = useState("");
    const [selectedMovie, setSelectedMovie] = useState<Movie | null>(null);
    const [isPopupOpen, setIsPopupOpen] = useState(false);

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value);
    };

    const searchForFilm = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const response = await axios.get(`/api/search/${input}`);
            const { data } = response;
            if (data.length > 0) {
                setMovies(data);
            } else {
                console.log("Keine Filme gefunden");
            }
        } catch (error) {
            console.error("Fehler beim Abrufen der Filmliste:", error);
        }
    };

    const handleSelectMovie = (movie: Movie) => {
        setSelectedMovie(movie);
        setIsPopupOpen(true);
    };

    const handleClosePopup = () => {
        setIsPopupOpen(false);
    };

    return (
        <>
            <div>
                <form onSubmit={searchForFilm}>
                    <input
                        type="text"
                        placeholder="Film suchen"
                        value={input}
                        onChange={handleInput}
                    />
                    <button type="submit">suchen</button>
                </form>
            </div>
            <div id="movie-list">
                <ul>
                    {movies.map(movie => (
                        <MovieItem key={movie.id} movie={movie} onSelect={handleSelectMovie} />
                    ))}
                </ul>
            </div>
            <button id="mehrButton" onClick={increaseListLengthBy10}>mehr</button>

            {isPopupOpen && selectedMovie && (
                <MoviePopup movie={selectedMovie} onClose={handleClosePopup} />
            )}
        </>
    );
};

export default List;