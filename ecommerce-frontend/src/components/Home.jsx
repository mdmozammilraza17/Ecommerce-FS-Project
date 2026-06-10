import Footer from "./Footer";
import "./Home.css";
import "./Footer.css";
import StdImage from '../assets/grocery-store.png';
import { FaShoppingCart } from "react-icons/fa";
import { FaUser } from "react-icons/fa";
export default function Home() {
  return (
    <>
      <header className="home-container">
        <div className="std-log-img">
          <img src={StdImage} alt="Std General Store Image Logo" />
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
          <input type="text" placeholder="Search products..." />
        </div>

        <div className="home-login-signup">
          <button>
            <FaShoppingCart style={{ marginRight: "6px" }} />Cart (0)</button>
          <button>
            <FaUser style={{ marginRight: "6px" }} />Log In / Sign Up</button>
        </div>
      </header>
      <Footer/>
    </>
  );
}