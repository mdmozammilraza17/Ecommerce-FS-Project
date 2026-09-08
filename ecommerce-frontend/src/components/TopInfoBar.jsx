
import { CiDeliveryTruck, CiHeart } from "react-icons/ci";
import { FaLeaf } from "react-icons/fa";
import { IoShieldCheckmarkOutline } from "react-icons/io5";
import { SlEarphonesAlt } from "react-icons/sl";
import "./TopInfoBar.css";


export default function TopInfoBar() {
  return (
    <div className="top-info-bar">

      {/* Fresh & Quality Products */}
      <div className="info-item">
        <div className="icon-leaf">
          <FaLeaf/>
        </div>
        <div>
          <h4>
            Fresh & Quality Products
          </h4>
        </div>
      </div>

      <div className="divider"></div>

      {/* Safe and Secure Payments */}
      <div className="info-item">
        <div className="icon-shield-check">
          <IoShieldCheckmarkOutline />
        </div>

        <div>
          <h4>Safe & Secure Payments</h4>
        </div>
      </div>

      <div className="divider"></div>

      {/* Fast & Reliable Delivery */}
      <div className="info-item">
        <div className="icon-delivery">
          <CiDeliveryTruck />
        </div>

        <div>
          <h4>Fast & Reliable Delivery</h4>
        </div>
      </div>

      <div className="divider"></div>

      {/* 24/7 Customer Support */}
      <div className="info-item">
        <div className="icon-earphone">
          <SlEarphonesAlt/>
        </div>

        <div>
          <h4>24/7 Customer Support</h4>
        </div>
      </div>

      <div className="divider"></div>

      {/* Trusted by Thousands*/}
      <div className="info-item">
        <div className="icon-heart">
          <CiHeart />
        </div>

        <div>
          <h4>Trusted by Thousands</h4>
        </div>
      </div>

    </div>
  );
}