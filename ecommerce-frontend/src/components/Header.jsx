import { useSelector } from "react-redux";
import './Header.css';
import HeaderLogo from '../assets/STD-Grocery-Store.png';
import { FiMenu, FiChevronDown, FiSearch, FiUser, FiShoppingCart } from "react-icons/fi";
import { Link } from "react-router-dom";
import { ClipLoader } from "react-spinners";

export default function Header() {


    const {
        isAuthenticated, user, authLoading
    } = useSelector((state) => state.auth);

    const getGreeting = () => {
        const hour = new Date().getHours();

        if (hour >= 5 && hour < 12) {
            return "Good Morning!";
        } else if (hour >= 12 && hour < 17) {
            return "Good Afternoon!";
        } else if (hour >= 17 && hour < 21) {
            return "Good Evening!";
        } else {
            return "Good Night!";
        }
    };


    return (
        <>
            <header className="header-container">

                <div className="main-container">

                    <div className="header-logo-img">
                        <img src={HeaderLogo} alt="Header Logo" />
                    </div>


                    <div className="categories-menu">
                        <FiMenu />
                        <span>All Categories</span>
                        <FiChevronDown />
                    </div>

                    <div className="search-bar">
                        <input type="text" placeholder="Search for milk, apple, rice" />
                        <button className="search-button">
                            <FiSearch />
                        </button>
                    </div>

                    <div className="offer-bar">
                        <span className="percentage-icon">%</span>
                        <h3>Offers</h3>
                    </div>

                    {authLoading ? (
                        <ClipLoader size={25} />
                    ) : isAuthenticated ? (<div className="profile-container">
                        <div className="profile-pic">
                            👤
                        </div>
                        <div className="name">
                            <h4>Hi, {user?.firstName}</h4>
                            <p>{getGreeting()}</p>

                        </div>
                        <FiChevronDown />

                    </div>) : (<div className="login-register">
                        <FiUser />
                        <h4>
                            <Link to="/login">Login / Register </Link>
                        </h4>
                    </div>)}

                    <div className="cart-icon">
                        <div className="cart-symbol">
                            <FiShoppingCart />
                            <span className="cart-count">0</span>
                        </div>
                        <span>Cart</span>
                    </div>

                </div>

            </header>
        </>
    )
}