import "./List.css"
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";

type ListProps = {
    movies : Movie[]
}
export default function List(props: ListProps) {

    return(

        <ul className="movie-list">
            {

            props.movies.map(movie => {
                return <MovieItem key={movie.id} movie={movie} />
            })
        }
        </ul>

    )
}