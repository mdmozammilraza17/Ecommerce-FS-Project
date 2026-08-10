import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { loginApi, currentUserApi } from "./authAPI";

// ======================================================
// INITIAL STATE
// ======================================================

const initialState = {
    user: null,
    accessToken: null,
    isAuthenticated: false,

    // App start / refresh ke time
    authLoading: true,

    // Login button click ke time
    loginLoading: false,

    error: null,
};

// ======================================================
// LOGIN USER
// ======================================================

export const loginUser = createAsyncThunk(
    "auth/loginUser",

    async (loginData, thunkAPI) => {
        try {

            const response = await loginApi(loginData);

            return response.data;

        } catch (error) {

            console.log("Backend Error:", error.response?.data);

            return thunkAPI.rejectWithValue(
                error.response?.data?.message ||
                error.response?.data?.error ||
                "Invalid credentials"
            );
        }
    }
);

// ======================================================
// INITIALIZE AUTH
// App start / Browser refresh
// ======================================================

export const initializeAuth = createAsyncThunk(
    "auth/initializeAuth",

    async (_, thunkAPI) => {
        try {
            const accessToken =
                localStorage.getItem("accessToken");

            if (!accessToken) {
                return null;
            }

            // If Token exits check the current user
            const response = await currentUserApi();

            return response.data;

        } catch (error) {

            // Token invalid / expired
            localStorage.removeItem("accessToken");

            return thunkAPI.rejectWithValue(
                error.response?.data?.message ||
                "Session expired"
            );
        }
    }
);

// ======================================================
// AUTH SLICE
// ======================================================

const authSlice = createSlice({

    name: "auth",

    initialState,

    extraReducers: (builder) => {

        builder

            // ==================================================
            // LOGIN - PENDING
            // ==================================================

            .addCase(loginUser.pending, (state) => {

                state.loginLoading = true;

                // Previous login error clear
                state.error = null;
            })


            // ==================================================
            // LOGIN - SUCCESS
            // ==================================================

            .addCase(loginUser.fulfilled, (state, action) => {

                state.loginLoading = false;

                state.user = {
                    userId: action.payload.userId,
                    firstName: action.payload.firstName,
                    lastName: action.payload.lastName,
                    emailAddress: action.payload.emailAddress,
                    role: action.payload.role,
                };

                state.accessToken =
                    action.payload.accessToken;

                state.isAuthenticated = true;

                state.error = null;

                // Save access Token in localStorage
                localStorage.setItem(
                    "accessToken",
                    action.payload.accessToken
                );
            })


            // ==================================================
            // LOGIN - FAILED
            // ==================================================

            .addCase(loginUser.rejected, (state, action) => {

                state.loginLoading = false;
                state.error = action.payload;
            })


            // ==================================================
            // INITIALIZE AUTH - PENDING
            // App start / Refresh
            // ==================================================

            .addCase(initializeAuth.pending, (state) => {

                state.authLoading = true;
            })


            // ==================================================
            // INITIALIZE AUTH - SUCCESS
            // Token is valid
            // ==================================================

            .addCase(initializeAuth.fulfilled, (state, action) => {

                state.authLoading = false;

                if (!action.payload) {

                    state.user = null;
                    state.accessToken = null;
                    state.isAuthenticated = false;
                    state.error = null;

                    return;
                }

                // Token valid 
                state.user = {
                    userId: action.payload.userId,
                    firstName: action.payload.firstName,
                    lastName: action.payload.lastName,
                    emailAddress: action.payload.emailAddress,
                    role: action.payload.role,
                };

                state.accessToken =
                    localStorage.getItem("accessToken");

                state.isAuthenticated = true;

                state.error = null;
            })


            // ==================================================
            // INITIALIZE AUTH - FAILED
            // Token invalid / expired
            // ==================================================

            .addCase(initializeAuth.rejected, (state) => {

                state.authLoading = false;
                state.user = null;
                state.accessToken = null;
                state.isAuthenticated = false;
                state.error = null;
            });
    }
});

export default authSlice.reducer;