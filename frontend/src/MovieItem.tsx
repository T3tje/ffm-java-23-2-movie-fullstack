import Movie from "./MoviesType.ts";
import "./MovieItem.css";

type MovieItemProps = {
    movie:Movie;
};


const MovieItem = (props: MovieItemProps) => {

    return <li className={"movie-item"}>{props.movie.name}</li>
}

export default MovieItem
