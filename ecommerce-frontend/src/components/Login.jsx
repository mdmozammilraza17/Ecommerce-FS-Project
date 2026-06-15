import "./Login.css";
import { useNavigate } from "react-router-dom";
import ImageBasket from "../assets/women-shopping.png";
import { useState } from "react";
import axios from "axios";

import { useDispatch } from "react-redux";
import { login } from "../features/auth/authSlice";

export default function Login({ onSignUp }) {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {
        try {

            const response = await axios.post(
                "http://localhost:8088/api/auth/token",
                {
                    username: username,
                    password: password,
                }
            );

            // get token
            const token = response.data.token;

            // save token
            localStorage.setItem("token", token);

            // update redux state
            dispatch(
                login({
                    username: username
                })
            );

            alert("Login successful!");

            navigate("/home");

        } catch (error) {
            console.log(error);
            alert("Invalid username or password");
        }
    };

    return (
        <>
            <div className="login-container">

                <div className="login-image">
                    <img src={ImageBasket} alt="Girl with bag" />
                </div>

                <div className="login-main">
                    
                    <h1>Login</h1>

                    <div className="form-group">
                        <input
                            type="text"
                            placeholder="Enter username"
                            value={username}
                            onChange={(e) =>
                                setUsername(e.target.value)
                            }
                        />
                    </div>

                    <div className="form-group">
                        <input
                            type="password"
                            placeholder="Enter password"
                            value={password}
                            onChange={(e) =>
                                setPassword(e.target.value)
                            }
                        />
                    </div>

                    <button onClick={handleLogin}>
                        Log In
                    </button>

                    <a href="#" id="forget-password">
                        Forgot Password?
                    </a>

                    <button
                        onClick={() => navigate("/signup")}
                    >
                        Create Account
                    </button>

                </div>

            </div>
        </>
    );
}