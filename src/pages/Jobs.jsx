import { useEffect, useState } from "react";

import JobService from "../services/JobService";
import ApplicationService from "../services/ApplicationService";

import SearchJob from "../components/jobs/SearchJob";
import JobCard from "../components/jobs/Jobcard";

import "../css/jobs.css";

export default function Jobs() {

    const userId = sessionStorage.getItem("userId");

    const [jobs, setJobs] = useState([]);

    const [filteredJobs, setFilteredJobs] = useState([]);

    const [appliedJobIds, setAppliedJobIds] = useState([]);

    const [title, setTitle] = useState("");

    const [location, setLocation] = useState("");

    const [company, setCompany] = useState("");

    useEffect(() => {

        loadJobs();

    }, []);

    async function loadJobs() {

        try {

            const response = await JobService.getAllJobs();

            setJobs(response.data);
            setFilteredJobs(response.data);

            if (userId) {
                const appRes = await ApplicationService.getApplicationsByUser(userId);
                setAppliedJobIds(appRes.data.map(app => app.jobId));
            }

        }
        catch (error) {

            console.log(error);

        }

    }

    function handleSearch() {
        let results = jobs;

        if (title.trim() !== "") {
            const terms = title.toLowerCase().split(/\s+/);
            results = results.filter(job => {
                return terms.every(term => 
                    (job.jobTitle && job.jobTitle.toLowerCase().includes(term))
                );
            });
        }

        if (location.trim() !== "") {
            const locTerm = location.toLowerCase();
            results = results.filter(job => 
                job.location && job.location.toLowerCase().includes(locTerm)
            );
        }

        if (company.trim() !== "") {
            const companyTerm = company.toLowerCase();
            results = results.filter(job => 
                job.companyName && job.companyName.toLowerCase().includes(companyTerm)
            );
        }

        setFilteredJobs(results);
    }

    function handleClear() {
        setTitle("");
        setLocation("");
        setCompany("");
        setFilteredJobs(jobs);
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
            loadJobs();
        } catch (error) {
            console.log(error);
            alert("Failed to apply for job. You may have already applied.");
        }
    }

    return (

        <div className="jobs-page">

            <h1>

                Available Jobs

            </h1>

            <SearchJob
                title={title}
                setTitle={setTitle}
                location={location}
                setLocation={setLocation}
                company={company}
                setCompany={setCompany}
                handleSearch={handleSearch}
                handleClear={handleClear}
            />

            <div className="jobs-list container">

                {

                    filteredJobs.length === 0 ?

                        <h2 className="text-center text-muted py-5">

                            No Jobs Found

                        </h2>

                        :

                        <div className="row row-cols-1 row-cols-md-2 g-4">
                            {filteredJobs.map((job) => (
                                <div key={job.jobId} className="col">
                                    <JobCard
                                        job={job}
                                        isApplied={appliedJobIds.includes(job.jobId)}
                                        onApply={handleApply}
                                    />
                                </div>
                            ))}
                        </div>

                }

            </div>

        </div>

    );

}
