import API from "../api/axiosConfig";

class JobService {

    getAllJobs() {
        return API.get("/jobs");
    }

    getJobById(jobId) {
        return API.get(`/jobs/${jobId}`);
    }

    getJobsByEmployer(employerId) {
        return API.get(`/jobs/employer/${employerId}`);
    }

    addJob(jobData) {
        return API.post("/jobs", jobData);
    }

    updateJob(jobId, jobData) {
        return API.put(`/jobs/${jobId}`, jobData);
    }

    deleteJob(jobId) {
        return API.delete(`/jobs/${jobId}`);
    }

    searchByTitle(title) {
        return API.get(`/jobs/search/title/${title}`);
    }

    searchByLocation(location) {
        return API.get(`/jobs/search/location/${location}`);
    }

}

export default new JobService();

