import '../components/TopHeader.css';
import { CiLocationOn } from "react-icons/ci";
import { IoIosArrowDown } from "react-icons/io";
import { LiaRupeeSignSolid } from "react-icons/lia";
import { BsShieldCheck } from "react-icons/bs";
import { MdInstallMobile } from "react-icons/md";
import { IoIosHelpCircleOutline } from "react-icons/io";
import { GiDigitalTrace } from "react-icons/gi";


export default function TopHeader() {
    return (
        <>
            <div className="top-header">
                <div className="top-header-container">
                    <div className="location-container">
                        <CiLocationOn />
                        <p>Delivery to: 822101 - Daltonganj</p>
                        <IoIosArrowDown />
                    </div>
                    <div className="free-delivery-container">
                        <BsShieldCheck />
                        <p>Free delivery on orders above <span className='price'><LiaRupeeSignSolid />499</span></p>
                    </div>
                    <div className="header-utility-links">

                        <div className="download-app-icon">
                            <MdInstallMobile />
                            <p>Download App</p>
                        </div>

                        <div className="track-order">
                            <GiDigitalTrace />
                            <p>Track Order</p>
                        </div>

                        <div className="help-support">
                            <IoIosHelpCircleOutline />
                            <p>Help & Support</p>
                        </div>

                    </div>
                </div>
            </div>
        </>
    )
}