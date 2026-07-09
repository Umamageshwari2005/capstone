import { Routes, Route, Navigate } from "react-router-dom";

// Authentication
import Login from "./pages/Login";
import Register from "./pages/Register";

// Layouts
import MainLayout from "./components/MainLayout";
import EmployerLayout from "./components/EmployerLayout";

// User Pages
import UserDashboard from "./pages/UserDashboard";
import Jobs from "./pages/Jobs";
import Resume from "./pages/Resume";
import Skills from "./pages/Skills";
import Applications from "./pages/Applications";
import Notifications from "./pages/Notifications";
import Profile from "./pages/Profile";

// Employer Pages
import EmployerDashboard from "./pages/EmployerDashboard";
import PostJob from "./pages/PostJob";
import MyJobs from "./pages/MyJobs";
import Applicants from "./pages/Applicants";
import EditJob from "./pages/EditJob";
import SendNotification from "./pages/SendNotification";

export default function App() {

    return (

        <Routes>

            {/* Default Route */}

            <Route
                path="/"
                element={<Navigate to="/login" />}
            />

            {/* Authentication */}

            <Route
                path="/login"
                element={<Login />}
            />

            <Route
                path="/register"
                element={<Register />}
            />

            {/* ================= USER MODULE ================= */}

            <Route element={<MainLayout />}>

                <Route
                    path="/user-dashboard"
                    element={<UserDashboard />}
                />

                <Route
                    path="/jobs"
                    element={<Jobs />}
                />

                <Route
                    path="/resume"
                    element={<Resume />}
                />

                <Route
                    path="/skills"
                    element={<Skills />}
                />

                <Route
                    path="/applications"
                    element={<Applications />}
                />

                <Route
                    path="/notifications"
                    element={<Notifications />}
                />

                <Route
                    path="/profile"
                    element={<Profile />}
                />

            </Route>

            {/* ================= EMPLOYER MODULE ================= */}

            <Route element={<EmployerLayout />}>

                <Route
                    path="/employer-dashboard"
                    element={<EmployerDashboard />}
                />

                <Route
                    path="/post-job"
                    element={<PostJob />}
                />

                <Route
                    path="/my-jobs"
                    element={<MyJobs />}
                />

                <Route
                    path="/applicants"
                    element={<Applicants />}
                />



                <Route
                    path="/send-notification"
                    element={<SendNotification />}
                />

            </Route>
            <Route

    path="/edit-job/:jobId"

    element={<EditJob/>}

/>

<Route

    path="/applicants/:jobId"

    element={<Applicants/>}

/>

        </Routes>

    );

}
