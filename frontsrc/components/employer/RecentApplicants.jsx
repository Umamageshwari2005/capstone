import { useNavigate } from "react-router-dom";
import "../../css/employerDashboard.css";

export default function RecentApplicants({

    applicants

}) {

    const navigate = useNavigate();

    return (

        <div className="recent-applicants">

            <div className="section-header">

                <h2>

                    Recent Applicants

                </h2>

            </div>

            {

                applicants.length === 0 ?

                <div className="empty-box">

                    No Applicants Available

                </div>

                :

                <table className="applicant-table">

                    <thead>

                        <tr>

                            <th>Applicant Name</th>

                            <th>Job Applied For</th>

                            <th>Application Date</th>

                            <th>Application Status</th>

                            <th>Action</th>

                        </tr>

                    </thead>

                    <tbody>

                        {

                            applicants.map(applicant => (

                                <tr key={applicant.applicationId}>

                                    <td>

                                        {applicant.userName || applicant.fullName}

                                    </td>

                                    <td>

                                        {applicant.jobTitle}

                                    </td>

                                    <td>

                                        {applicant.appliedDate || "N/A"}

                                    </td>

                                    <td>

                                        <span className={`status-badge ${applicant.status.toLowerCase().replace(" ", "-")}`} style={{ color: "#000000", fontWeight: "bold" }}>

                                            {applicant.status}

                                        </span>

                                    </td>

                                    <td>

                                        <button className="view-btn" onClick={() => navigate("/applicants")}>

                                            View Profile

                                        </button>

                                    </td>

                                </tr>

                            ))

                        }

                    </tbody>

                </table>

            }

        </div>

    );

}
