import API from "../api/axiosConfig";

class NotificationService {

    getNotificationsByUser(userId) {
        return API.get(`/notifications/user/${userId}`);
    }

    addNotification(data) {
        return API.post("/notifications", data);
    }

}

export default new NotificationService();
