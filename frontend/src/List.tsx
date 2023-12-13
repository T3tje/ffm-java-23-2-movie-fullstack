import "./List.css"
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";

type ListProps = {
    movies : Movie[]
}
export default function List(props: ListProps) {

    return(
        <ul>
            {
            props.movies.map(movie => {
                return <MovieItem movie={movie} />
            })
        }
        </ul>

        /*
        <ul id="movie-list">
            <li className="movie-item">The Shawshank Redemption</li>
            <li className="movie-item">The Godfather</li>
            <li className="movie-item">The Dark Knight</li>
            <li className="movie-item">Pulp Fiction</li>
            <li className="movie-item">Schindler's List</li>
        </ul>

         */
    )
}