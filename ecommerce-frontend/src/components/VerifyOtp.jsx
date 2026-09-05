import { useEffect, useRef, useState } from 'react';
import { FaCheck, FaClock } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { FiMail } from "react-icons/fi";
import { RiLockLine, RiShieldCheckFill } from "react-icons/ri";
import { useNavigate } from 'react-router-dom';
import api from "../api/api";
import AlmostThereImage from '../assets/Almost-There-Image.png';
import { showError, showSuccess } from '../utils/toastUtil';
import SecurityIcon from './SecurityIcon';
import './VerifyOtp.css';

export default function VerifyOtp() {
    const inputArr = [0, 1, 2, 3, 4, 5];

    const inputRefs = useRef([]);

    const [otp, setOtp] = useState(["", "", "", "", "", ""]);

    const [loading, setLoading] = useState(false);

    const [resendTimer, setResendTimer] = useState(60);

    const [seconds, setSeconds] = useState(3);

    const [resending, setResending] = useState(false);

    const [isVerified, setIsVerified] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        if (resendTimer <= 0) return;

        const interval = setInterval(() => {
            setResendTimer((prev) => prev - 1);
        }, 1000);

        return () => clearInterval(interval);
    }, [resendTimer]);

    useEffect(() => {
        if (!isVerified) return;

        const interval = setInterval(() => {
            setSeconds((prev) => {
                if (prev <= 1) {
                    sessionStorage.removeItem("email");
                    clearInterval(interval);
                    navigate("/login", { replace: true });
                    return 0;
                }

                return prev - 1;
            });
        }, 1000);

        return () => clearInterval(interval);
    }, [isVerified, navigate]);

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
            showSuccess(response.data.message);
            setResendTimer(response.data.resendAvailableIn);
            setOtp(["", "", "", "", "", ""]);
            inputRefs.current?.[0].focus();
        }
        catch (error) {
            showError(
                error.response?.data?.message || "Failed to resend OTP"
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

    const email = sessionStorage.getItem("email");

    useEffect(() => {
        if (!email && !isVerified) {
            showError("Please create your account first!");
            navigate("/signup");
        }
    }, [email, isVerified, navigate])

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
            setIsVerified(true);
            setSeconds(3);

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

                        {!isVerified ? (
                            <>
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
                            </>
                        ) : (
                            <>
                                <div className="email-verified-cont">
                                    <div className="email-verified">
                                        <div className="email-verified-container">
                                            <div className="email-verified-icon-wrapper">
                                                <svg
                                                    className="email-verified-icon"
                                                    viewBox="0 0 52 52"
                                                >
                                                    <path
                                                        className="checkmark"
                                                        d="M14 27 L23 36 L39 17"
                                                    />
                                                </svg>
                                            </div>
                                            <h2>Email Verified!</h2>
                                            <p>Your Email has been successfully verified</p>
                                            <p>and your account is now active.</p>
                                        </div>

                                        <div className="account-active-container">
                                            <div className="lock-icon">
                                                <RiLockLine className="lock-icon" />
                                            </div>
                                            <div className="account-message">
                                                <h4>Account Activated Successfully</h4>
                                                <p>You will be redirected to login page</p>
                                                <p>in <span>{seconds}</span> seconds...</p>
                                            </div>
                                        </div>

                                        <div className="progress-bar">
                                            <div className="progress"></div>
                                        </div>
                                        <div className="thank-you-container">
                                            <div className="security-icon">
                                                <SecurityIcon size={18} />
                                            </div>
                                            <div className="thank-you-message">
                                                <p><span style={{ color: "rgb(87, 88, 90)" }}>Thank you for choosing </span> <span style={{ color: "#338C3E", fontWeight: 600 }}>STD Grocery Store</span> </p>
                                            </div>
                                        </div>
                                    </div>
                                    <p>Redirecting to login page...</p>
                                </div>
                            </>
                        )}

                    </div>
                </div>
            </div>
        </>
    )
}