import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import JobService from "../services/JobService";

import "../css/myJobs.css";

export default function MyJobs() {

    const navigate = useNavigate();

    const employerId = sessionStorage.getItem("userId");

    const [jobs, setJobs] = useState([]);

    const [searchId, setSearchId] = useState("");
    const [searchTitle, setSearchTitle] = useState("");
    const [searchLocation, setSearchLocation] = useState("");

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadJobs();

    }, []);

    async function loadJobs() {

        try {

            const response =
                await JobService.getJobsByEmployer(employerId);

            setJobs(response.data);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    }

    function editJob(jobId) {
        navigate(`/edit-job/${jobId}`);
    }

    async function deleteJob(jobId) {
        if (!window.confirm("Are you sure you want to delete this job?")) {
            return;
        }
        try {
            await JobService.deleteJob(jobId);
            alert("Job Deleted Successfully");
            loadJobs();
        } catch (error) {
            console.log(error);
            alert("Failed to Delete Job");
        }
    }

    function viewApplicants(jobId) {
        navigate(`/applicants/${jobId}`);
    }

    if (loading) {

        return (

            <h2>Loading Jobs...</h2>

        );

    }

    const filteredJobs = jobs.filter(job => {
        const matchId = searchId.trim() === "" || job.jobId.toString() === searchId.trim();
        const matchTitle = searchTitle.trim() === "" || job.jobTitle.toLowerCase().includes(searchTitle.toLowerCase());
        const matchLocation = searchLocation.trim() === "" || job.location.toLowerCase().includes(searchLocation.toLowerCase());
        return matchId && matchTitle && matchLocation;
    });

    return (

        <div
    className="my-jobs-page"
    style={{
        background: "linear-gradient(135deg,#dbeafe,#c7d2fe,#ddd6fe)",
        minHeight: "100vh",
        padding: "30px",
        borderRadius: "20px"
    }}
>

            <h1>

                My Posted Jobs

            </h1>

            <div className="search-filters" style={{ display: "flex", gap: "15px", marginBottom: "20px", flexWrap: "wrap", alignItems: "flex-end" }}>
                <div style={{ display: "flex", flexDirection: "column", gap: "5px" }}>
                    <label style={{ fontWeight: "bold", fontSize: "0.9rem", color: "#334155" }}>Search Job by ID</label>
                    <input
                        type="text"
                        placeholder="Enter Job ID"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                        style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #cbd5e1" }}
                    />
                </div>
                <div style={{ display: "flex", flexDirection: "column", gap: "5px" }}>
                    <label style={{ fontWeight: "bold", fontSize: "0.9rem", color: "#334155" }}>Search Job by Title</label>
                    <input
                        type="text"
                        placeholder="Enter Job Title"
                        value={searchTitle}
                        onChange={(e) => setSearchTitle(e.target.value)}
                        style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #cbd5e1" }}
                    />
                </div>
                <div style={{ display: "flex", flexDirection: "column", gap: "5px" }}>
                    <label style={{ fontWeight: "bold", fontSize: "0.9rem", color: "#334155" }}>Search Job by Location</label>
                    <input
                        type="text"
                        placeholder="Enter Job Location"
                        value={searchLocation}
                        onChange={(e) => setSearchLocation(e.target.value)}
                        style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #cbd5e1" }}
                    />
                </div>
                <button
                    onClick={() => {
                        setSearchId("");
                        setSearchTitle("");
                        setSearchLocation("");
                    }}
                    style={{ padding: "9px 15px", backgroundColor: "#6b7280", color: "white", border: "none", borderRadius: "6px", cursor: "pointer", fontWeight: "bold" }}
                >
                    Clear / View All
                </button>
            </div>

            {

                filteredJobs.length === 0 ?

                <div className="empty-box">

                    No Jobs Found

                </div>

                :

                <table className="my-jobs-table">

                    <thead>

                        <tr>

                            <th>Job ID</th>

                            <th>Job Title</th>

                            <th>Company</th>

                            <th>Location</th>

                            <th>Salary</th>

                            <th>Experience</th>

                            <th>Job Type</th>

                            <th>Actions</th>

                        </tr>

                    </thead>

                    <tbody>

                        {

                            filteredJobs.map(job => (

                                <tr key={job.jobId}>

                                    <td>{job.jobId}</td>

                                    <td>{job.jobTitle}</td>

                                    <td>{job.companyName}</td>

                                    <td>{job.location}</td>

                                    <td>₹ {job.salary}</td>

                                    <td>{job.experience}</td>

                                    <td>{job.jobType}</td>

                                    <td>

                                        <button
                                            className="edit-btn"
                                            onClick={() => editJob(job.jobId)}
                                        >

                                            Edit

                                        </button>

                                        <button
                                            className="delete-btn"
                                            onClick={() => deleteJob(job.jobId)}
                                        >

                                            Delete

                                        </button>

                                        <button
                                            className="view-btn"
                                            onClick={() => viewApplicants(job.jobId)}
                                        >

                                            Applicants

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
