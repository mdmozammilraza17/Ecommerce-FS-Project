import './Signup.css';
import { Link, useNavigate } from "react-router-dom";
import FreshGroceryImg from '../assets/Fresh-Grocery-Image.png';
import { FiCircle, FiPhone, FiUser } from "react-icons/fi";
import { FiMail } from "react-icons/fi";
import { FiLock } from "react-icons/fi";
import { FcGoogle } from "react-icons/fc";
import { useState } from 'react';
import api from "../api/api";
import { showSuccess, showError } from "../utils/toastUtil";
import { IoCloseCircle } from "react-icons/io5";
export default function Signup() {

    const [formData, setFormData] = useState({
        "firstName": "",
        "lastName": "",
        "emailAddress": "",
        "phoneNumber": "",
        "password": "",
        "confirmPassword": ""
    });

    const [errors, setErrors] = useState({});

    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData(prev => ({
            ...prev,
            [name]: value
        }))

        setErrors(prev => ({
            ...prev,
            [name]: ""
        }))
    };

    const validate = () => {
        let newErrors = {};

        if (!formData.firstName.trim()) {
            newErrors.firstName = "First Name is required";
        }

        if (!formData.lastName.trim()) {
            newErrors.lastName = "Last Name is required";
        }

        if (!formData.emailAddress.trim()) {
            newErrors.emailAddress = "Email Address is required";
        } else if (
            !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.emailAddress)
        ) {
            newErrors.emailAddress = "Please enter a valid email address";
        }

        if (!formData.phoneNumber.trim()) {
            newErrors.phoneNumber = "Phone Number is required";
        }

        if (!formData.password.trim()) {
            newErrors.password = "Password is required";
        }

        if (!formData.confirmPassword.trim()) {
            newErrors.confirmPassword = "Confirm Password is required";
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!validate()) {
            return;
        }

        try {
            setLoading(true);
            const response = await api.post("/api/auth/signup", formData);
            showSuccess(response.data.message);
            sessionStorage.setItem("email", formData.emailAddress);
            navigate("/verify-otp");

            console.log(sessionStorage.getItem("email"));

        }
        catch (error) {
            showError(
                error.response?.data?.message || "Something went wrong!"
            );
        }
        finally {
            setLoading(false);
        }
    };


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
                            <form className="signup-form" onSubmit={handleSubmit}>
                                <div className="name-row">
                                    <div className="form-group">

                                        <label htmlFor='firstName'>First Name</label>
                                        <div className="input-wrapper">
                                            <FiUser className="input-icon" />
                                            <input
                                                id='firstName'
                                                name='firstName'
                                                type="text" placeholder='Enter first name'
                                                value={formData.firstName}
                                                onChange={handleChange}
                                                className={errors.firstName ? "input-error" : ""}
                                            />
                                        </div>
                                        {errors.firstName && (

                                            <small className='error-text'>
                                                <IoCloseCircle className="error-icon" />
                                                {errors.firstName}
                                            </small>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor='lastName'>Last Name</label>
                                        <div className="input-wrapper">
                                            <FiUser className="input-icon" />
                                            <input
                                                id='lastName'
                                                name='lastName'
                                                type="text" placeholder='Enter last name'
                                                value={formData.lastName}
                                                onChange={handleChange}
                                                className={errors.lastName ? "input-error" : ""}
                                            />
                                        </div>
                                        {errors.lastName && (
                                            <small className='error-text'>
                                                <IoCloseCircle className="error-icon" />
                                                {errors.lastName}
                                            </small>
                                        )}
                                    </div>
                                </div>
                                <div className="form-group">

                                    <label htmlFor='emailAddress'>Email Address</label>
                                    <div className="input-wrapper">
                                        <FiMail className='input-icon' />
                                        <input
                                            id='emailAddress'
                                            name='emailAddress'
                                            type="email" placeholder='Enter email address'
                                            value={formData.emailAddress}
                                            onChange={handleChange}
                                            className={errors.emailAddress ? "input-error" : ""}

                                        />
                                    </div>
                                    {errors.emailAddress && (
                                        <small className='error-text'>
                                            <IoCloseCircle className="error-icon" />
                                            {errors.emailAddress}
                                        </small>
                                    )}
                                </div>

                                <div className="form-group">
                                    <label htmlFor='phoneNumber'>Enter Phone Number</label>
                                    <div className="input-wrapper">
                                        <FiPhone className='input-icon' />
                                        <input
                                            id='phoneNumber'
                                            name='phoneNumber'
                                            type="tel" placeholder='Enter Phone number'
                                            value={formData.phoneNumber}
                                            onChange={handleChange}
                                            className={errors.phoneNumber ? "input-error" : ""}
                                        />
                                    </div>

                                    {errors.phoneNumber && (
                                        <small className='error-text'>
                                            <IoCloseCircle className="error-icon" />
                                            {errors.phoneNumber}
                                        </small>
                                    )}
                                </div>

                                <div className="form-row">
                                    <div className="form-group">

                                        <label htmlFor='password'>Password</label>
                                        <div className="input-wrapper">
                                            <FiLock className='input-icon' />
                                            <input
                                                id='password'
                                                name='password'
                                                type="password" placeholder='Enter password'
                                                value={formData.password}
                                                onChange={handleChange}
                                                className={errors.password ? "input-error" : ""}
                                            />
                                        </div>
                                        {errors.password && (
                                            <small className='error-text'>
                                                <IoCloseCircle className="error-icon" />
                                                {errors.password}
                                            </small>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor='confirmPassword'>Confirm Password</label>
                                        <div className="input-wrapper">
                                            <FiLock className='input-icon' />
                                            <input
                                                id='confirmPassword'
                                                name='confirmPassword'
                                                type="password" placeholder='Confirm Password'
                                                value={formData.confirmPassword}
                                                onChange={handleChange}
                                                className={errors.confirmPassword ? "input-error" : ""}
                                            />
                                        </div>
                                        {errors.confirmPassword && (
                                            <small className='error-text'>
                                                <IoCloseCircle className="error-icon" />
                                                {errors.confirmPassword}
                                            </small>
                                        )}
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
                                    <button type='submit'
                                        className='signup-btn'
                                        disabled={loading}
                                    >{loading ? (
                                        <>
                                            <span className='spinner'></span>
                                            Creating Account...
                                        </>
                                    ) : ("Create Account")}</button>
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