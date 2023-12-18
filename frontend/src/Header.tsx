import Navigation from "./Navigation.tsx";
import "./Header.css"

type HeaderProps = {
    listAmount: number,
    getAllMovies: (amount:number) => void
}

export default function Header(props:HeaderProps) {

    return(
        <div id="header">
            <img id="logo" src="../public/logo.png" alt="logo"/>
            <Navigation listAmount={props.listAmount} getAllMovies={props.getAllMovies}/>
        </div>
    )
}