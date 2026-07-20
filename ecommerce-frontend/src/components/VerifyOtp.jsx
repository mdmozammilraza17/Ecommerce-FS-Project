import './VerifyOtp.css';
import AlmostThereImage from '../assets/Almost-There-Image.png';
import { FiMail } from "react-icons/fi";
import { FcGoogle } from "react-icons/fc";
import { RiShieldCheckFill } from "react-icons/ri";
import { useEffect, useRef, useState } from 'react';
import api from "../api/api";
import { showError, showSuccess } from '../utils/toastUtil';
import { useNavigate } from 'react-router-dom';

export default function VerifyOtp() {
    const inputArr = [0, 1, 2, 3, 4, 5];

    const inputRefs = useRef([]);

    const [otp, setOtp] = useState(["", "", "", "", "", ""]);

    const [loading, setLoading] = useState(false);

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
            if (otp[index] != "") {
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

        if (otpValue.length != 6) {
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
                                <span className="resend-link">Resend OTP</span>in
                                <span className='time-sec'>00:48</span>
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
                    </div>
                </div>
            </div>
        </>
    )
}