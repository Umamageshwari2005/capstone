import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import JobService from "../services/JobService";
import UserService from "../services/UserService";
import "../css/postJob.css";

export default function PostJob() {
    const navigate = useNavigate();
    const employerId = sessionStorage.getItem("userId");

    const [job, setJob] = useState({
        jobTitle: "",
        companyName: "",
        description: "",
        location: "",
        salary: "",
        experience: "",
        jobType: "",
        requiredSkills: "",
        employerId: employerId,
        companyEmail: "",
        companyPhone: "",
        companyAddress: "",
        companyWebsite: "",
        companyDescription: ""
    });

    useEffect(() => {
        loadEmployerDetails();
    }, []);

    async function loadEmployerDetails() {
        try {
            const response = await UserService.getUser(employerId);
            const emp = response.data;
            setJob(prev => ({
                ...prev,
                companyName: emp.fullName || prev.companyName,
                companyEmail: emp.email || prev.companyEmail,
                companyPhone: emp.phone || prev.companyPhone,
                companyAddress: emp.address || prev.companyAddress,
                companyWebsite: emp.website || prev.companyWebsite,
                companyDescription: emp.description || prev.companyDescription
            }));
        } catch (error) {
            console.log("Failed to load employer details for defaults", error);
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
            await JobService.addJob(payload);
            alert("Job Posted Successfully!");
            navigate("/my-jobs");
        } catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Failed to Post Job";
            alert("Failed to Post Job: " + msg);
        }
    }

    function handleReset() {
        setJob({
            jobTitle: "",
            companyName: "",
            description: "",
            location: "",
            salary: "",
            experience: "",
            jobType: "",
            requiredSkills: "",
            employerId: employerId,
            companyEmail: "",
            companyPhone: "",
            companyAddress: "",
            companyWebsite: "",
            companyDescription: ""
        });
        loadEmployerDetails();
    }

    return (
        <div
    className="post-job-page"
    style={{
        maxWidth: "900px",
        margin: "20px auto",
        padding: "30px",
        background: "linear-gradient(135deg,#dbeafe,#c7d2fe,#ddd6fe)",
        borderRadius: "20px",
        boxShadow: "0 10px 30px rgba(0,0,0,0.12)"
    }}
>
           <h1
    style={{
        background: "linear-gradient(135deg,#60A5FA,#3B82F6)",
        color: "white",
        padding: "18px",
        borderRadius: "15px",
        textAlign: "center",
        marginBottom: "30px"
    }}
>Post New Job</h1>
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
                        placeholder="Java, Spring Boot, React"
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
                        value={job.companyEmail}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Contact Phone</label>
                    <input
                        type="text"
                        name="companyPhone"
                        value={job.companyPhone}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Address</label>
                    <input
                        type="text"
                        name="companyAddress"
                        value={job.companyAddress}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Company Website</label>
                    <input
                        type="text"
                        name="companyWebsite"
                        value={job.companyWebsite}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group full-width">
                    <label>Company Description / Details</label>
                    <textarea
                        name="companyDescription"
                        rows="4"
                        value={job.companyDescription}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="button-group" style={{ marginTop: "20px" }}>
                    <button type="submit" style={{ backgroundColor: "#4f46e5", color: "white", padding: "10px 20px", border: "none", borderRadius: "6px", cursor: "pointer", fontSize: "16px", fontWeight: "bold" }}>
                        Post Job
                    </button>
                    <button
                        type="button"
                        onClick={handleReset}
                        style={{ backgroundColor: "#64748b", color: "white", padding: "10px 20px", border: "none", borderRadius: "6px", cursor: "pointer", fontSize: "16px", fontWeight: "bold", marginLeft: "10px" }}
                    >
                        Reset
                    </button>
                </div>
            </form>
        </div>
    );
}
