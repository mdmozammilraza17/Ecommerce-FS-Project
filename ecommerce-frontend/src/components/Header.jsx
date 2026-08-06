import { useEffect, useRef, useState } from "react";
import { FaUserCircle } from "react-icons/fa";
import { useSelector } from "react-redux";
import StdImage from "../assets/grocery-store.png";
import ProfilePic1 from "../assets/profile-pic-1.jpg";
import CartButton from "./CartButton";
import LoginSignupButton from "./LoginSignupButton";
import ProfileDropdown from "./ProfileDropdown";
import SearchBar from "./SearchBar";
import { ClipLoader } from "react-spinners";

export default function Header() {

    const dropdownRef = useRef(null);

    const [showDropdown, setDropdown] = useState(false);

    const handleDropdown = () => {
        setDropdown(prev => !prev);
    };

    const {
        isAuthenticated, authLoading
    } = useSelector((state) => state.auth);


    useEffect(() => {
        function handleClickOutside(event) {
            if (dropdownRef.current &&
                !dropdownRef.current.contains(event.target)) {
                setDropdown(false);
            }
        }
        document.addEventListener(
            "mousedown",
            handleClickOutside
        );

        return () => {
            document.removeEventListener(
                "mousedown",
                handleClickOutside
            );
        };
    }, [])

    return (
        <>
            <header className="home-container">


                <div className="std-log-img">
                    <img
                        src={StdImage}
                        alt="Std General Store Image Logo"
                    />
                </div>

                <div className="home-actions">
                    <nav>
                        <ul>
                            <li>Home</li>
                            <li>Products</li>
                            <li>Categories</li>
                        </ul>
                    </nav>
                </div>

                <SearchBar />

                <CartButton />

                {
                    authLoading ? (
                        <div className="home-auth-loading">
                            <ClipLoader
                                size={20}
                                speedMultiplier={1}
                            />
                        </div>
                    ) : isAuthenticated ?
                        (
                            <div className="home-profile"
                                ref={dropdownRef}>


                                <button onClick={handleDropdown}>
                                    <FaUserCircle style={{ marginRight: "6px" }} />

                                    <img
                                        src={ProfilePic1}
                                        alt="Profile image"
                                    />
                                </button>
                                {showDropdown && <ProfileDropdown />}
                            </div>
                        ) : (<LoginSignupButton />)
                }
            </header>
        </>
    )
}