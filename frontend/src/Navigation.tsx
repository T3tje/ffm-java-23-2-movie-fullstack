
import { Link } from "react-router-dom";

export default function Navigation() {

    return(

        <div id="navigation">
            <Link to="/" ><button>All Movies</button></Link>
            <Link to="/favorites"><button>Favorites</button></Link>
        </div>

    )
}