import Navigation from "./Navigation.tsx";
import "./Header.css"

export default function Header() {

    return(
        <div id="header">
            <img id="logo" src="../public/logo.png" alt="logo"/>
            <Navigation/>
        </div>
    )
}