import { useEffect, useState } from "react";

import ApplicationService from "../services/ApplicationService";
import NotificationService from "../services/NotificationService";

import "../css/applications.css";

export default function Applications() {

    const userId = sessionStorage.getItem("userId");

    const [applications, setApplications] = useState([]);

    const [filteredApplications, setFilteredApplications] = useState([]);

    const [search, setSearch] = useState("");

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadApplications();

    }, []);

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

    async function loadApplications() {

        try {

            const response =
                await ApplicationService.getApplicationsByUser(userId);

            setApplications(response.data);

            setFilteredApplications(response.data);

        }

        catch(error){

            console.log(error);

        }

        finally{

            setLoading(false);

        }

    }

    function handleSearch(value){

        setSearch(value);

        if(value.trim()===""){

            setFilteredApplications(applications);

            return;

        }

        const result=applications.filter(application=> {
            const appIdStr = application.applicationId ? application.applicationId.toString() : "";
            const titleStr = application.jobTitle ? application.jobTitle.toLowerCase() : "";
            const companyStr = application.companyName ? application.companyName.toLowerCase() : "";
            const searchVal = value.toLowerCase();

            return appIdStr.includes(searchVal) || 
                   titleStr.includes(searchVal) || 
                   companyStr.includes(searchVal);
        });

        setFilteredApplications(result);

    }

    async function withdrawApplication(id){

        if(!window.confirm("Withdraw this Application?")){

            return;

        }

        try{

            await ApplicationService.deleteApplication(id);

            alert("Application Withdrawn Successfully");

            loadApplications();

        }

        catch(error){

            console.log(error);

        }

    }

    if(loading){

        return(

            <div className="loading">

                <h2>

                    Loading...

                </h2>

            </div>

        );

    }

    return(

        <div
    className="applications-page"
    style={{
        background: "linear-gradient(135deg,#e0f2fe,#ede9fe,#dcfce7)",
        minHeight: "100vh",
        padding: "30px",
        borderRadius: "20px"
    }}
>

            <div className="applications-header">

                <h1>

                    My Applications

                </h1>

                <input

                    type="text"

                    placeholder="Search by Title, Company, or Application ID..."

                    value={search}

                    onChange={(e)=>handleSearch(e.target.value)}

                />

            </div>

            {

                filteredApplications.length===0?

                <div className="empty-box">

                    No Applications Found

                </div>

                :

                <table
    className="applications-table"
    style={{
        background: "linear-gradient(135deg,#ffffff,#eef6ff)",
        borderRadius: "18px",
        overflow: "hidden",
        boxShadow: "0 12px 30px rgba(0,0,0,0.15)"
    }}
>

                    <thead>

                        <tr>

                            <th>Application ID</th>

                            <th>Job ID</th>

                            <th>Company Name</th>

                            <th>Job Title</th>

                            <th>Date Applied</th>

                            <th>Status</th>

                            <th>Action</th>

                        </tr>

                    </thead>

                    <tbody>

                        {

                            filteredApplications.map(application=>(

                                <tr
                                    key={application.applicationId}
                                >

                                    <td>

                                        {application.applicationId}

                                    </td>

                                    <td>

                                        {application.jobId}

                                    </td>

                                    <td>

                                        {application.companyName || "N/A"}

                                    </td>

                                    <td>

                                        {application.jobTitle || "N/A"}

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
                                        <div style={{ display: "flex", gap: "10px" }}>
                                            <button

                                                className="withdraw-btn"

                                                onClick={()=>withdrawApplication(application.applicationId)}

                                            >

                                                Withdraw

                                            </button>
                                        </div>

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
