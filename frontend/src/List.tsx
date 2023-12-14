import "./List.css"
import Movie from "./MoviesType.ts";
import MovieItem from "./MovieItem.tsx";

type ListProps = {
    movies : Movie[]
}
export default function List(props: ListProps) {

    return(
        <>
            <div>
                <input
                    type="text"
                    placeholder="Film suchen"
                />
                <button>suchen</button>
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