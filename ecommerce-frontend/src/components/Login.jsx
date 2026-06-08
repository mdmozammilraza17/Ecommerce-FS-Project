import "./Login.css";
import { useNavigate } from "react-router-dom";
import ImageBasket from "../assets/women-shopping.png";
export default function Login({onSignUp}) {
    const navigate = useNavigate();

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
                    <button onClick={()=> navigate("/signup")}>
                        Create Account</button>
                </div>
            </div>
        </>
    )
}