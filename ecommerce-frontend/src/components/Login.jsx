import { useEffect, useState } from 'react';
import { FcGoogle } from 'react-icons/fc';
import { FiLock, FiMail, FiUser } from 'react-icons/fi';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from "react-router-dom";
import FreshGroceryStoreImage from '../assets/Fresh-Grocery-Image.png';
import '../components/Login.css';
import { loginUser } from '../features/auth/authSlice';
import { showError } from '../utils/toastUtil';

export default function Login() {

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const { isAuthenticated, loginLoading, error } = useSelector((state) => state.auth);

    const [formData, setFormData] = useState(
        {
            username: "",
            password: ""
        }
    );

    useEffect(() => {
        if (error) {
            showError(error);
        }
    }, [error]);

    async function handleSubmit(e) {
        e.preventDefault();
        const result = await dispatch(loginUser(formData));

        if (loginUser.fulfilled.match(result)) {
            navigate("/home", { replace: true });
        }
    }

    return (
        <>
            <div className="login-page">
                <div className="login-container">
                    <div className="login-left-image">
                        <img src={FreshGroceryStoreImage} alt="Left Image of Fresh Grocery Store" />
                    </div>
                    <div className="login-right-section">
                        <form onSubmit={handleSubmit} className='loginForm'>
                            <div className="header-content">
                                <div className="password-icon">
                                    <FiLock className="lock-icon" />
                                </div>
                                <div className="login-content">
                                    <h3>Login</h3>
                                    <p>Login with your email and password</p>
                                </div>
                            </div>
                            <div className="email-password-container">

                                <div className="form-group">
                                    <label htmlFor="email">Email</label>
                                    <div className="input-wrapper">
                                        <FiUser className="input-icon" />
                                        <input
                                            type="email"
                                            id="email"
                                            name="username"
                                            value={formData.username}
                                            onChange={(e) => {
                                                setFormData(
                                                    {
                                                        ...formData,
                                                        [e.target.name]: e.target.value
                                                    }
                                                );
                                            }
                                            }
                                            placeholder="Enter your email or mobile number"
                                        />
                                    </div>
                                </div>

                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <div className="input-wrapper">
                                        <FiLock className="input-icon" />
                                        <input
                                            type="password"
                                            id="password"
                                            name="password"
                                            value={formData.password}
                                            onChange={(e) => {
                                                setFormData(
                                                    {
                                                        ...formData,
                                                        [e.target.name]: e.target.value
                                                    }
                                                )
                                            }
                                            }
                                            placeholder="Enter your password"
                                        />
                                    </div>
                                </div>

                            </div>

                            <div className="remember-forgot-container">
                                <div className="remember-me">
                                    <input type="checkbox" id="remember" />
                                    <label htmlFor="remember">Remember me</label>
                                </div>
                                <Link to="/forgot-password">Forgot Password?</Link>
                            </div>

                            <button type='submit' disabled={loginLoading}>{loginLoading ? "Logging in..." : "Login"}</button>

                        </form>
                        <div className="login-divider">OR</div>
                        <div className="social-login">

                            <button type="button" className="otp-login-btn">
                                <FiMail className="otp-msg-icon" />
                                <span>Continue with OTP</span>
                            </button>

                            <button type="button" className="login-btn">
                                <FcGoogle className="login-google-icon" />
                                <span>Continue with Google</span>
                            </button>
                        </div>

                        <div className="dont-have-account">
                            <p>Don't have account?</p>
                            <Link to="/signup">Create Account</Link>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}