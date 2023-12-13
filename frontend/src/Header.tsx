import Navigation from "./Navigation.tsx";
import "./Header.css"

type HeaderProps = {
    getAllMovies: () => void
}

export default function Header(props:HeaderProps) {

    return(
        <div id="header">
            <img id="logo" src="../public/logo.png" alt="logo"/>
            <Navigation getAllMovies={props.getAllMovies}/>
        </div>
    )
}