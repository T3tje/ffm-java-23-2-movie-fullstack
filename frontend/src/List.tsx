import "./List.css"
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";
import {Dispatch, SetStateAction, useState} from "react";
import axios from "axios";

type ListProps = {
    movies: Movie[],
    setMovies: Dispatch<SetStateAction<Movie[]>>
}
export default function List(props: ListProps) {

    const [input, setInput] = useState("")

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value);
    }

    const searchForFilm = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        try {
            const response = await axios.get(`/api/search/${input}`);
            const { data } = response;

            if (data.length > 0) {
                props.setMovies(data);
            } else {
                console.log("Keine Filme gefunden");
            }
        } catch (error) {
            console.error("Fehler beim Abrufen der Filmliste:", error);
        }
    };


    return(
        <>
            <div>
                <form onSubmit={searchForFilm}>
                    <input
                        type="text"
                        placeholder="Film suchen"
                        onChange={handleInput}
                        value={input}
                    />
                    <button>suchen</button>
                </form>
            </div>
            <div id="movie-list">
                <ul>
                    {
                        props.movies.map(movie => {
                            return <MovieItem key={movie.id} movie={movie} />
                        })
                    }
                </ul>
            </div>
        </>
    )
}