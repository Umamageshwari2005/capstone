import Header from "./Header";
import Sidebar from "./Sidebar";
import { Outlet } from "react-router-dom";
import "../css/layout.css";

export default function MainLayout() {

    return (

        <>
            <Header />

            <div className="layout-container">

                <Sidebar />

                <main className="main-content">

                    <Outlet />

                </main>

            </div>

        </>

    );

}
