import api from "../../api/axiosConfig";

export const loginApi = async (loginData) => {
    return api.post("/api/auth/login", loginData);
};