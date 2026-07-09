import API from "../api/axiosConfig";

class SkillService {

    getSkillsByUser(userId) {
        return API.get(`/skills/user/${userId}`);
    }

    addSkill(data) {
        return API.post("/skills", data);
    }

    deleteSkill(skillId) {
        return API.delete(`/skills/${skillId}`);
    }

}

export default new SkillService();
