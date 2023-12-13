
type HeaderProps = {
    getAllMovies: () => void
}

export default function Navigation(props:HeaderProps) {

    return(
        <>
        <div id="navigation">
            <button onClick={() => props.getAllMovies()}>All Films</button>
            <button>Favorites</button>
            </div>
        </>
    )
}