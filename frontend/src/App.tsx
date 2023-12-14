import './App.css'
import Header from "./Header.tsx";
import List from "./List.tsx";
import {useEffect, useState} from "react";
import Movie from "./MoviesType.ts";
import axios from "axios";

function App() {

    const [movies, setMovies] = useState<Movie[]>([])
    const [listAmount, setListAmount] = useState(10)

    const getAllMovies = async (): Promise<void> => {
        try {
            const response = await axios.get(`/api?entries=${listAmount}`);
            const { data } = response;

            if (data.length > 0) {
                setMovies(data);
            } else {
                console.log("Keine Filme gefunden");
            }
            return
        } catch (error) {
            console.error("Fehler beim Abrufen der Filmliste:", error);
            return
        }
    };

    useEffect(() => {
        getAllMovies();
    }, []);


    const increaseListLengthBy10 = () => {
        setListAmount(listAmount + 10)
        getAllMovies();
    }


    return (
    <>
        <Header getAllMovies={getAllMovies}/>
        <List movies={movies} setMovies={setMovies} increaseListLengthBy10={increaseListLengthBy10}/>
    </>
  )
}

export default App
