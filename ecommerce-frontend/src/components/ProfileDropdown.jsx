import { CgProfile } from "react-icons/cg";
import { SlArrowRight } from "react-icons/sl";
import { FaRegUser } from "react-icons/fa";
import { RiShoppingBasketLine } from "react-icons/ri";
import { CiLocationOn } from "react-icons/ci";
import { PiWallet } from "react-icons/pi";
import { CiViewList } from "react-icons/ci";
import { VscReferences } from "react-icons/vsc";
import { CiBellOn } from "react-icons/ci";
import { IoIosLogOut } from "react-icons/io";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { TfiHelpAlt } from "react-icons/tfi";
import { IoWalletSharp } from "react-icons/io5";


import "./ProfileDropdown.css";
export default function ProfileDropdown() {

    const {
        isAuthenticated
    } = useSelector((state) => state.auth);

    return (
        <>
            <div className="profile-dropdown-container">
                <ul>

                    {!isAuthenticated && (
                        <>
                            <li>
                                <div className="login">
                                    <CgProfile className="login-icon" />
                                    <Link to="/login" className="login-link">
                                        <h5>Login</h5>
                                        <p>Access your account</p>
                                    </Link>
                                    <SlArrowRight className="login-arrow" />
                                </div>
                            </li>

                        </>
                    )}

                    {isAuthenticated && (
                        <>
                            <li>
                                <div className="profile">
                                    <FaRegUser className="profile-icon" />
                                    <h5>My Profile</h5>
                                </div>
                            </li>
                        </>
                    )}

                    {
                        <li>
                            <div className="orders">
                                <RiShoppingBasketLine className="orders-icon" />
                                <h5>My Orders</h5>
                            </div>
                        </li>
                    }

                    {
                        (isAuthenticated && (
                            <>
                                <li>
                                    <div className="addresses">
                                        <CiLocationOn className="location-icon" />
                                        <h5>My Addresses</h5>
                                    </div>
                                </li>
                            </>
                        ) &&
                            (
                                <>
                                    <li>
                                        <div className="wallets">
                                            <IoWalletSharp className="wallet-icon" />
                                            <h5>My Wallet</h5>
                                        </div>
                                    </li>
                                </>
                            ))
                    }

                    {
                        <li>
                            <div className="list">
                                <CiViewList className="list-icon" />
                                <h5>My List</h5>
                            </div>
                        </li>
                    }

                    {
                        (isAuthenticated && (
                            <>
                                <li>
                                    <div className="refer-earn">
                                        <VscReferences className="refer-icon" />
                                        <h5>Refer & Earn</h5>
                                    </div>
                                </li>
                            </>
                        ))
                    }


                    {
                        <li>
                            <div className="notifications">
                                <CiBellOn className="bell-icon" />
                                <h5>Notifications</h5>
                                <div className="icon-msg">
                                    <p>5</p>
                                </div>
                            </div>
                        </li>
                    }

                    {
                        <li>
                            <div className="help-support">
                                <TfiHelpAlt className="help-icon" />
                                <h5>Help & Support</h5>
                            </div>
                        </li>
                    }

                    {
                        (isAuthenticated && (
                            <>
                                <li>
                                    <div className="logout-div">
                                        <IoIosLogOut className="logout-icon" />
                                        <h5>Logout</h5>
                                    </div>
                                </li>
                            </>
                        ))
                    }

                    {!isAuthenticated && (
                        <>
                            <li>
                                <div className="register">
                                    <IoIosLogOut className="register-icon" />
                                    <Link to="/signup" className="register-link">
                                        <h5>Register</h5>
                                        <p>Create a new account</p>
                                    </Link>
                                </div>
                            </li>
                        </>
                    )}

                </ul>

            </div>
        </>
    )
}