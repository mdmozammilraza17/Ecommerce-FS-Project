import './Signup.css';
import { Link } from 'react-router-dom';
import FreshGroceryImg from '../assets/Fresh-Grocery-Image.png';
import { FiPhone, FiUser } from "react-icons/fi";
import { FiMail } from "react-icons/fi";
import { FiLock } from "react-icons/fi";
import { FcGoogle } from "react-icons/fc";
export default function Signup() {
    return (
        <>
            <div className="signup-page">

                <div className="signup-container">

                    <div className="signup-image-section">
                        <img src={FreshGroceryImg} alt="Fresh Grocery Image" />
                    </div>
                    <div className="signup-form-section">

                        <div className="signup-card">
                            <div className="signup-header">
                                <h1>Create Account</h1>
                                <p>Fill in the below to create your account</p>
                            </div>
                            <form className="signup-form">
                                <div className="name-row">
                                    <div className="form-group">

                                        <label htmlFor='firstName'>First Name</label>
                                        <div className="input-wrapper">
                                            <FiUser className="input-icon" />
                                            <input
                                                id='firstName'
                                                name='firstName'
                                                type="text" placeholder='Enter first name' />
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor='lastName'>Last Name</label>
                                        <div className="input-wrapper">
                                            <FiUser className="input-icon" />
                                            <input
                                                id='lastName'
                                                name='lastName'
                                                type="text" placeholder='Enter last name' />
                                        </div>
                                    </div>
                                </div>
                                <div className="form-group">

                                    <label htmlFor='emailAddress'>Email Address</label>
                                    <div className="input-wrapper">
                                        <FiMail className='input-icon' />
                                        <input
                                            id='emailAddress'
                                            name='emailAddress'
                                            type="email" placeholder='Enter email address' />
                                    </div>
                                </div>

                                <div className="form-group">
                                    <label htmlFor='mobileNumber'>Enter Phone Number</label>
                                    <div className="input-wrapper">
                                        <FiPhone className='input-icon' />
                                        <input
                                            id='mobileNumber'
                                            name='mobileNumber'
                                            type="tel" placeholder='Enter mobile number' />
                                    </div>
                                </div>

                                <div className="form-row">
                                    <div className="form-group">

                                        <label htmlFor='password'>Password</label>
                                        <div className="input-wrapper">
                                            <FiLock className='input-icon' />
                                            <input
                                                id='password'
                                                name='password'
                                                type="password" placeholder='Enter password' />
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor='confirmPassword'>Confirm Password</label>
                                        <div className="input-wrapper">
                                            <FiLock className='input-icon' />
                                            <input
                                                id='confirmPassword'
                                                name='confirmPassword'
                                                type="password" placeholder='Confirm Password' />
                                        </div>
                                    </div>
                                </div>

                                <div className="email-verification">
                                    <FiMail className="verification-icon" />
                                    <div className="email-wrapper">
                                        <h4>Email Verification</h4>
                                        <p>We will send a 6-digit OTP to your email address for verification.</p>
                                    </div>
                                </div>
                                <div className="signup-actions">
                                    <button type='submit'>Create Account</button>
                                </div>
                            </form>

                            <div className="signup-divider">
                                <span>OR</span>
                            </div>
                            <div className="social-login">
                                <button type="button" className="google-login-btn">
                                    <FcGoogle className="google-icon" />
                                    <span>Continue with Google</span>
                                </button>
                            </div>
                            <div className="signup-footer">
                                <p>
                                    Already have an account?
                                    <Link to='/login'>Log In</Link>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}