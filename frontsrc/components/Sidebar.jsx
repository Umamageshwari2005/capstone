import { NavLink } from "react-router-dom";
import {
    FaHome,
    FaBriefcase,
    FaFileAlt,
    FaTools,
    FaClipboardList,
    FaBell,
    FaUser,
    FaSignOutAlt
} from "react-icons/fa";

import "../css/sidebar.css";

export default function Sidebar() {

    return (

        <div className="sidebar">

            <h2>CareerCrafter</h2>

            <NavLink to="/user-dashboard">
                <FaHome /> Dashboard
            </NavLink>

            <NavLink to="/jobs">
                <FaBriefcase /> Jobs
            </NavLink>

            <NavLink to="/resume">
                <FaFileAlt /> Resume
            </NavLink>

            <NavLink to="/skills">
                <FaTools /> Skills
            </NavLink>

            <NavLink to="/applications">
                <FaClipboardList /> Applications
            </NavLink>

            <NavLink to="/notifications">
                <FaBell /> Notifications
            </NavLink>

            <NavLink to="/profile">
                <FaUser /> Profile
            </NavLink>

            <NavLink to="/login" onClick={() => sessionStorage.clear()}>
                <FaSignOutAlt /> Logout
            </NavLink>

        </div>

    );

}
