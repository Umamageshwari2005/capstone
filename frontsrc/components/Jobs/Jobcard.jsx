import { useState } from "react";
import "../../css/jobs.css";

export default function JobCard({
    job,
    isApplied,
    onApply
}) {
    const [showDetails, setShowDetails] = useState(false);

    return (
        <div className="job-card">
            <div className="job-header">
                <div>
                    <h2>{job.jobTitle}</h2>
                    <h4>{job.companyName}</h4>
                </div>
                <span className="job-type">
                    {job.jobType}
                </span>
            </div>

            <div className="job-info">
                <span>📍 {job.location}</span>
                <span>💼 {job.experience}</span>
                <span>💰 ₹ {job.salary} LPA</span>
            </div>

            <p className="job-description">
                {job.description}
            </p>

            <div className="skills">
                <strong>Required Skills:</strong> {job.requiredSkills}
            </div>

            {showDetails && (
                <div className="company-details-block" style={{ marginTop: "15px", paddingTop: "15px", borderTop: "1px dashed #ddd" }}>
                    <h5 style={{ margin: "0 0 10px 0", color: "#10b981" }}>Company Profile & Requirements</h5>
                    <p style={{ margin: "3px 0" }}><strong>Email:</strong> {job.companyEmail || "N/A"}</p>
                    <p style={{ margin: "3px 0" }}><strong>Phone:</strong> {job.companyPhone || "N/A"}</p>
                    <p style={{ margin: "3px 0" }}><strong>Address:</strong> {job.companyAddress || "N/A"}</p>
                    <p style={{ margin: "3px 0" }}><strong>Website:</strong> {job.companyWebsite ? <a href={job.companyWebsite.startsWith("http") ? job.companyWebsite : `http://${job.companyWebsite}`} target="_blank" rel="noreferrer">{job.companyWebsite}</a> : "N/A"}</p>
                    <p style={{ margin: "10px 0 0 0" }}><strong>About Company:</strong></p>
                    <p style={{ margin: "5px 0 0 0", fontStyle: "italic", color: "#666" }}>{job.companyDescription || "No description provided."}</p>
                    
                    <p style={{ margin: "10px 0 0 0" }}><strong>Company Job Requirement Details:</strong></p>
                    <p style={{ margin: "5px 0 0 0", color: "#555" }}>{job.description}</p>
                </div>
            )}

            <div className="job-footer" style={{ marginTop: "15px", display: "flex", gap: "10px", alignItems: "center" }}>
                <button className="details-btn" onClick={() => setShowDetails(!showDetails)}>
                    {showDetails ? "Hide Details" : "View Details"}
                </button>

                <div style={{ flex: 1, display: "flex", justifyContent: "flex-end" }}>
                    {isApplied ? (
                        <span style={{ color: "#10b981", fontWeight: "bold", fontSize: "16px" }}>
                            This company has been applied
                        </span>
                    ) : (
                        <button
                            className="apply-btn"
                            onClick={() => onApply(job.jobId)}
                            style={{ padding: "10px 20px", cursor: "pointer", borderRadius: "8px", border: "none", backgroundColor: "#2563eb", color: "white", fontWeight: "bold" }}
                        >
                            Apply Now
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
}
