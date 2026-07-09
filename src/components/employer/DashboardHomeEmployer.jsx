import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import JobService from "../../services/JobService";
import ApplicationService from "../../services/ApplicationService";

import WelcomeEmployer from "./WelcomeEmployer";
import EmployerStatistics from "./EmployerStatistics";
import EmployerSearchBar from "./EmployerSearchBar";
import LatestPostedJobs from "./LatestPostedJobs";
import RecentApplicants from "./RecentApplicants";

import "../../css/employerDashboard.css";

export default function DashboardHomeEmployer() {

    const navigate = useNavigate();

    const employerId = sessionStorage.getItem("userId");

    const [jobs, setJobs] = useState([]);

    const [filteredJobs, setFilteredJobs] = useState([]);

    const [applicants, setApplicants] = useState([]);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadDashboard();

    }, []);

    async function loadDashboard() {

        try {

            const jobResponse =
                await JobService.getJobsByEmployer(employerId);

            setJobs(jobResponse.data);

            setFilteredJobs(jobResponse.data);

            const applicantResponse =
                await ApplicationService.getApplicantsByEmployer(employerId);
            setApplicants(applicantResponse.data);

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
            loadDashboard();
        } catch (error) {
            console.log(error);
            alert("Failed to Delete Job");
        }
    }

    function viewApplicants(jobId) {
        navigate(`/applicants/${jobId}`);
    }

    function handleSearch(keyword) {

        if (keyword.trim() === "") {

            setFilteredJobs(jobs);

            return;

        }

        const result = jobs.filter(job =>

            job.jobTitle
                .toLowerCase()
                .includes(keyword.toLowerCase())

        );

        setFilteredJobs(result);

    }

    if (loading) {

        return (

            <div className="loading">

                <h2>Loading Dashboard...</h2>

            </div>

        );

    }

    const closedCount = applicants.filter(app => 
        app.status === "Selected" || 
        app.status === "Rejected" || 
        app.status.toLowerCase().includes("rejected")
    ).length;

    return (

        <div
    className="employer-dashboard"
    style={{
        background: "linear-gradient(135deg,#93c5fd,#a5b4fc,#c4b5fd)",
        minHeight: "100vh",
        padding: "25px",
        borderRadius: "20px"
    }}
>

            <WelcomeEmployer />

            <EmployerStatistics

                totalJobs={jobs.length}

                applicants={applicants.length}

                closed={closedCount}

            />

            <div className="employer-dashboard-bottom">

                <LatestPostedJobs

                    jobs={filteredJobs}
                    onEdit={editJob}
                    onDelete={deleteJob}
                    onApplicants={viewApplicants}

                />

                <RecentApplicants

                    applicants={applicants}

                />

            </div>

            <footer className="dashboard-footer">

                © 2026 CareerCrafter | Employer Portal

            </footer>

        </div>

    );

}
