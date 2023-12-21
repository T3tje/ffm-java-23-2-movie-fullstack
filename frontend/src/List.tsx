// List.tsx
import React, { useState } from "react";
import "./List.css";
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";
import MoviePopup from "./MoviePopup.tsx";

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


const List: React.FC<ListProps> = ({ movies, setMovies, increaseListLengthBy10 }) => {
    const [input, setInput] = useState("");
    const [selectedMovie, setSelectedMovie] = useState<Movie | null>(null);
    const [isPopupOpen, setIsPopupOpen] = useState(false);

    useEffect(() => {
        props.getAllFavorites(); // Hier wird die Funktion aufgerufen
    }, [props.favorites]);

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value);
    };

    const searchForFilm = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const response = await axios.get(`/api/search/${input}`);
            const { data } = response;


            if (data.length > 0 && props.setMovies) {
                props.setMovies(data);

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


    return(
        <>
            <div>
                {props.title !== "Favorites" ?
                    <form onSubmit={searchForFilm}>
                <input
                    type="text"
                    placeholder="Film suchen"
                    onChange={handleInput}
                    value={input}
                />
                <button>suchen</button>
            </form>: <h1></h1>
                    }

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
            <button id="mehrButton" onClick={increaseListLengthBy10}>mehr</button>

            {isPopupOpen && selectedMovie && (
                <MoviePopup movie={selectedMovie} onClose={handleClosePopup} />
            )}
        </>
    );
};

export default List;