import "../css/header.css";

import { useNavigate } from "react-router-dom";

export default function Header(){

    const navigate = useNavigate();

    const fullName = sessionStorage.getItem("fullName");

    function logout(){

        sessionStorage.clear();

        navigate("/login");

    }

    return(

        <header className="header">

            <div className="logo">

                CareerCrafter

            </div>

            <div className="user-box">

                <h3>

                    Welcome {fullName}

                </h3>

                <button
                    className="logout-btn"
                    onClick={logout}>

                    Logout

                </button>

            </div>

        </header>

    );

}
