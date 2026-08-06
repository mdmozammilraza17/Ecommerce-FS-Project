import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
});

// ======================================================
// REQUEST INTERCEPTOR
// ======================================================

api.interceptors.request.use(
    (config) => {

        const token =
            localStorage.getItem("accessToken");

        if (token) {
            config.headers.Authorization =
                `Bearer ${token}`;
        }

        return config;
    },

    (error) => Promise.reject(error)
);


// ======================================================
// RESPONSE INTERCEPTOR
// ======================================================

api.interceptors.response.use(

    (response) => response,

    (error) => {

        const requestUrl =
            error.config?.url;

        const isLoginRequest =
            requestUrl?.includes("/api/auth/login");

        if (
            error.response?.status === 401 &&
            !isLoginRequest
        ) {

            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken");

            window.location.href = "/login";
        }

        return Promise.reject(error);
    }
);

export default api;