import api from "../api/axiosConfig";

const register = (user) => {

    return api.post("/auth/register", user);

};

const login = (loginData) => {

    return api.post("/auth/login", loginData);

};

const logout = () => {

    sessionStorage.clear();

};

const forgotPassword = (data) => {
    return api.post("/auth/forgot-password", data);
};

export default {

    register,

    login,

    logout,

    forgotPassword

};
