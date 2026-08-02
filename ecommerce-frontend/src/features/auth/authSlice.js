import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { loginApi } from "./authAPI"


const initialState = {
    user: null,
    accessToken: null,
    refreshToken: null,
    isAuthenticated: false,
    loading: false,
    error: null,
}

// Async Thunk
export const loginUser = createAsyncThunk(
    "auth/loginUser",
    async (loginData, thunkAPI) => {
        try {
            const response = await loginApi(loginData);
            return response.data;
        } catch (error) {
            return thunkAPI.rejectWithValue(
                error.response?.data?.message || "Login Failed"
            );
        }
    }
);

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    extraReducers: (builder) => {
        builder
            // Pending
            .addCase(loginUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })


            .addCase(loginUser.fulfilled, (state, action) => {
                state.loading = false,
                    state.user = {
                        userId: action.payload.userId,
                        firstName: action.payload.firstName,
                        lastName: action.payload.lastName,
                        emailAddress: action.payload.emailAddress,
                        role: action.payload.role,
                    };

                state.accessToken = action.payload.accessToken;
                state.refreshToken = action.payload.refreshToken;
                state.isAuthenticated = true;

                localStorage.setItem("accessToken", action.payload.accessToken);
                localStorage.setItem("refreshToken", action.payload.refreshToken);
            })

             // Failed
            .addCase(loginUser.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
                state.isAuthenticated = false;
            });
    }
})
export default authSlice.reducer;