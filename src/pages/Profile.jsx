import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../services/UserService";
import CertificationService from "../services/CertificationService";
import "../css/profile.css";

export default function Profile() {

    const navigate = useNavigate();

    const userId = sessionStorage.getItem("userId");

    const [isEditing, setIsEditing] = useState(false);

    const [user, setUser] = useState({
        fullName: "",
        email: "",
        password: "",
        phone: "",
        gender: "",
        dob: "",
        address: "",
        degree: "",
        college: "",
        cgpa: "",
        passedOutYear: "",
        experience: "",
        currentCompany: "",
        projects: "",
        certifications: "",
        linkedin: "",
        github: "",
        role: ""
    });

    const [certifications, setCertifications] = useState([]);
    const [certName, setCertName] = useState("");
    const [certFile, setCertFile] = useState(null);

    useEffect(() => {
        loadUser();
        loadCertifications();
    }, []);

    async function loadCertifications() {
        try {
            const response = await CertificationService.getCertificationsByUser(userId);
            setCertifications(response.data || []);
        } catch (error) {
            console.log("Failed to load certifications", error);
        }
    }

    async function loadUser() {

        try {

            const response = await UserService.getUser(userId);

            setUser(response.data);

        }
        catch (error) {

            console.log(error);

        }

    }

    function handleChange(event) {

        setUser({

            ...user,

            [event.target.name]: event.target.value

        });

    }

    async function handleSubmit(event) {

        event.preventDefault();

        try {

            await UserService.updateUser(userId, user);

            alert("Profile Updated Successfully");

            setIsEditing(false);

            loadUser();

        } catch (error) {

            console.log(error);

            alert("Update Failed");

        }

    }

    async function handleDeleteProfile() {

        if (!window.confirm("Are you sure you want to delete your profile account permanently? This action cannot be undone.")) {
            return;
        }

        try {
            await UserService.deleteUser(userId);
            alert("Your account has been deleted successfully.");
            sessionStorage.clear();
            navigate("/login");
        } catch (error) {
            console.log(error);
            alert("Failed to delete account");
        }

    }

    async function handleUploadCertification(e) {
        e.preventDefault();
        if (!certFile) {
            alert("Please choose a PDF file to upload");
            return;
        }
        const formData = new FormData();
        formData.append("certificationName", certName || "My Certificate");
        formData.append("userId", userId);
        formData.append("file", certFile);

        try {
            await CertificationService.uploadCertification(formData);
            alert("Certification Uploaded Successfully!");
            setCertName("");
            setCertFile(null);
            const fileInput = document.getElementById("certification-file-input");
            if (fileInput) fileInput.value = "";
            loadCertifications();
        } catch (error) {
            console.log(error);
            alert("Failed to upload certification");
        }
    }

    async function handleDeleteCertification(id) {
        if (!window.confirm("Are you sure you want to delete this certification?")) {
            return;
        }
        try {
            await CertificationService.deleteCertification(id);
            alert("Certification Deleted Successfully");
            loadCertifications();
        } catch (error) {
            console.log(error);
            alert("Failed to delete certification");
        }
    }

    return (

        <div
    className="profile-page"
    style={{
        maxWidth: "850px",
        margin: "0 auto",
        padding: "25px",
        background: "linear-gradient(135deg,#dbeafe,#c7d2fe,#ddd6fe)",
        borderRadius: "20px"
    }}
>

            <h2>My Profile</h2>

            {!isEditing ? (
                <div className="profile-view-card" style={{ backgroundColor: "white", padding: "30px", borderRadius: "12px", border: "1px solid #e2e8f0", boxShadow: "0 4px 6px rgba(0,0,0,0.05)" }}>
                    <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", borderBottom: "2px solid #4f46e5", paddingBottom: "10px", marginBottom: "20px" }}>
                        <h3 style={{ margin: 0, color: "#4f46e5" }}>Personal Information</h3>
                        <span className="status-badge" style={{ backgroundColor: "#e0e7ff", color: "#4338ca" }}>{user.role}</span>
                    </div>

                    <div style={{ display: "flex", flexDirection: "column", gap: "12px" }}>
                        <p style={{ margin: "5px 0" }}><strong>Name:</strong> {user.fullName || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Email:</strong> {user.email || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Phone Number:</strong> {user.phone || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Gender:</strong> {user.gender || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Date of Birth:</strong> {user.dob || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Address:</strong> {user.address || "N/A"}</p>

                        <h3 style={{ borderBottom: "2px solid #4f46e5", paddingBottom: "5px", marginTop: "20px", color: "#4f46e5" }}>Education & Qualifications</h3>
                        <p style={{ margin: "5px 0" }}><strong>Degree:</strong> {user.degree || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>College:</strong> {user.college || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>CGPA:</strong> {user.cgpa || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Passed Out Year:</strong> {user.passedOutYear || "N/A"}</p>

                        <h3 style={{ borderBottom: "2px solid #4f46e5", paddingBottom: "5px", marginTop: "20px", color: "#4f46e5" }}>Professional Experience & Skills</h3>
                        <p style={{ margin: "5px 0" }}><strong>Experience:</strong> {user.experience || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Current Company:</strong> {user.currentCompany || "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>Skills:</strong> {
                            user.skills && user.skills.length > 0 
                                ? user.skills.map(s => s.skillName).join(", ") 
                                : "N/A"
                        }</p>
                        <p style={{ margin: "5px 0" }}><strong>Uploaded Resumes:</strong> {
                            user.resumes && user.resumes.length > 0 
                                ? user.resumes.map(r => r.resumeName).join(", ") 
                                : "N/A"
                        }</p>

                        <h3 style={{ borderBottom: "2px solid #4f46e5", paddingBottom: "5px", marginTop: "20px", color: "#4f46e5" }}>Online Presence</h3>
                        <p style={{ margin: "5px 0" }}><strong>LinkedIn:</strong> {user.linkedin ? <a href={user.linkedin} target="_blank" rel="noreferrer">{user.linkedin}</a> : "N/A"}</p>
                        <p style={{ margin: "5px 0" }}><strong>GitHub:</strong> {user.github ? <a href={user.github} target="_blank" rel="noreferrer">{user.github}</a> : "N/A"}</p>

                        <h3 style={{ borderBottom: "2px solid #4f46e5", paddingBottom: "5px", marginTop: "20px", color: "#4f46e5" }}>Certifications (PDF)</h3>
                        {certifications.length === 0 ? (
                            <p style={{ margin: "5px 0", color: "#666" }}>No certifications uploaded yet.</p>
                        ) : (
                            <div style={{ display: "flex", flexDirection: "column", gap: "8px", marginTop: "10px" }}>
                                {certifications.map(c => (
                                    <div key={c.certificationId} style={{ display: "flex", justifyContent: "space-between", alignItems: "center", backgroundColor: "#f8fafc", padding: "8px 12px", borderRadius: "6px", border: "1px solid #e2e8f0" }}>
                                        <span style={{ fontWeight: "600", color: "#334155" }}>{c.certificationName}</span>
                                        <div style={{ display: "flex", gap: "8px" }}>
                                            <button 
                                                className="btn btn-primary btn-sm rounded-3 fw-bold px-3 py-1"
                                                onClick={() => window.open(`http://localhost:8080/api/certifications/download/${c.filePath.split(/[\\/]/).pop()}`, "_blank")}
                                                style={{ fontSize: "12px", padding: "4px 8px", backgroundColor: "#3b82f6", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}
                                            >
                                                View
                                            </button>
                                            <button 
                                                className="btn btn-danger btn-sm rounded-3 fw-bold px-3 py-1"
                                                onClick={() => handleDeleteCertification(c.certificationId)}
                                                style={{ fontSize: "12px", padding: "4px 8px", backgroundColor: "#ef4444", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}
                                            >
                                                Delete
                                            </button>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        )}

                        <div style={{ marginTop: "25px", padding: "20px", border: "1px dashed #cbd5e1", borderRadius: "10px", backgroundColor: "#f8fafc" }}>
                            <h4 style={{ margin: "0 0 15px 0", fontSize: "1.1rem", color: "#334155" }}>Upload New Certification</h4>
                            <form onSubmit={handleUploadCertification} style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
                                <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
                                    <label style={{ fontSize: "13px", fontWeight: "600", color: "#4b5563" }}>Certification Name</label>
                                    <input 
                                        type="text" 
                                        value={certName}
                                        onChange={(e) => setCertName(e.target.value)}
                                        placeholder="e.g. AWS Certified Solutions Architect"
                                        required
                                        style={{ padding: "8px 12px", borderRadius: "6px", border: "1px solid #d1d5db" }}
                                    />
                                </div>
                                <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
                                    <label style={{ fontSize: "13px", fontWeight: "600", color: "#4b5563" }}>Choose PDF Document</label>
                                    <input 
                                        id="certification-file-input"
                                        type="file" 
                                        accept=".pdf"
                                        onChange={(e) => setCertFile(e.target.files[0])}
                                        required
                                        style={{ padding: "6px", borderRadius: "6px", border: "1px solid #d1d5db", backgroundColor: "white" }}
                                    />
                                </div>
                                <button 
                                    type="submit" 
                                    style={{ marginTop: "10px", padding: "10px", backgroundColor: "#10b981", color: "white", border: "none", borderRadius: "6px", fontWeight: "bold", cursor: "pointer" }}
                                >
                                    Upload PDF Certificate
                                </button>
                            </form>
                        </div>
                    </div>

                    <div style={{ marginTop: "30px", display: "flex", gap: "10px" }}>
                        <button onClick={() => setIsEditing(true)} style={{ backgroundColor: "#4f46e5", color: "white" }}>
                            Edit Profile
                        </button>
                        <button onClick={handleDeleteProfile} style={{ backgroundColor: "#dc3545", color: "white" }}>
                            Delete Profile
                        </button>
                    </div>
                </div>
            ) : (
                <form onSubmit={handleSubmit} className="profile-form">

                    <div className="form-grid">

                        <div className="form-group">
                            <label>Full Name</label>
                            <input
                                type="text"
                                name="fullName"
                                value={user.fullName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label>Email</label>
                            <input
                                type="email"
                                name="email"
                                value={user.email}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label>Phone Number</label>
                            <input
                                type="text"
                                name="phone"
                                value={user.phone}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Gender</label>
                            <input
                                type="text"
                                name="gender"
                                value={user.gender}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Date of Birth</label>
                            <input
                                type="date"
                                name="dob"
                                value={user.dob}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Address</label>
                            <input
                                type="text"
                                name="address"
                                value={user.address}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Degree</label>
                            <input
                                type="text"
                                name="degree"
                                value={user.degree}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>College</label>
                            <input
                                type="text"
                                name="college"
                                value={user.college}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>CGPA</label>
                            <input
                                type="number"
                                step="0.01"
                                name="cgpa"
                                value={user.cgpa}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Passed Out Year</label>
                            <input
                                type="number"
                                name="passedOutYear"
                                value={user.passedOutYear}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Experience</label>
                            <input
                                type="text"
                                name="experience"
                                value={user.experience}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Current Company</label>
                            <input
                                type="text"
                                name="currentCompany"
                                value={user.currentCompany}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Projects</label>
                            <input
                                type="text"
                                name="projects"
                                value={user.projects}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Certifications</label>
                            <input
                                type="text"
                                name="certifications"
                                value={user.certifications}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>LinkedIn</label>
                            <input
                                type="text"
                                name="linkedin"
                                value={user.linkedin}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>GitHub</label>
                            <input
                                type="text"
                                name="github"
                                value={user.github}
                                onChange={handleChange}
                            />
                        </div>

                    </div>

                    <div style={{ marginTop: "20px", display: "flex", gap: "10px" }}>
                        <button
                            type="submit"
                            className="save-btn"
                            style={{ backgroundColor: "#4f46e5", color: "white" }}>
                            Update Profile
                        </button>
                        <button
                            type="button"
                            onClick={() => setIsEditing(false)}
                            style={{ backgroundColor: "#6c757d", color: "white" }}>
                            Cancel
                        </button>
                    </div>

                </form>
            )}

        </div>

    );

}
