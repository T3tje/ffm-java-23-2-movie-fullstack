
import { useState } from "react";
import React from 'react';
import Movie from "./MoviesType.ts";
import "./MovieItem.css";
import axios from "axios";
import { Dispatch, SetStateAction } from "react";

type MovieItemProps = {
    movie: Movie,
    onSelect: (movie: Movie) => void; // Hinzugefügt
    favorites: Movie[];
    setFavorites: Dispatch<SetStateAction<Movie[]>> | undefined;
};

const MovieItem:React.FC<MovieItemProps> = (props: MovieItemProps) => {
    const [isFavorite, setIsFavorite] = useState(
        props.favorites.some((favorite) => favorite.id === props.movie.id)
    );

    const addMovie = async (movie: Movie): Promise<void> => {
        try {
            const response = await axios.post(`/api/addfavorit`, movie);
            props.setFavorites ? props.setFavorites([...response.data]) : null;
            setIsFavorite(true);
            console.log("Film wurde hinzugefügt:", response.data);
        } catch (error) {
            console.error("Fehler beim Hinzufügen des Films:", error);
            throw error;
        }
    };

    const removeMovie = async (movie: Movie): Promise<void> => {
        try {
            await axios.delete(`/api/deletefavorit/${movie.id}`);
            const updatedFavorites:Movie[] = props.favorites.filter(
                (favorite:Movie):boolean => favorite.id !== movie.id
            );
            props.setFavorites ? props.setFavorites(updatedFavorites) : null;
            setIsFavorite(false);
            console.log("Film wurde aus Favoriten entfernt:", movie);
        } catch (error) {
            console.error("Fehler beim Entfernen des Films aus Favoriten:", error);
            throw error;
        }
    };

    const handleClickFav = () => {
        if (isFavorite) {
            removeMovie(props.movie);
        } else {
            addMovie(props.movie);
        }
    };
      
      const handleClick = () => {
        props.onSelect(props.movie);
    }

    return (
        <li className={"movie-item"} onClick={handleClick}>
            {props.movie.title}
            <span
                className="herz"
                onClick={(e) => {
                    e.stopPropagation(); // Verhindert, dass das Klick-Ereignis weitergeleitet wird
                    handleClickFav();
                }}
                style={{ color: isFavorite ? "#D2B593" : "initial" }}
            >
            ♥
        </span>
        </li>
    );
};

export default MovieItem;

