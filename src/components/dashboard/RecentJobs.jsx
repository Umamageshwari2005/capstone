import "../css/recentJobs.css";

export default function RecentJobs({ jobs }) {

    return (

        <div className="dashboard-card">

            <div className="card-header">

                <h2>Latest Jobs</h2>

            </div>

            <div className="table-container">

                <table className="dashboard-table">

                    <thead>

                        <tr>

                            <th>Job Title</th>

                            <th>Company</th>

                            <th>Location</th>

                            <th>Type</th>

                            <th>Salary</th>

                        </tr>

                    </thead>

                    <tbody>

                        {jobs.length === 0 ? (

                            <tr>

                                <td colSpan="5">

                                    No Jobs Available

                                </td>

                            </tr>

                        ) : (

                            jobs.map((job) => (

                                <tr key={job.jobId}>

                                    <td>{job.jobTitle}</td>

                                    <td>{job.companyName}</td>

                                    <td>{job.location}</td>

                                    <td>{job.jobType}</td>

                                    <td>₹ {job.salary}</td>

                                </tr>

                            ))

                        )}

                    </tbody>

                </table>

            </div>

        </div>

    );

}
