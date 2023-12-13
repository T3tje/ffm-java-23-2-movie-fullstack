import Navigation from "./Navigation.tsx";

type HeaderProps = {
    getAllMovies: () => void
}

export default function Header(props:HeaderProps) {

    return(
        <div id="header">
            <img src="../public/logo.png" alt="logo"/>
            <Navigation getAllMovies={props.getAllMovies}/>
        </div>
    )
}