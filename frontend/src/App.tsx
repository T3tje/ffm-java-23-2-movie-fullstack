import './App.css'
import Header from "./Header.tsx";
import List from "./List.tsx";
import {useEffect, useState} from "react";
import Movie from "./MoviesType.ts";
import axios from "axios";
import {Route, Routes} from "react-router-dom";

function App() {

    const [movies, setMovies] = useState<Movie[]>([])
    const [listAmount, setListAmount] = useState(10)
    const [favAmount, setFavAmount] = useState(10)
    const [favorites, setFavorites] = useState<Movie[]>([])

    const getAllMovies = async (amount:number): Promise<void> => {
        try {
            const response = await axios.get(`/api?entries=${amount}`);
            const { data } = response;

            if (data.length > 0) {
                console.log(data)
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

    const getAllFavorites = async (): Promise<void> => {
        try {
            const response = await axios.get(`/api/favorites`);
            const { data } = response;

            if (data.length > 0) {
                setFavorites(data);
            } else {
                console.log("Keine Filme / Favorites gefunden");
            }
            return
        } catch (error) {
            console.error("Fehler beim Abrufen der Favoritenliste:", error);
            return
        }
    };

    useEffect(() => {
        getAllMovies(listAmount);
        getAllFavorites()
    }, []);


    const increaseAllListLengthBy10 = () => {
        const newAmount: number = listAmount + 10;
        setListAmount(newAmount)
        getAllMovies(newAmount);
    }

    const increaseFavListLengthBy10 = () : void => {
        const newAmount: number = favAmount + 10;
        setFavAmount(newAmount)
        getAllFavorites()
    }

    return (
    <>
        <Header/>
        <Routes>
            <Route
                path="/"
                element={
                    <List
                        itemsForList={movies}
                        setMovies={setMovies}
                        increaseListLengthBy10={increaseAllListLengthBy10}
                        title="All Movies"
                    />
                }
            />
            <Route
                path="/favorites"
                element={
                    <List
                        itemsForList={favorites}
                        setFavorites={setFavorites}
                        increaseListLengthBy10={increaseFavListLengthBy10}
                        title="Favorites"
                    />
                }
            />
        </Routes>

    </>
  )
}

export default App
