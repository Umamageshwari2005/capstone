import API from "../api/axiosConfig";

class ResumeService {

    getResumeByUser(userId) {

        return API.get(`/resumes/user/${userId}`);

    }

    uploadResume(formData) {

        return API.post("/resumes/upload", formData, {

            headers:{

                "Content-Type":"multipart/form-data"

            }

        });

    }

    deleteResume(resumeId){

        return API.delete(`/resumes/${resumeId}`);

    }

    updateResume(resumeId, formData) {
        return API.put(`/resumes/${resumeId}`, formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        });
    }

}

export default new ResumeService();
