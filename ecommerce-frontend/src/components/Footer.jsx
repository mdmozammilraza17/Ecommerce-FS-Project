import { FaFacebookF, FaInstagram, FaTwitter } from "react-icons/fa";

export default function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">

        <div className="footer-section">
          <h2 className="store-name">STD Grocery Store</h2>
          <p className="store-desc">
            Your trusted grocery and daily needs store serving customers with
            quality products and affordable prices.
          </p>

          <div className="social-icons">
            <a href="#">
              <FaFacebookF />
            </a>

            <a href="#">
              <FaInstagram />
            </a>

            <a href="#">
              <FaTwitter />
            </a>
          </div>
        </div>

        <div className="footer-section">
          <h3>Contact</h3>
          <p>📞 +91 6205914390</p>
          <p>📍 Pahari Muhalla, Near Gamhel Asthan</p>
          <p>Daltonganj, Jharkhand - 822101</p>
        </div>

        <div className="footer-section">
          <h3>Company</h3>
          <p>About Us</p>
          <p>Privacy Policy</p>
          <p>Terms & Conditions</p>
        </div>

      </div>

      <div className="footer-bottom">
        © 2026 STD Grocery Store. All Rights Reserved.
      </div>
    </footer>
  );
}