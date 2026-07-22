import { FcGoogle } from 'react-icons/fc';
import FreshGroceryStoreImage from '../assets/Fresh-Grocery-Image.png';
import { FiLock, FiMail } from 'react-icons/fi';
import { FiUser } from 'react-icons/fi';
import { Link } from 'react-router-dom';
import '../components/Login.css';

export default function Login() {
    return (
        <>
            <div className="login-page">
                <div className="login-container">
                    <div className="login-left-image">
                        <img src={FreshGroceryStoreImage} alt="Left Image of Fresh Grocery Store" />
                    </div>
                    <div className="login-right-section">
                        <form className='loginForm'>
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

                            <button>Login</button>

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