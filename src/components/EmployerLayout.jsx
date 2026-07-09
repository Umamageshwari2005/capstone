import Header from "./Header";
import EmployerSidebar from "./EmployerSidebar";
import { Outlet } from "react-router-dom";
import "../css/layout.css";

export default function EmployerLayout() {

    return (

        <>
            <Header />

            <div className="layout-container">

                <EmployerSidebar />

                <main className="main-content">

                    <Outlet />

                </main>

            </div>

        </>

    );

}
