import "../../css/profileCard.css";
import { FaUserGraduate, FaUniversity, FaAward, FaBriefcase } from "react-icons/fa";

export default function ProfileCard({

    user = {},

    resumes = [],

    applications = [],

    skills = []

}) {

    const profileCompletion = () => {

        let score = 0;

        if (user?.fullName) score += 10;
        if (user?.email) score += 10;
        if (user?.phone) score += 10;
        if (user?.degree) score += 10;
        if (user?.college) score += 10;
        if (user?.cgpa) score += 10;
        if (user?.linkedin) score += 10;
        if (user?.github) score += 10;
        if (resumes.length > 0) score += 10;
        if (skills.length > 0) score += 10;

        return score;
    };

    return (

        <div className="profile-card">

            <img
                src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                alt="Profile"
            />

            <h2>{user?.fullName}</h2>

            <p>{user?.email}</p>

            <div className="progress-box">

                <span>Profile Completion</span>

                <div className="progress">

                    <div
                        className="progress-fill"
                        style={{ width: `${profileCompletion()}%` }}
                    />

                </div>

                <h4>{profileCompletion()}%</h4>

            </div>

            <div className="profile-details">

                <div>

                    <FaUserGraduate />

                    <span>{user?.degree || "Degree"}</span>

                </div>

                <div>

                    <FaUniversity />

                    <span>{user?.college || "College"}</span>

                </div>

                <div>

                    <FaAward />

                    <span>CGPA : {user?.cgpa || "--"}</span>

                </div>

                <div>

                    <FaBriefcase />

                    <span>{user?.experience || "Fresher"}</span>

                </div>

            </div>

            <div className="dashboard-small-cards">

                <div>

                    <h3>{resumes.length}</h3>

                    <span>Resume</span>

                </div>

                <div>

                    <h3>{applications.length}</h3>

                    <span>Applications</span>

                </div>

                <div>

                    <h3>{skills.length}</h3>

                    <span>Skills</span>

                </div>

            </div>

        </div>

    );

}
