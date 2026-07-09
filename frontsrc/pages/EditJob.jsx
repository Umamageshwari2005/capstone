import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import JobService from "../services/JobService";
import "../css/postJob.css";

export default function EditJob() {
    const { jobId } = useParams();
    const navigate = useNavigate();

    const [job, setJob] = useState({
        jobTitle: "",
        companyName: "",
        description: "",
        location: "",
        salary: "",
        experience: "",
        jobType: "",
        requiredSkills: "",
        employerId: sessionStorage.getItem("userId"),
        companyEmail: "",
        companyPhone: "",
        companyAddress: "",
        companyWebsite: "",
        companyDescription: ""
    });

    useEffect(() => {
        loadJob();
    }, []);

    async function loadJob() {
        try {
            const response = await JobService.getJobById(jobId);
            setJob(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    function handleChange(e) {
        setJob({
            ...job,
            [e.target.name]: e.target.value
        });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        try {
            const payload = {
                ...job,
                salary: job.salary ? parseFloat(job.salary) : 0.0,
                employerId: job.employerId ? parseInt(job.employerId) : null
            };
            await JobService.updateJob(jobId, payload);
            alert("Job Updated Successfully");
            navigate("/my-jobs");
        } catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Unable to Update Job";
            alert("Unable to Update Job: " + msg);
        }
    }

    return (
        <div className="post-job-page" style={{ maxWidth: "800px", margin: "20px auto", padding: "20px" }}>
            <h1 style={{ borderBottom: "2px solid #4f46e5", paddingBottom: "10px", color: "#4f46e5", marginBottom: "25px" }}>Edit Job</h1>
            <form className="post-job-form" onSubmit={handleSubmit}>
                
                <h3 className="full-width" style={{ color: "#4f46e5", borderBottom: "1px solid #e2e8f0", paddingBottom: "8px", marginTop: "0" }}>Job Information</h3>
                
                <div className="form-group">
                    <label>Job Title / Role</label>
                    <input
                        type="text"
                        name="jobTitle"
                        value={job.jobTitle}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group full-width">
                    <label>Job Description</label>
                    <textarea
                        name="description"
                        rows="4"
                        value={job.description}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Location</label>
                    <input
                        type="text"
                        name="location"
                        value={job.location}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Salary Package (LPA / ₹)</label>
                    <input
                        type="number"
                        name="salary"
                        value={job.salary}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Experience Needed (e.g. Fresher, 2+ Years)</label>
                    <input
                        type="text"
                        name="experience"
                        value={job.experience}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Job Type</label>
                    <select
                        name="jobType"
                        value={job.jobType}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Select Job Type</option>
                        <option value="Full Time">Full Time</option>
                        <option value="Part Time">Part Time</option>
                        <option value="Internship">Internship</option>
                        <option value="Remote">Remote</option>
                    </select>
                </div>

                <div className="form-group">
                    <label>Required Skills</label>
                    <input
                        type="text"
                        name="requiredSkills"
                        value={job.requiredSkills}
                        onChange={handleChange}
                        required
                    />
                </div>

                <h3 className="full-width" style={{ color: "#4f46e5", borderBottom: "1px solid #e2e8f0", paddingBottom: "8px", marginTop: "30px" }}>Company Information</h3>

                <div className="form-group">
                    <label>Company Name</label>
                    <input
                        type="text"
                        name="companyName"
                        value={job.companyName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Contact Email</label>
                    <input
                        type="email"
                        name="companyEmail"
                        value={job.companyEmail || ""}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Contact Phone</label>
                    <input
                        type="text"
                        name="companyPhone"
                        value={job.companyPhone || ""}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Address</label>
                    <input
                        type="text"
                        name="companyAddress"
                        value={job.companyAddress || ""}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Website</label>
                    <input
                        type="text"
                        name="companyWebsite"
                        value={job.companyWebsite || ""}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group full-width">
                    <label>Company Description / Details</label>
                    <textarea
                        name="companyDescription"
                        rows="4"
                        value={job.companyDescription || ""}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="button-group" style={{ marginTop: "20px" }}>
                    <button type="submit" style={{ backgroundColor: "#4f46e5", color: "white", padding: "10px 20px", border: "none", borderRadius: "6px", cursor: "pointer", fontSize: "16px", fontWeight: "bold" }}>
                        Update Job
                    </button>
                    <button
                        type="button"
                        onClick={() => navigate("/my-jobs")}
                        style={{ backgroundColor: "#64748b", color: "white", padding: "10px 20px", border: "none", borderRadius: "6px", cursor: "pointer", fontSize: "16px", fontWeight: "bold", marginLeft: "10px" }}
                    >
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
}
