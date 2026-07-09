import { useEffect, useState } from "react";
import ApplicationService from "../services/ApplicationService";
import NotificationService from "../services/NotificationService";

export default function SendNotification() {
    const employerId = sessionStorage.getItem("userId");
    const [applicationId, setApplicationId] = useState("");
    const [loading, setLoading] = useState(false);
    const [applicant, setApplicant] = useState(null);
    const [message, setMessage] = useState("");
    const [sentNotifications, setSentNotifications] = useState([]);

    useEffect(() => {
        loadSentNotifications();
    }, []);

    async function loadSentNotifications() {
        try {
            const response = await NotificationService.getNotificationsByUser(employerId);
            setSentNotifications(response.data || []);
        } catch (error) {
            console.log("Failed to load sent notifications history", error);
        }
    }

    async function handleSearch(e) {
        e.preventDefault();
        if (!applicationId.trim()) {
            alert("Please enter a valid Application ID");
            return;
        }

        setLoading(true);
        setApplicant(null);

        try {
            const response = await ApplicationService.getApplicantDetails(applicationId);
            setApplicant(response.data);
        } catch (error) {
            console.log(error);
            alert("Application ID not found or error loading details.");
        } finally {
            setLoading(false);
        }
    }

    async function handleSend(e) {
        e.preventDefault();
        if (!message.trim()) {
            alert("Please enter a message");
            return;
        }

        try {
            const payload = {
                userId: applicant.userId,
                message: `[Company: ${applicant.companyName} | Job ID: ${applicant.jobId}] ${message}`,
                type: "Application Update",
                read: false
            };
            await NotificationService.addNotification(payload);

            const payloadEmployer = {
                userId: parseInt(employerId),
                message: `[Sent to: ${applicant.fullName} | Application ID: ${applicationId}] ${message}`,
                type: "Sent Notification",
                read: false
            };
            await NotificationService.addNotification(payloadEmployer);

            alert("Notification sent successfully!");
            setMessage("");
            setApplicant(null);
            setApplicationId("");
            loadSentNotifications();
        } catch (error) {
            console.log(error);
            alert("Failed to send notification");
        }
    }

    const sentList = sentNotifications.map(n => {
        const regex = /^\[Sent to:\s*(.*?)\s*\|\s*Application ID:\s*(.*?)\]\s*(.*)$/;
        const match = n.message.match(regex);
        if (match) {
            return {
                id: n.notificationId,
                applicantName: match[1],
                applicationId: match[2],
                msg: match[3]
            };
        }
        return {
            id: n.notificationId,
            applicantName: "N/A",
            applicationId: "N/A",
            msg: n.message
        };
    });

    return (
        <div
    style={{
        maxWidth: "900px",
        margin: "20px auto",
        padding: "30px",
        fontFamily: "sans-serif",
        background: "linear-gradient(135deg,#DBEAFE,#C7D2FE,#DDD6FE)",
        borderRadius: "20px",
        boxShadow: "0 10px 30px rgba(0,0,0,.12)"
    }}
>
            <h2 style={{ borderBottom: "2px solid #4f46e5", paddingBottom: "10px", color: "#4f46e5" }}>Send Notification to Applicant</h2>
            
            <form onSubmit={handleSearch} style={{ display: "flex", gap: "10px", marginBottom: "30px", marginTop: "20px" }}>
                <input
                    type="number"
                    placeholder="Enter Application ID"
                    value={applicationId}
                    onChange={(e) => setApplicationId(e.target.value)}
                    style={{ flex: 1, padding: "10px", borderRadius: "6px", border: "1px solid #ccc", fontSize: "16px" }}
                    required
                />
                <button 
                    type="submit" 
                    style={{ padding: "10px 20px", backgroundColor: "#4f46e5", color: "white", border: "none", borderRadius: "6px", cursor: "pointer", fontSize: "16px" }}
                >
                    {loading ? "Searching..." : "Search Applicant"}
                </button>
            </form>

            {applicant && (
                <div style={{ backgroundColor: "#f8fafc", border: "1px solid #e2e8f0", borderRadius: "10px", padding: "20px" }}>
                    <h3 style={{ color: "#334155", borderBottom: "1px solid #cbd5e1", paddingBottom: "8px", marginTop: 0 }}>Applicant Information</h3>
                    
                    <div style={{ display: "flex", flexDirection: "column", gap: "10px", margin: "15px 0" }}>
                        <p style={{ margin: "4px 0" }}><strong>Full Name:</strong> {applicant.fullName}</p>
                        <p style={{ margin: "4px 0" }}><strong>Email:</strong> {applicant.email}</p>
                        <p style={{ margin: "4px 0" }}><strong>Phone:</strong> {applicant.phone || "N/A"}</p>
                        <p style={{ margin: "4px 0" }}><strong>Degree:</strong> {applicant.degree || "N/A"}</p>
                        <p style={{ margin: "4px 0" }}><strong>College:</strong> {applicant.college || "N/A"}</p>
                        <p style={{ margin: "4px 0" }}><strong>Job Title:</strong> {applicant.jobTitle || "N/A"}</p>
                        <p style={{ margin: "4px 0" }}><strong>Experience:</strong> {applicant.experience || "N/A"}</p>
                        <p style={{ margin: "4px 0" }}><strong>Skills:</strong> {
                            applicant.skills && applicant.skills.length > 0 
                                ? applicant.skills.map(s => s.skillName).join(", ") 
                                : "N/A"
                        }</p>
                        <p style={{ margin: "4px 0" }}><strong>Resumes:</strong> {
                            applicant.resumes && applicant.resumes.length > 0 
                                ? applicant.resumes.map(r => (
                                    <button 
                                        key={r.resumeId} 
                                        onClick={() => window.open(`http://localhost:8080${r.resumeUrl}`, "_blank")}
                                        style={{ marginLeft: "5px", padding: "2px 6px", fontSize: "12px", cursor: "pointer" }}
                                    >
                                        View {r.resumeName}
                                    </button>
                                )) 
                                : "N/A"
                        }</p>
                    </div>

                    <form onSubmit={handleSend} style={{ marginTop: "25px", display: "flex", flexDirection: "column", gap: "12px" }}>
                        <label style={{ fontWeight: "bold", color: "#334155" }}>Compose Notification Message</label>
                        <textarea
                            rows="4"
                            placeholder="Write message to send..."
                            value={message}
                            onChange={(e) => setMessage(e.target.value)}
                            style={{ width: "100%", padding: "10px", borderRadius: "6px", border: "1px solid #ccc", fontSize: "15px" }}
                            required
                        />
                        <button 
                            type="submit" 
                            style={{ alignSelf: "flex-start", padding: "10px 25px", backgroundColor: "#10b981", color: "white", border: "none", borderRadius: "6px", cursor: "pointer", fontSize: "16px", fontWeight: "bold" }}
                        >
                            Send Notification
                        </button>
                    </form>
                </div>
            )}

            <h3 style={{ marginTop: "40px", borderBottom: "2px solid #10b981", paddingBottom: "10px", color: "#10b981" }}>Sent Notifications History</h3>
            {sentList.length === 0 ? (
                <p>No notifications sent yet.</p>
            ) : (
                <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "15px" }}>
                    <thead>
                        <tr style={{ backgroundColor: "#f1f5f9", textAlign: "left" }}>
                            <th style={{ padding: "10px", border: "1px solid #e2e8f0", width: "130px" }}>Application ID</th>
                            <th style={{ padding: "10px", border: "1px solid #e2e8f0", width: "180px" }}>Applicant Name</th>
                            <th style={{ padding: "10px", border: "1px solid #e2e8f0" }}>Message</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sentList.map(n => (
                            <tr key={n.id}>
                                <td style={{ padding: "10px", border: "1px solid #e2e8f0", fontWeight: "bold" }}>{n.applicationId}</td>
                                <td style={{ padding: "10px", border: "1px solid #e2e8f0" }}>{n.applicantName}</td>
                                <td style={{ padding: "10px", border: "1px solid #e2e8f0" }}>{n.msg}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}
