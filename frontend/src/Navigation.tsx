
type HeaderProps = {
    listAmount: number,
    getAllMovies: (amount:number) => void
}

export default function Navigation(props:HeaderProps) {

    return(

        <div id="navigation">
            <button onClick={() => props.getAllMovies(props.listAmount)}>All Films</button>
            <button>Favorites</button>
        </div>

    )
}