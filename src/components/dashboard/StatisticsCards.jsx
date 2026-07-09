import "../../css/dashboard.css";
import {
    FaBriefcase,
    FaFileAlt,
    FaBell,
    FaClipboardList
} from "react-icons/fa";

export default function StatisticsCards({

    jobs,
    applications,
    notifications,
    resumes

}) {

    return (

        <div className="stats-grid">

            <div className="stat-card jobs">

                <FaBriefcase className="stat-icon"/>

                <h4>Total Jobs</h4>

                <h2>{jobs}</h2>

            </div>

            <div className="stat-card apps">

                <FaClipboardList className="stat-icon"/>

                <h4>Applications</h4>

                <h2>{applications}</h2>

            </div>

            <div className="stat-card resume">

                <FaFileAlt className="stat-icon"/>

                <h4>Resume</h4>

                <h2>

                    {

                        resumes > 0

                        ?

                        "Uploaded"

                        :

                        "Not Uploaded"

                    }

                </h2>

            </div>

            <div className="stat-card notify">

                <FaBell className="stat-icon"/>

                <h4>Notifications</h4>

                <h2>{notifications}</h2>

            </div>

        </div>

    );

}
