import Footer from "./Footer";
import "./Home.css";
import "./Footer.css";

import StdImage from "../assets/grocery-store.png";
import ProfilePic1 from "../assets/profile-pic-1.jpg";

import { FaShoppingCart, FaUserCircle, FaUser } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

export default function Home() {

  const navigate = useNavigate();

  const { isLoggedIn } = useSelector(
    (state) => state.auth
  );

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

        <div className="home-search">
          <input
            type="text"
            placeholder="Search products..."
          />
        </div>

        <div className="cart-icon">
          <button>
                <FaShoppingCart style={{ marginRight: "6px" }} />
                Cart (0)
              </button>
        </div>

        {
          !isLoggedIn ? (
            <div className="home-login-signup">


              <button onClick={() => navigate("/login")}>
                <FaUser style={{ marginRight: "6px" }} />
                Log In / Sign Up
              </button>

            </div>
          ) : (
            <div className="home-profile">

              
              <button>
                <FaUserCircle style={{ marginRight: "6px" }} />

                <img
                  src={ProfilePic1}
                  alt="Profile image"
                />
              </button>
            </div>
          )
        }

      </header>

      <Footer />
    </>
  );
}