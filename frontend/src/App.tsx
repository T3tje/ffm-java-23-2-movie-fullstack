import './App.css'
import Header from "./Header.tsx";
import List from "./List.tsx";
import {useEffect, useState} from "react";
import Movie from "./MoviesType.ts";
import axios from "axios";

function App() {

    const [movies, setMovies] = useState<Movie[]>([])

    useEffect(() => getAllMovies, [])
    const getAllMovies = ():void => {
        axios.get("/api")
            .then(response => {
                response.data.length > 0 ? setMovies(response.data) : console.log("Keine Filme gefunden")
            }).catch(error => console.log(error))
    }

  return (
    <>
        <Header getAllMovies={getAllMovies}/>
        <List movies={movies}/>
    </>
  )
}

export default App
