import "./List.css"
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";
import {Dispatch, SetStateAction, useEffect, useState} from "react";
import axios from "axios";

type ListProps = {
    itemsForList: Movie[];
    setMovies?: Dispatch<SetStateAction<Movie[]>>;
    increaseListLengthBy10: () => void;
    setFavorites?: Dispatch<SetStateAction<Movie[]>>;
    title: string;
    favorites: Movie[];
    getAllFavorites: () => Promise<void>; // Hier wird die Funktion definiert
};


export default function List(props: ListProps) {

    const [input, setInput] = useState("")

    useEffect(() => {
        props.getAllFavorites(); // Hier wird die Funktion aufgerufen
    }, [props.favorites]);

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value);
    }
    const searchForFilm = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        try {
            const response = await axios.get(`/api/search/${input}`);
            const { data } = response;

            if (data.length > 0 && props.setMovies) {
                props.setMovies(data);
            } else {
                console.log("Keine Filme gefunden");
            }
            return
        } catch (error) {
            console.error("Fehler beim Abrufen der Filmliste:", error);
            return
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
                    <h2 id="listTitle">{props.title}</h2>
                    {
                        props.itemsForList.map(movie => {
                            return (
                            <MovieItem
                                key={movie.id}
                                movie={movie}
                                favorites={props.favorites}
                                setFavorites={props.setFavorites}
                            />
                            )
                        })
                    }
                </ul>
            </div>
            <button id="mehrButton" onClick={props.increaseListLengthBy10}>mehr</button>
        </>
    )
}