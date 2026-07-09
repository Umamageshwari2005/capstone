import api from "../api/axiosConfig";

const uploadCertification = (formData) => {
    return api.post("/certifications/upload", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};

const getCertificationsByUser = (userId) => {
    return api.get(`/certifications/user/${userId}`);
};

const deleteCertification = (id) => {
    return api.delete(`/certifications/${id}`);
};

export default {
    uploadCertification,
    getCertificationsByUser,
    deleteCertification
};
