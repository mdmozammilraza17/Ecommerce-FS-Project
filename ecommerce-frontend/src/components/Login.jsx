import { useState } from "react";
import "./login.css";
import ImageBasket from "../assets/women-shopping.png";
import SignUp from "./Signup";
export default function Login({onSignUp}) {

    return (
        <>
            <div className="login-container">

                <div className="login-image">
                    <img src={ImageBasket} alt="basket image" />
                </div>


                <div className="login-main">
                    <h1>Login</h1>

                    <div className="form-group">
                        <input type="text" placeholder="Enter username" />
                    </div>

                    <div className="form-group">
                        <input type="password" placeholder="Enter password" />
                    </div>
                    <button>Log In</button>
                    <a href="#" id="forget-password">Forgot Password?</a>
                    <button onClick={onSignUp}>Create Account</button>
                </div>
            </div>
        </>
    )
}