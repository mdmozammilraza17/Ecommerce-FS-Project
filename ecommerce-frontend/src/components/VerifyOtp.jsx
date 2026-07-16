import './VerifyOtp.css';
import AlmostThereImage from '../assets/Almost-There-Image.png';
import { FiMail } from "react-icons/fi";
import { FcGoogle } from "react-icons/fc";
import { RiShieldCheckFill } from "react-icons/ri";
export default function VerifyOtp() {
    return (
        <>
            <div className="otp-verification-page">
                <div className="otp-verification-container">
                    <div className="otp-verification-image">
                        <img src={AlmostThereImage} alt="Almost there image in the left side" />
                    </div>

                    <div className="otp-right-section">
                        <div className="email-icon">
                            <FiMail className="verification-icon" />
                        </div>
                        <div className="verify-email-content">
                            <h3>Verify Your Email</h3>
                            <p>We have sent a 6-digit OTP to</p>
                            <p className="email-address"><b>mdmozammilraza06@gmail.com</b></p>
                        </div>
                        <div className="otp-boxes">
                            <input type="text" maxLength="1" />
                            <input type="text" maxLength="1" />
                            <input type="text" maxLength="1" />
                            <input type="text" maxLength="1" />
                            <input type="text" maxLength="1" />
                            <input type="text" maxLength="1" />
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
                            <button>Verify and Active Account</button>
                        </div>
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