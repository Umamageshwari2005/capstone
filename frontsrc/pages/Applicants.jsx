import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import ApplicationService from "../services/ApplicationService";
import NotificationService from "../services/NotificationService";
import api from "../api/axiosConfig";

import "../css/applicants.css";

export default function Applicants() {

    const { jobId } = useParams();

    const employerId = sessionStorage.getItem("userId");

    const [applications, setApplications] = useState([]);

    const [searchId, setSearchId] = useState("");

    const [loading, setLoading] = useState(true);

    const [selectedApplicant, setSelectedApplicant] = useState(null);

    const [applicantCertifications, setApplicantCertifications] = useState([]);

    useEffect(() => {
        if (selectedApplicant) {
            loadApplicantCertifications(selectedApplicant.userId);
        } else {
            setApplicantCertifications([]);
        }
    }, [selectedApplicant]);

    async function loadApplicantCertifications(uid) {
        try {
            const response = await api.get(`/certifications/user/${uid}`);
            setApplicantCertifications(response.data || []);
        } catch (err) {
            console.log("Failed to load certifications", err);
        }
    }

    useEffect(() => {

        loadApplicants();

    }, [jobId, searchId]);

    async function loadApplicants() {

        try {

            let response;
            if (searchId.trim() && !isNaN(searchId.trim())) {
                response = await ApplicationService.searchApplicants(employerId, parseInt(searchId.trim()));
            } else {
                response = await ApplicationService.getApplicantsByEmployer(employerId);
            }

            if (jobId) {
                setApplications(response.data.filter(app => app.jobId === parseInt(jobId)));
            } else {
                setApplications(response.data);
            }

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    }

    function viewResume(applicationId) {

        const app = applications.find(a => a.applicationId === applicationId);
        if (app && app.resumes && app.resumes.length > 0) {
            const resumeUrl = `http://localhost:8080${app.resumes[0].resumeUrl}`;
            window.open(resumeUrl, "_blank");
        } else {
            alert("No resume uploaded by this applicant.");
        }

    }

    const getTimeline = (status) => {
        const milestones = [];
        milestones.push({ label: "Applied", cleared: true });

        if (status === "Applied") return milestones;

        if (status.includes("Rejected")) {
            if (status.includes("Round 1")) {
                milestones.push({ label: "Interview Round 1 Scheduled", cleared: true });
                milestones.push({ label: "Round 1 Rejected", cleared: false, rejected: true });
            } else if (status.includes("Round 2")) {
                milestones.push({ label: "Interview Round 1 Scheduled", cleared: true });
                milestones.push({ label: "Round 1 Cleared", cleared: true });
                milestones.push({ label: "Interview Round 2 Scheduled", cleared: true });
                milestones.push({ label: "Round 2 Rejected", cleared: false, rejected: true });
            } else if (status.includes("Round 3")) {
                milestones.push({ label: "Interview Round 1 Scheduled", cleared: true });
                milestones.push({ label: "Round 1 Cleared", cleared: true });
                milestones.push({ label: "Interview Round 2 Scheduled", cleared: true });
                milestones.push({ label: "Round 2 Cleared", cleared: true });
                milestones.push({ label: "Interview Round 3 Scheduled", cleared: true });
                milestones.push({ label: "Round 3 Rejected", cleared: false, rejected: true });
            } else if (status.includes("HR")) {
                milestones.push({ label: "Interview Round 1 Scheduled", cleared: true });
                milestones.push({ label: "Round 1 Cleared", cleared: true });
                milestones.push({ label: "Interview Round 2 Scheduled", cleared: true });
                milestones.push({ label: "Round 2 Cleared", cleared: true });
                milestones.push({ label: "HR Interview Scheduled", cleared: true });
                milestones.push({ label: "HR Round Rejected", cleared: false, rejected: true });
            }
            return milestones;
        }

        if (status === "Rejected") {
            milestones.push({ label: "Rejected", cleared: false, rejected: true });
            return milestones;
        }

        if (status.includes("Round 1") || status.includes("Round 2") || status.includes("Round 3") || status.includes("HR") || status === "Selected") {
            milestones.push({ label: "Interview Round 1 Scheduled", cleared: true });
        }
        if (status.includes("Round 1 Cleared") || status.includes("Round 2") || status.includes("Round 3") || status.includes("HR") || status === "Selected") {
            milestones.push({ label: "Round 1 Cleared", cleared: true });
        }
        if (status.includes("Round 2") || status.includes("Round 3") || status.includes("HR") || status === "Selected") {
            milestones.push({ label: "Interview Round 2 Scheduled", cleared: true });
        }
        if (status.includes("Round 2 Cleared") || status.includes("Round 3") || status.includes("HR") || status === "Selected") {
            milestones.push({ label: "Round 2 Cleared", cleared: true });
        }
        if (status.includes("Round 3")) {
            milestones.push({ label: "Interview Round 3 Scheduled", cleared: true });
            if (status.includes("Cleared")) {
                milestones.push({ label: "Round 3 Cleared", cleared: true });
            }
        }
        if (status.includes("HR") || status === "Selected") {
            milestones.push({ label: "HR Interview Scheduled", cleared: true });
        }
        if (status.includes("HR Round Cleared") || status === "Selected") {
            milestones.push({ label: "HR Round Cleared", cleared: true });
        }
        if (status === "Selected") {
            milestones.push({ label: "Selected", cleared: true });
        }

        return milestones;
    };

    async function updateApplicationStatus(applicationId, nextStatus, notificationMsg) {
        try {
            const app = applications.find(a => a.applicationId === applicationId);
            if (!app) return;

            await ApplicationService.updateApplicationStatus(applicationId, {
                status: nextStatus,
                userId: app.userId,
                jobId: app.jobId
            });

            try {
                await NotificationService.addNotification({
                    userId: app.userId,
                    message: `[Company: ${app.companyName} | Job ID: ${app.jobId}] ${notificationMsg}`,
                    type: "Interview Update",
                    read: false
                });
            } catch (notifyErr) {
                console.log("Failed to send notification", notifyErr);
            }

            alert(`Status updated to "${nextStatus}" successfully`);
            loadApplicants();
        } catch (error) {
            console.log(error);
            alert("Failed to Update Status");
        }
    }

    function scheduleRound(app, roundNum) {
        const details = prompt(`Enter Interview Round ${roundNum} details (Date, Time, Link/Venue):`, "Date: [Tomorrow], Time: [11:00 AM], Meet Link: [meet.google.com/xyz]");
        if (details === null) return;
        const status = `Interview Round ${roundNum} Scheduled`;
        const msg = `Interview Round ${roundNum} Scheduled: ${details}`;
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function passRound(app, roundNum) {
        const status = `Round ${roundNum} Cleared`;
        const msg = `Congratulations! You cleared Interview Round ${roundNum}.`;
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function rejectRound(app, roundNum) {
        if (!window.confirm(`Reject this candidate in Round ${roundNum}?`)) return;
        const status = `Round ${roundNum} Rejected`;
        const msg = `We regret to inform you that you were not selected after Interview Round ${roundNum}.`;
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function scheduleHRRound(app) {
        const details = prompt("Enter HR Interview details (Date, Time, Link/Venue):", "Date: [Friday], Time: [3:00 PM], Meet Link: [meet.google.com/abc]");
        if (details === null) return;
        const status = "HR Interview Scheduled";
        const msg = `HR Interview Scheduled: ${details}`;
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function passHRRound(app) {
        const status = "HR Round Cleared";
        const msg = "Congratulations! You cleared the HR Interview round.";
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function rejectHRRound(app) {
        if (!window.confirm("Reject this candidate in HR Round?")) return;
        const status = "HR Round Rejected";
        const msg = "We regret to inform you that you were not selected after the HR Interview.";
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function markSelected(app) {
        if (!window.confirm("Mark candidate as Selected (Final)?")) return;
        const status = "Selected";
        const msg = "Congratulations! You have been selected for this position. Offer letter details will follow soon.";
        updateApplicationStatus(app.applicationId, status, msg);
    }

    function rejectFinal(app) {
        if (!window.confirm("Mark candidate as Rejected (Final)?")) return;
        const status = "Rejected";
        const msg = "We regret to inform you that you were not selected for this position.";
        updateApplicationStatus(app.applicationId, status, msg);
    }

    const renderActionPanel = (application) => {
        const status = application.status;

        if (status === "Applied") {
            return (
                <button
                    onClick={() => scheduleRound(application, 1)}
                    style={{ backgroundColor: "#4f46e5", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                >
                    Schedule Round 1
                </button>
            );
        }

        if (status === "Interview Round 1 Scheduled") {
            return (
                <div style={{ display: "flex", gap: "6px" }}>
                    <button
                        onClick={() => passRound(application, 1)}
                        style={{ backgroundColor: "#10b981", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Clear Round 1
                    </button>
                    <button
                        onClick={() => rejectRound(application, 1)}
                        style={{ backgroundColor: "#ef4444", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Reject
                    </button>
                </div>
            );
        }

        if (status === "Round 1 Cleared") {
            return (
                <button
                    onClick={() => scheduleRound(application, 2)}
                    style={{ backgroundColor: "#4f46e5", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                >
                    Schedule Round 2
                </button>
            );
        }

        if (status === "Interview Round 2 Scheduled") {
            return (
                <div style={{ display: "flex", gap: "6px" }}>
                    <button
                        onClick={() => passRound(application, 2)}
                        style={{ backgroundColor: "#10b981", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Clear Round 2
                    </button>
                    <button
                        onClick={() => rejectRound(application, 2)}
                        style={{ backgroundColor: "#ef4444", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Reject
                    </button>
                </div>
            );
        }

        if (status === "Round 2 Cleared") {
            return (
                <div style={{ display: "flex", gap: "6px" }}>
                    <button
                        onClick={() => scheduleRound(application, 3)}
                        style={{ backgroundColor: "#4f46e5", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Schedule Round 3
                    </button>
                    <button
                        onClick={() => scheduleHRRound(application)}
                        style={{ backgroundColor: "#eab308", color: "black", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Schedule HR Round
                    </button>
                </div>
            );
        }

        if (status === "Interview Round 3 Scheduled") {
            return (
                <div style={{ display: "flex", gap: "6px" }}>
                    <button
                        onClick={() => passRound(application, 3)}
                        style={{ backgroundColor: "#10b981", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Clear Round 3
                    </button>
                    <button
                        onClick={() => rejectRound(application, 3)}
                        style={{ backgroundColor: "#ef4444", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Reject
                    </button>
                </div>
            );
        }

        if (status === "Round 3 Cleared") {
            return (
                <button
                    onClick={() => scheduleHRRound(application)}
                    style={{ backgroundColor: "#eab308", color: "black", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                >
                    Schedule HR Round
                </button>
            );
        }

        if (status === "HR Interview Scheduled") {
            return (
                <div style={{ display: "flex", gap: "6px" }}>
                    <button
                        onClick={() => passHRRound(application)}
                        style={{ backgroundColor: "#10b981", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Clear HR Round
                    </button>
                    <button
                        onClick={() => rejectHRRound(application)}
                        style={{ backgroundColor: "#ef4444", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Reject
                    </button>
                </div>
            );
        }

        if (status === "HR Round Cleared") {
            return (
                <div style={{ display: "flex", gap: "6px" }}>
                    <button
                        onClick={() => markSelected(application)}
                        style={{ backgroundColor: "#10b981", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Select Candidate
                    </button>
                    <button
                        onClick={() => rejectFinal(application)}
                        style={{ backgroundColor: "#ef4444", color: "white", padding: "6px 12px", borderRadius: "4px", border: "none", cursor: "pointer", fontSize: "0.85rem", fontWeight: "600" }}
                    >
                        Reject
                    </button>
                </div>
            );
        }

        return <span style={{ color: "#64748b", fontWeight: "bold" }}>Finished</span>;
    };

    if (loading) {

        return <h2>Loading Applicants...</h2>;

    }

    const filteredApplicants = applications;

    return (

        <div
    className="applicants-page"
    style={{
        background: "linear-gradient(135deg,#dbeafe,#c7d2fe,#ddd6fe)",
        minHeight: "100vh",
        padding: "30px",
        borderRadius: "20px"
    }}
>

            <h1
    style={{
        background: "linear-gradient(135deg,#60A5FA,#3B82F6)",
        color: "white",
        padding: "18px",
        borderRadius: "15px",
        textAlign: "center",
        marginBottom: "25px"
    }}
>
                Job Applicants {jobId ? `for Job #${jobId}` : ""}
            </h1>

            <div style={{ marginBottom: "20px", display: "flex", gap: "10px", alignItems: "center" }}>
                <label style={{ fontWeight: "bold", fontSize: "1.05rem", color: "#334155" }}>Search by Applicant ID:</label>
                <input
                    type="text"
                    placeholder="Enter Applicant ID..."
                    value={searchId}
                    onChange={(e) => setSearchId(e.target.value)}
                    style={{ padding: "10px 14px", borderRadius: "8px", border: "1px solid #cbd5e1", minWidth: "220px", outline: "none", fontSize: "0.95rem" }}
                />
            </div>

            {

                filteredApplicants.length === 0 ?

                    <div className="empty-box">

                        No Applicants Found

                    </div>

                    :

                    <table className="applicants-table">

                        <thead>

                            <tr>

                                <th>Application ID</th>

                                <th>Applicant ID</th>

                                <th>Applicant Name</th>

                                <th>Job ID</th>

                                <th>Job Title</th>

                                <th>Company Name</th>

                                <th>Applied Date</th>

                                <th>Current Status</th>

                                <th>Documents</th>

                                <th>Action Panel</th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                filteredApplicants.map(application => (

                                    <tr key={application.applicationId}>

                                        <td>

                                            {application.applicationId}

                                        </td>

                                        <td>

                                            {application.userId}

                                        </td>

                                        <td>

                                            {application.userName || application.fullName}

                                        </td>

                                        <td>

                                            {application.jobId}

                                        </td>

                                        <td>

                                            {application.jobTitle}

                                        </td>

                                        <td>

                                            {application.companyName}

                                        </td>

                                        <td>

                                            {application.appliedDate || "N/A"}

                                        </td>

                                        <td>

                                             <div style={{ display: "flex", flexDirection: "column", gap: "2px", fontSize: "0.8rem", textAlign: "left", backgroundColor: "#f8fafc", padding: "8px", borderRadius: "6px", border: "1px solid #e2e8f0" }}>
                                                 {getTimeline(application.status).map((m, idx) => (
                                                     <div key={idx} style={{ display: "flex", alignItems: "center", gap: "5px", color: "black", fontWeight: "500" }}>
                                                         <span>{m.label}</span>
                                                         <span>{m.rejected ? "❌" : "✅"}</span>
                                                     </div>
                                                 ))}
                                             </div>

                                        </td>

                                        <td>
                                            <div style={{ display: "flex", flexDirection: "column", gap: "5px" }}>
                                                <button

                                                    className="resume-btn"

                                                    onClick={() => viewResume(application.applicationId)}

                                                    style={{ padding: "4px 8px", fontSize: "0.85rem" }}

                                                >

                                                    Resume

                                                </button>

                                                <button

                                                    className="profile-btn"

                                                    onClick={() => setSelectedApplicant(application)}

                                                    style={{ padding: "4px 8px", fontSize: "0.85rem" }}

                                                >

                                                    Profile

                                                </button>
                                            </div>

                                        </td>

                                        <td>
                                            {renderActionPanel(application)}
                                        </td>

                                    </tr>

                                ))

                            }

                        </tbody>

                    </table>

            }

            {selectedApplicant && (
                <div className="profile-modal-overlay" onClick={() => setSelectedApplicant(null)}>
                    <div className="profile-modal-content" onClick={(e) => e.stopPropagation()}>
                        <div className="modal-header">
                            <h2>Applicant Profile Details</h2>
                            <button className="close-modal" onClick={() => setSelectedApplicant(null)}>×</button>
                        </div>
                        <div className="modal-body">
                            <section className="profile-section">
                                <h3>Personal Details</h3>
                                <p><strong>Job ID:</strong> {selectedApplicant.jobId}</p>
                                <p><strong>Company ID:</strong> {selectedApplicant.companyId || "N/A"}</p>
                                <p><strong>Full Name:</strong> {selectedApplicant.fullName}</p>
                                <p><strong>Email:</strong> {selectedApplicant.email}</p>
                                <p><strong>Phone:</strong> {selectedApplicant.phone || "N/A"}</p>
                                <p><strong>Gender:</strong> {selectedApplicant.gender || "N/A"}</p>
                                <p><strong>DOB:</strong> {selectedApplicant.dob || "N/A"}</p>
                                <p><strong>Address:</strong> {selectedApplicant.address || "N/A"}</p>
                            </section>

                            <section className="profile-section">
                                <h3>Educational Qualification</h3>
                                <p><strong>Degree:</strong> {selectedApplicant.degree || "N/A"}</p>
                                <p><strong>College/University:</strong> {selectedApplicant.college || "N/A"}</p>
                                <p><strong>CGPA:</strong> {selectedApplicant.cgpa || "N/A"}</p>
                                <p><strong>Passed Out Year:</strong> {selectedApplicant.passedOutYear || "N/A"}</p>
                            </section>

                            <section className="profile-section">
                                <h3>Experience & Skills</h3>
                                <p><strong>Experience:</strong> {selectedApplicant.experience || "N/A"}</p>
                                <p><strong>Current Company:</strong> {selectedApplicant.currentCompany || "N/A"}</p>
                                <p><strong>Skills:</strong> {
                                    selectedApplicant.skills && selectedApplicant.skills.length > 0
                                        ? selectedApplicant.skills.map(s => s.skillName).join(", ")
                                        : "N/A"
                                }</p>
                            </section>

                            <section className="profile-section">
                                <h3>Resumes & Attachments</h3>
                                <p><strong>Uploaded Resumes:</strong> {
                                    selectedApplicant.resumes && selectedApplicant.resumes.length > 0
                                        ? selectedApplicant.resumes.map(r => (
                                            <button 
                                                key={r.resumeId}
                                                className="resume-btn"
                                                onClick={() => window.open(`http://localhost:8080${r.resumeUrl}`, "_blank")}
                                                style={{ marginLeft: "5px", padding: "4px 8px", fontSize: "12px" }}
                                            >
                                                Download {r.resumeName}
                                            </button>
                                        ))
                                        : "No Resume Uploaded"
                                }</p>
                            </section>

                            <section className="profile-section">
                                <h3>Additional Info</h3>
                                <p><strong>Projects:</strong> {selectedApplicant.projects || "N/A"}</p>
                                <div style={{ marginTop: "15px", paddingTop: "15px", borderTop: "1px dashed #ddd" }}>
                                    <strong>Uploaded Certifications (PDF):</strong>
                                    {applicantCertifications.length === 0 ? (
                                        <p style={{ margin: "5px 0 0 0", color: "#666" }}>No PDF Certifications Uploaded</p>
                                    ) : (
                                        <div style={{ display: "flex", flexDirection: "column", gap: "8px", marginTop: "10px" }}>
                                            {applicantCertifications.map(c => (
                                                <div key={c.certificationId} style={{ display: "flex", justifyContent: "space-between", alignItems: "center", backgroundColor: "#f8fafc", padding: "8px 12px", borderRadius: "6px", border: "1px solid #e2e8f0" }}>
                                                    <span style={{ fontWeight: "600", color: "#334155" }}>{c.certificationName}</span>
                                                    <button 
                                                        className="resume-btn"
                                                        onClick={() => window.open(`http://localhost:8080/api/certifications/download/${c.filePath.split(/[\\/]/).pop()}`, "_blank")}
                                                        style={{ fontSize: "12px", padding: "4px 8px", cursor: "pointer" }}
                                                    >
                                                        View PDF
                                                    </button>
                                                </div>
                                            ))}
                                        </div>
                                    )}
                                </div>
                                <p>
                                    <strong>LinkedIn:</strong>{" "}
                                    {selectedApplicant.linkedin ? (
                                        <a href={selectedApplicant.linkedin} target="_blank" rel="noreferrer">{selectedApplicant.linkedin}</a>
                                    ) : "N/A"}
                                </p>
                                <p>
                                    <strong>GitHub:</strong>{" "}
                                    {selectedApplicant.github ? (
                                        <a href={selectedApplicant.github} target="_blank" rel="noreferrer">{selectedApplicant.github}</a>
                                    ) : "N/A"}
                                </p>
                            </section>
                        </div>
                    </div>
                </div>
            )}

        </div>

    );

}
