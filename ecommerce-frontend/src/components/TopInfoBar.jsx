import {
  FaMapMarkerAlt,
  FaChevronDown,
  FaTruck,
  FaTags,
  FaShippingFast,
  FaMobileAlt,
  FaFacebookF,
  FaInstagram,
  FaWhatsapp,
} from "react-icons/fa";

import "./TopInfoBar.css";

export default function TopInfoBar() {
  return (
    <div className="top-info-bar">

      {/* Location */}
      <div className="info-item">
        <div className="icon-circle">
          <FaMapMarkerAlt />
        </div>

        <div>
          <p className="small-text">Delivery to</p>
          <h4>
            Daltonganj, Jharkhand 822101 <FaChevronDown />
          </h4>
        </div>
      </div>

      <div className="divider"></div>

      {/* Free Delivery */}
      <div className="info-item">
        <div className="icon-circle">
          <FaTruck />
        </div>

        <div>
          <h4>Free Delivery</h4>
          <p>On orders above ₹499</p>
        </div>
      </div>

      <div className="divider"></div>

      {/* Best Prices */}
      <div className="info-item">
        <div className="icon-circle">
          <FaTags />
        </div>

        <div>
          <h4>Best Prices</h4>
          <p>You won't find cheaper</p>
        </div>
      </div>

      <div className="divider"></div>

      {/* On Time Delivery */}
      <div className="info-item">
        <div className="icon-circle">
          <FaShippingFast />
        </div>

        <div>
          <h4>On Time Delivery</h4>
          <p>Lightning fast delivery</p>
        </div>
      </div>

      <div className="divider"></div>

      {/* Download App */}
      <div className="info-item">
        <div className="icon-circle">
          <FaMobileAlt />
        </div>

        <div>
          <h4>Download App</h4>
          <p>Get Extra 5% OFF</p>
        </div>
      </div>

      <div className="divider"></div>

      {/* Social Icons */}
      <div className="social-icons">
        <FaFacebookF />
        <FaInstagram />
        <FaWhatsapp />
      </div>

    </div>
  );
}