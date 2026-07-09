import { useEffect, useState } from "react";
import ResumeService from "../services/ResumeService";
import "../css/resume.css";

export default function Resume() {
    const userId = sessionStorage.getItem("userId");

    const [resumeName, setResumeName] = useState("");
    const [file, setFile] = useState(null);
    const [resumes, setResumes] = useState([]);
    
    const [isUpdating, setIsUpdating] = useState(false);
    const [editName, setEditName] = useState("");
    const [editFile, setEditFile] = useState(null);
    
    const [searchId, setSearchId] = useState("");
    const [searchedResume, setSearchedResume] = useState(null);
    const [searchPerformed, setSearchPerformed] = useState(false);

    useEffect(() => {
        loadResumes();
    }, []);

    async function loadResumes() {
        try {
            const response = await ResumeService.getResumeByUser(userId);
            setResumes(response.data || []);
        } catch (error) {
            console.log(error);
        }
    }

    async function uploadResume(e) {
        e.preventDefault();
        if (file == null) {
            alert("Please Choose a PDF Resume");
            return;
        }

        const formData = new FormData();
        formData.append("resumeName", resumeName || "My Resume");
        formData.append("userId", userId);
        formData.append("file", file);

        try {
            await ResumeService.uploadResume(formData);
            alert("Resume Uploaded Successfully");
            setResumeName("");
            setFile(null);
            loadResumes();
        } catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Resume upload failed.";
            alert("Upload Failed: " + msg);
        }
    }

    async function deleteResume(id) {
        if (!window.confirm("Are you sure you want to delete your resume?")) {
            return;
        }
        try {
            await ResumeService.deleteResume(id);
            alert("Resume Deleted Successfully");
            setIsUpdating(false);
            loadResumes();
        } catch (error) {
            console.log(error);
            alert("Failed to delete resume");
        }
    }

    function viewResume(res) {
        if (res.resumeUrl) {
            window.open(`http://localhost:8080${res.resumeUrl}`, "_blank");
        } else {
            window.open(`http://localhost:8080/api/resumes/download/${res.resumeId}`, "_blank");
        }
    }

    function startEdit(res) {
        setIsUpdating(true);
        setEditName(res.resumeName);
        setEditFile(null);
    }

    async function saveUpdate(id) {
        const formData = new FormData();
        formData.append("resumeName", editName || "Updated Resume");
        if (editFile) {
            formData.append("file", editFile);
        }

        try {
            await ResumeService.updateResume(id, formData);
            alert("Resume Updated Successfully");
            setIsUpdating(false);
            loadResumes();
        } catch (error) {
            console.log(error);
            const msg = error.response?.data?.message || error.message || "Failed to update resume.";
            alert("Failed to update resume: " + msg);
        }
    }

    async function handleSearch(e) {
        e.preventDefault();
        if (!searchId.trim()) {
            setSearchedResume(null);
            setSearchPerformed(false);
            return;
        }
        try {
            const response = await ResumeService.getResumeById(parseInt(searchId));
            setSearchedResume(response.data);
            setSearchPerformed(true);
        } catch (error) {
            console.log(error);
            setSearchedResume(null);
            setSearchPerformed(true);
        }
    }

    const currentResume = resumes.length > 0 ? resumes[0] : null;

    return (
        <div
    className="container py-5"
    style={{
        maxWidth: "850px",
        background: "linear-gradient(135deg,#e0f2fe,#ede9fe,#dcfce7)",
        borderRadius: "25px",
        padding: "30px",
        boxShadow: "0 15px 35px rgba(0,0,0,0.12)"
    }}
>
            <div
    className="card shadow border-0 rounded-4 p-4 mb-4"
    style={{
        background: "linear-gradient(135deg,#f8fbff,#eef6ff)",
        border: "3px solid #1357a5"
    }}
>
                <h2 className="fs-3 fw-bold mb-4 text-center" style={{ color: "#3c32fb" }}>Resume Management</h2>

                {/* 1. If no resume exists, show Upload Form */}
                {!currentResume ? (
                    <div className="mb-4">
                        <div className="alert alert-info rounded-3 text-center mb-4">
                            You have not uploaded any resume yet. Upload your PDF resume below.
                        </div>
                        <form onSubmit={uploadResume} className="row g-3">
                            <div className="col-12">
                                <label className="form-label fw-bold">Resume Name</label>
                                <input
                                    type="text"
                                    className="form-control rounded-3"
                                    value={resumeName}
                                    onChange={(e) => setResumeName(e.target.value)}
                                    placeholder="e.g. Java Developer Resume"
                                    required
                                />
                            </div>
                            <div className="col-12">
                                <label className="form-label fw-bold">Choose PDF Document</label>
                                <input
                                    type="file"
                                    className="form-control rounded-3"
                                    accept=".pdf"
                                    onChange={(e) => setFile(e.target.files[0])}
                                    required
                                />
                            </div>
                            <div className="col-12 text-center mt-4">
                                <button className="btn btn-primary px-5 py-2.5 rounded-3 fw-bold" style={{ backgroundColor: "#4f46e5", border: "none" }}>
                                    Upload Resume
                                </button>
                            </div>
                        </form>
                    </div>
                ) : (
                    /* 2. If resume exists, show active resume card and edit fields */
                    <div className="mb-4">
                        <div className="alert alert-success rounded-3 text-center mb-4">
                            Your active resume is uploaded and ready. Only one resume can be active at a time.
                        </div>

                        {!isUpdating ? (
                            <div className="card border-light-subtle rounded-4 p-4 shadow-sm bg-light-subtle">
                                <div className="d-flex justify-content-between align-items-center mb-3">
                                    <h4 className="fw-bold mb-0 text-primary">{currentResume.resumeName}</h4>
                                    <span className="badge text-bg-success rounded-pill px-3 py-1.5 fs-12">Active Resume</span>
                                </div>
                                <p className="text-secondary mb-4 fs-14"><strong>Resume ID:</strong> {currentResume.resumeId}</p>

                                <div className="d-flex flex-wrap gap-2 justify-content-center">
                                    <button onClick={() => viewResume(currentResume)} className="btn btn-primary px-4 py-2 rounded-3 fw-bold">
                                        View Resume
                                    </button>
                                    <button onClick={() => startEdit(currentResume)} className="btn btn-warning px-4 py-2 rounded-3 fw-bold text-dark">
                                        Update Details / File
                                    </button>
                                    <button onClick={() => deleteResume(currentResume.resumeId)} className="btn btn-danger px-4 py-2 rounded-3 fw-bold">
                                        Delete Resume
                                    </button>
                                </div>
                            </div>
                        ) : (
                            /* Editing Block */
                            <div className="card border-warning rounded-4 p-4 shadow-sm bg-light-subtle">
                                <h4 className="fw-bold text-warning mb-3">Update Active Resume</h4>
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Resume Name</label>
                                    <input
                                        type="text"
                                        className="form-control rounded-3"
                                        value={editName}
                                        onChange={(e) => setEditName(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Replace PDF Document (Optional)</label>
                                    <input
                                        type="file"
                                        className="form-control rounded-3"
                                        accept=".pdf"
                                        onChange={(e) => setEditFile(e.target.files[0])}
                                    />
                                </div>
                                <div className="d-flex gap-2">
                                    <button onClick={() => saveUpdate(currentResume.resumeId)} className="btn btn-success px-4 py-2 rounded-3 fw-bold">
                                        Save Changes
                                    </button>
                                    <button onClick={() => setIsUpdating(false)} className="btn btn-secondary px-4 py-2 rounded-3 fw-bold">
                                        Cancel
                                    </button>
                                </div>
                            </div>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
}
