import { FaFacebookF, FaInstagram, FaTwitter, FaWhatsapp } from "react-icons/fa";
import STdLogo1 from "../assets/STD-Logo-1.png";
import GooglePlayImg from "../assets/Google-Play.png";
import AppStoreImg from "../assets/App-Store.png";
import VisaLogo from "../assets/VISA.png";
import MASTERCARDLogo from "../assets/MASTERCARD.png";
import PaytmLogo from "../assets/Paytm.png";
import RupayLogo from "../assets/Rupay.png";
import UpiLogo from "../assets/UPI.png";
export default function Footer() {
  return (
    <footer className="footer">

      <div className="footer-container">

      <div className="footer-section company-info">
        <img src={STdLogo1} alt="STD Logo 1 image" />
        <p>Your one-stop online grocery store for all your daily needs. Quality products, best prices, on time delivery.</p>
        <div className="social-links">
          <a href="#" aria-label="Facebook"><FaFacebookF /></a>
          <a href="#" aria-label="Instagram"><FaInstagram /></a>
          <a href="#" aria-level="whatsApp"><FaWhatsapp /></a>
          <a href="#" aria-label="twitter"><FaTwitter/></a>
        </div>
        </div>      

        <div className="footer-section quick-links">

          <h4>QUICK LINKS</h4>
          <ul>
            <li>Home</li>
          <li>Products</li>
          <li>Categories</li>
          <li>Offers</li>
          <li>About Us</li>
          <li>Contact Us</li>
          <li>Store Locator</li>
          </ul>
        </div>


        <div className="footer-section customer-service">
          <h4>CUSTOMER SERVICE</h4>
          <ul>
            <li>My Account</li>
          <li>Track Order</li>
          <li>Wishlist</li>
          <li>FAQs</li>
          <li>Help & Support</li>
          <li>Return & Refund</li>
          <li>Privacy Policy</li>
          </ul>
        </div>


        <div className="footer-section categories">
          <h4>CATEGORIES</h4>
          <ul>
          <li>Staples</li>
          <li>Beverages</li>
          <li>Snacks</li>
          <li>Personal Care</li>
          <li>Home Care</li>
          <li>Baby Care</li>
          <li>View All</li>
          </ul>
        </div>

        <div className="footer-section download-app">
          <h4>DOWNLOAD APP</h4>
          <p>Get 5% OFF on your first order</p>
          <div className="app-buttons">
          <img src={GooglePlayImg} alt="GET IT ON Google Play Image" />
          <img src={AppStoreImg} alt="Download on the App Store Image" />
          </div>
        </div>


        <div className="footer-section newsletter">
          <h4>NEWSLETTER</h4>
          <p>Subscribe to get updates on offers and new products</p>
          <input type="email" placeholder="Enter your email"/>
          <button>Subscribe</button>

        </div>

        </div>
        <div className="bottom-footer">
          <p>© 2026 STD General Store, Daltonganj. All Rights Reserved.</p>
          <div className="cards-payment">
            <img src={VisaLogo} alt="VISA logo image" />
            <img src={MASTERCARDLogo} alt="Master Logo Image" />
            <img src={RupayLogo} alt="RupayLogoImage" />
            <img src={UpiLogo} alt="UPI Logo Image" />
            <img src={PaytmLogo} alt="Paytm Logo Image" />
          </div>
        </div>
    </footer>
  );
}