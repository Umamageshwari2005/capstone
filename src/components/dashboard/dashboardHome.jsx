import { useEffect, useState } from "react";

import UserService from "../../services/UserService";
import JobService from "../../services/JobService";
import ResumeService from "../../services/ResumeService";
import NotificationService from "../../services/NotificationService";
import ApplicationService from "../../services/ApplicationService";

import WelcomeBanner from "./WelcomeBanner";
import StatisticsCards from "./StatisticsCards";
import LatestJobs from "./LatestJobs";
import RecommendedJobs from "./RecommendedJobs";
import RecentApplications from "./RecentApplications";

import "../../css/dashboardHome.css";

export default function DashboardHome() {

    const userId = sessionStorage.getItem("userId");

    const [user,setUser]=useState({});

    const [jobs,setJobs]=useState([]);

    const [applications,setApplications]=useState([]);

    const [notifications,setNotifications]=useState([]);

    const [resumes,setResumes]=useState([]);

    const [recommendedJobs, setRecommendedJobs] = useState([]);

    useEffect(()=>{

        loadDashboard();

    },[]);

    async function loadDashboard(){

        try{

            const userRes = await UserService.getUser(userId);

            const jobRes = await JobService.getAllJobs();

            const appRes =
            await ApplicationService.getApplicationsByUser(userId);

            const resumeRes =
            await ResumeService.getResumeByUser(userId);

            const notifyRes =
            await NotificationService.getNotificationsByUser(userId);

            setUser(userRes.data);

            setJobs(jobRes.data);

            setApplications(appRes.data);

            setResumes(resumeRes.data);

            setNotifications(notifyRes.data);

            // Compute dynamic recommendations based on user skills, degree and applications
            const userSkillsList = (userRes.data.skills || []).map(s => s.skillName.toLowerCase());
            const userRole = (userRes.data.degree || "").toLowerCase();

            const matches = jobRes.data.map(job => {
                let score = 0;
                // Match skills
                const jobSkills = (job.requiredSkills || "").toLowerCase();
                userSkillsList.forEach(skill => {
                    if (jobSkills.includes(skill)) {
                        score += 2;
                    }
                });
                // Match title/degree
                const jobTitle = (job.jobTitle || "").toLowerCase();
                if (userRole && jobTitle.includes(userRole)) {
                    score += 1;
                }
                return { job, score };
            })
            .filter(item => item.score > 0)
            .sort((a, b) => b.score - a.score)
            .map(item => item.job)
            .slice(0, 3); // top 3 recommendations

            if (matches.length === 0) {
                // fallback to available jobs that are not the first three
                setRecommendedJobs(jobRes.data.slice(3, 6));
            } else {
                setRecommendedJobs(matches);
            }

        }

        catch(err){

            console.log(err);

        }

    }

    async function handleApply(jobId) {
        if (!userId) {
            alert("Please log in to apply for jobs");
            return;
        }
        try {
            await ApplicationService.applyJob({
                status: "Applied",
                userId: parseInt(userId),
                jobId: parseInt(jobId)
            });
            alert("Applied for Job Successfully");
            loadDashboard();
        } catch (error) {
            console.log(error);
            alert("Failed to apply for job. You may have already applied.");
        }
    }

    const appliedJobIds = (applications || []).map(app => app.jobId);

    return(

        <div className="dashboard-home">

            <WelcomeBanner
                user={user}
            />

            <StatisticsCards

                jobs={jobs.length}

                resumes={resumes.length}

                applications={applications.length}

                notifications={notifications.length}

            />

            

    <div
    style={{
        display: "flex",
        gap: "20px",
        alignItems: "flex-start"
    }}
>

    <div style={{ flex: 2 }}>
        <LatestJobs 
            jobs={jobs} 
            appliedJobIds={appliedJobIds} 
            onApply={handleApply} 
        />
        
        <RecommendedJobs 
            jobs={recommendedJobs} 
        />
    </div>

    <div style={{ flex: 1 }}>
        <RecentApplications applications={applications} />
    </div>



</div>

            <footer className="dashboard-footer">

                © 2026 CareerCrafter | Developed by Umamageshwari

            </footer>

        </div>

    );

}
