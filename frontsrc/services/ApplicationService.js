import API from "../api/axiosConfig";

class ApplicationService {

    getApplicantDetails(applicationId) {
        return API.get(`/applications/details/${applicationId}`);
    }

    searchApplicants(employerId, applicantId) {
        return API.get(`/applications/employer/${employerId}/search`, {
            params: { applicantId }
        });
    }

    getApplicationsByUser(userId) {
        return API.get(`/applications/user/${userId}`);
    }

    applyJob(data) {
        return API.post("/applications", data);
    }

    getApplicantsByEmployer(employerId) {
        return API.get(`/applications/employer/${employerId}`);
    }

    updateApplicationStatus(applicationId, data) {
        return API.put(`/applications/${applicationId}`, data);
    }

    deleteApplication(applicationId) {
        return API.delete(`/applications/${applicationId}`);
    }

}

export default new ApplicationService();
