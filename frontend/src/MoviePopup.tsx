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
                <button className="close-btn" onClick={onClose}>Schließen</button>
                {/* Hier die Movie-Informationen anzeigen */}
                <h2>{movie.title}</h2>
                <img src={movie.url} />
                <h3>Erscheinungsjahr {movie.year}</h3>


                {/* Weitere Details hier einfügen */}
            </div>
        </div>
    );
}

export default MoviePopup;