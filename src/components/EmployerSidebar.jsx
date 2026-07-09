import { NavLink } from "react-router-dom";

import {
    FaHome,
    FaPlusCircle,
    FaBriefcase,
    FaUsers,
    FaBuilding,
    FaSignOutAlt,
    FaBell
} from "react-icons/fa";

import "../css/sidebar.css";

export default function EmployerSidebar() {

    return (

        <div className="sidebar">

            <h2>CareerCrafter</h2>

            <NavLink to="/employer-dashboard">

                <FaHome />

                Dashboard

            </NavLink>

            <NavLink to="/post-job">

                <FaPlusCircle />

                Post Job

            </NavLink>

            <NavLink to="/my-jobs">

                <FaBriefcase />

                My Jobs

            </NavLink>

            <NavLink to="/applicants">

                <FaUsers />

                Applicants

            </NavLink>



            <NavLink to="/send-notification">

                <FaBell />

                Send Notification

            </NavLink>

            <NavLink to="/login" onClick={() => sessionStorage.clear()}>

                <FaSignOutAlt />

                Logout

            </NavLink>

        </div>

    );

}
