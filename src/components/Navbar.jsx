import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {

    const navigate = useNavigate();

    const role = sessionStorage.getItem("role");

    const fullName = sessionStorage.getItem("fullName");

    const logout = () => {

        sessionStorage.clear();

        navigate("/login");

    };

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">

            <div className="container">

                <Link className="navbar-brand fw-bold" to="/">

                    CareerCrafter

                </Link>

                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav">

                    <span className="navbar-toggler-icon"></span>

                </button>

                <div className="collapse navbar-collapse" id="navbarNav">

                    <ul className="navbar-nav me-auto">

                        {
                            role === "JOB_SEEKER" &&

                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/user-dashboard">
                                        Dashboard
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/jobs">
                                        Jobs
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/resume">
                                        Resume
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/skills">
                                        Skills
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/preferences">
                                        Preferences
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/applications">
                                        Applications
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/notifications">
                                        Notifications
                                    </Link>
                                </li>
                            </>
                        }

                        {
                            role === "EMPLOYER" &&

                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/employer-dashboard">
                                        Dashboard
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/add-job">
                                        Add Job
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/manage-jobs">
                                        Manage Jobs
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/applications">
                                        Applicants
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/notifications">
                                        Notifications
                                    </Link>
                                </li>
                            </>
                        }

                    </ul>

                    <span className="text-white me-3">

                        Welcome, {fullName}

                    </span>

                    <button
                        className="btn btn-light"
                        onClick={logout}>

                        Logout

                    </button>

                </div>

            </div>

        </nav>

    );

}
