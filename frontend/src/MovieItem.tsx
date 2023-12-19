import React from 'react';
import Movie from "./MoviesType.ts";
import "./MovieItem.css";

type MovieItemProps = {
    movie: Movie;
    onSelect: (movie: Movie) => void; // Hinzugefügt
};

const MovieItem: React.FC<MovieItemProps> = (props) => {
    const handleClick = () => {
        props.onSelect(props.movie);
    }

    return (
        <li className={"movie-item"} onClick={handleClick}>
            {props.movie.title}
        </li>
    );
};

export default MovieItem;