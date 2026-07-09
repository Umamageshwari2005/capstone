import API from "../api/axiosConfig";

class UserService {

    getUser(userId) {
        return API.get(`/users/${userId}`);
    }

    updateUser(userId, userData) {
        return API.put(`/users/${userId}`, userData);
    }

    deleteUser(userId) {
        return API.delete(`/users/${userId}`);
    }

}

export default new UserService();
