import api from "../../api/axiosConfig";

export const loginApi = async (loginData) => {
    return api.post("/api/auth/login", loginData);
};

// Current user api
export const currentUserApi = () =>
{
    return api.get("/api/auth/me");
}