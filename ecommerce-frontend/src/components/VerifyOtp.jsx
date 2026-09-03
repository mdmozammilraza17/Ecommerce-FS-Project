import { useEffect, useRef, useState } from 'react';
import { FaCheck, FaClock } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { FiMail } from "react-icons/fi";
import { RiShieldCheckFill } from "react-icons/ri";
import { useNavigate } from 'react-router-dom';
import api from "../api/api";
import AlmostThereImage from '../assets/Almost-There-Image.png';
import { showError, showSuccess } from '../utils/toastUtil';
import './VerifyOtp.css';
import axios from 'axios';

export default function VerifyOtp() {
    const inputArr = [0, 1, 2, 3, 4, 5];

    const inputRefs = useRef([]);

    const [otp, setOtp] = useState(["", "", "", "", "", ""]);

    const [loading, setLoading] = useState(false);

    const [resendTimer, setResendTimer] = useState(60);

    const [resending, setResending] = useState(false);

    useEffect(() => {
        const interval = setInterval(() => {
            setResendTimer((prev) => {
                if (prev <= 1) {
                    clearInterval(interval);
                    return 0;
                }
                return prev - 1;
            });
        }, 1000);
        return () => clearInterval(interval);
    }, [resendTimer])

    const formatTime = (seconds) => {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = seconds % 60;

        return `${String(minutes).padStart(2, "0")}:${String(remainingSeconds).padStart(2, "0")}`;

    }

    const handleResendOtp = async () => {
        try {
            setResending(true);
            const response = await api.post("/api/auth/resend-otp",
                {
                    emailAddress: email
                }
            );
            setResendTimer(response.data.resendAvailableIn);
        }
        catch (error) {
            showError(
                error.response.message || "Failed to resend OTP"
            );
        }
        finally {
            setResending(false);
        }
    }

    useEffect(() => {
        inputRefs.current[0]?.focus();
    }, [])

    const handleChange = (e, index) => {
        const value = e.target.value;

        if (!/^\d?$/.test(value)) return;

        const updatedOtp = [...otp];
        updatedOtp[index] = value;
        setOtp(updatedOtp);

        if (value && index < inputArr.length - 1) {
            inputRefs.current[index + 1]?.focus();
        }
    }

    const handleKeyDown = (e, index) => {
        if (e.key === "Backspace") {
            if (otp[index] !== "") {
                const updatedOtp = [...otp];
                updatedOtp[index] = "";
                setOtp(updatedOtp);
                return;
            }
            if (index > 0) {
                inputRefs.current[index - 1]?.focus();
            }
        }
    }

    const navigate = useNavigate();

    const email = sessionStorage.getItem("email");

    useEffect(() => {
        if (!email) {
            showError("Please create your account first!");
            navigate("/signup");
        }
    }, [email, navigate])

    const handleSubmit = async (e) => {
        e.preventDefault();
        const otpValue = otp.join("");

        if (otpValue.length !== 6) {
            showError("Please enter a valid 6 digit OTP.")
            return;
        }
        try {
            setLoading(true)
            const response = await api.post("/api/auth/verify-otp", {
                email: email,
                otp: otpValue
            });

            showSuccess(response.data.message);

            setTimeout(() => {
                sessionStorage.removeItem("email");
                navigate("/login")
            }, 3000)

        }
        catch (error) {
            showError(
                error.response?.data?.message || "Something went wrong!"
            )
        }
        finally {
            setLoading(false);
        }
    }

    const handlePaste = (e) => {
        e.preventDefault();

        const pastedData = e.clipboardData.getData("text").trim();

        if (!/^\d{6}$/.test(pastedData)) return;

        const otpArray = pastedData.split("");

        setOtp(otpArray);

        inputRefs.current[5]?.focus();
    };

    return (
        <>
            <div className="otp-verification-page">
                <div className="otp-verification-container">
                    <div className="otp-verification-image">
                        <img src={AlmostThereImage} alt="Almost there image in the left side" />
                    </div>

                    <div className="otp-right-section">
                        <form onSubmit={handleSubmit}
                        >
                            <div className="email-icon">
                                <FiMail className="verification-icon" />
                            </div>
                            <div className="verify-email-content">
                                <h3>Verify Your Email</h3>
                                <p>We have sent a 6-digit OTP to</p>
                                <p className="email-address"><b>{email}</b></p>
                            </div>
                            <div className="otp-boxes">
                                {inputArr.map((item, index) => {
                                    return <input
                                        type="text"
                                        inputMode="numeric"
                                        autoComplete="one-time-code"
                                        onPaste={handlePaste}
                                        key={index}
                                        value={otp[index]}
                                        maxLength={1}
                                        ref={(el) => {
                                            inputRefs.current[index] = el;
                                        }}
                                        onChange={(e) => handleChange(e, index)}
                                        onKeyDown={(e) => handleKeyDown(e, index)}
                                    />
                                })}
                            </div>
                            <div className="resend-otp">
                                <span>Didn't receive the code?</span>

                                {resendTimer > 0 ? (
                                    <>
                                        <span className="resend-link-disable">
                                            Resend OTP in
                                        </span>

                                        <span className="time-sec">
                                            {formatTime(resendTimer)}
                                        </span>
                                    </>
                                ) : (
                                    <span
                                        className="resend-link"
                                        onClick={!resending ? handleResendOtp : undefined}
                                    >
                                        {resending ? 'Sending...' : "Resend OTP"}
                                    </span>
                                )}
                            </div>
                            <div className="secure-verification">

                                <div className="secure-icon">

                                    <RiShieldCheckFill className="shield-icon" />
                                </div>

                                <div className="secure-content">
                                    <h4>Secure Verification</h4>
                                    <p>Your verification code will be valid for 5 minutes.</p>
                                    <p>Please do not share this code with anyone.</p>
                                </div>
                            </div>
                            <div className="verify-btn">
                                <button type='submit' disabled={loading}>{
                                    loading ? (
                                        <>
                                            <span className="spinner"></span>
                                            Verifying OTP...
                                        </>
                                    ) :
                                        (
                                            "Verify & Active Account"
                                        )
                                }</button>
                            </div>
                        </form>
                        <div className="verification-divider">
                            <span>OR</span>
                        </div>
                        <div className="continue-with-google">
                            <button type="button" className="google-login-btn">
                                <FcGoogle className="google-icon" />
                                <span>Continue with Google</span>
                            </button>
                        </div>

                        {resendTimer > 0 ? <div className="disable-state">
                            <div className="watch">
                                <FaClock />
                            </div>
                            <div className="disable-state-container">
                                <h4>Please wait</h4>
                                <p>You can resend OTP after {formatTime(resendTimer)} seconds</p>
                            </div>
                        </div> : <div className="enable-state">
                            <div className="success-icon">
                                <FaCheck />
                            </div>
                            <div className="enable-state-container">
                                <h4>You can resend now</h4>
                                <p>Click on 'Resend OTP' to receive a new code</p>
                            </div>
                        </div>}



                    </div>
                </div>
            </div>
        </>
    )
}