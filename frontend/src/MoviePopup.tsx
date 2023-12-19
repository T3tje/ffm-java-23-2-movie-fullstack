import React from 'react';
import Movie from "./MoviesType.ts";

type MoviePopupProps = {
    movie: Movie;
    onClose: () => void;
};

const MoviePopup: React.FC<MoviePopupProps> = ({ movie, onClose }) => {
    return (
        <div className="popup">
            <div className="popup-inner">
                <button className="close-btn" onClick={onClose}>schließen</button>
                {/* Hier die Movie-Informationen anzeigen */}
                <h1>{movie.title}</h1>

                <p>Erscheinungsjahr {movie.year}</p>


                {/* Weitere Details hier einfügen */}
            </div>
        </div>
    );
}

export default MoviePopup;