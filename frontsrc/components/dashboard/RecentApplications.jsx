import "../../css/recentApplications.css";

export default function RecentApplications({ applications }) {

    const filteredApps = (applications || [])
        .slice(-3)
        .reverse();

    return (

        <div className="recent-applications">

            <div className="card-header">

                <h2>Recent Applications</h2>

            </div>

            <div className="table-container">

                <table className="dashboard-table">

                    <thead>

                        <tr>

                            <th>Application ID</th>
                            <th>Job ID</th>
                            <th>Company Name</th>
                            <th>Job Title</th>
                            <th>Application Status</th>
                            <th>Application Date</th>

                        </tr>

                    </thead>

                    <tbody>

                        {
                            filteredApps.length === 0 ?

                                <tr>

                                    <td colSpan="6">

                                        No Applications Found

                                    </td>

                                </tr>

                                :

                                filteredApps.map((application) => (

                                    <tr key={application.applicationId}>

                                        <td>{application.applicationId}</td>

                                        <td>{application.jobId}</td>

                                        <td>{application.companyName || "N/A"}</td>

                                        <td>{application.jobTitle || "N/A"}</td>

                                        <td>

                                            <span className={`status-badge ${application.status.toLowerCase().replace(" ", "-")}`} style={{ color: "#000000", fontWeight: "bold" }}>

                                                {application.status}

                                            </span>

                                        </td>

                                        <td>{application.appliedDate || "N/A"}</td>

                                    </tr>

                                ))

                        }

                    </tbody>

                </table>

            </div>

        </div>

    );

}
