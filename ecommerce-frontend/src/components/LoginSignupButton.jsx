import { FaUser } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

export default function LoginSignupButton ()
{

    const navigate = useNavigate();

    return (
        <>
            <div className="home-login-signup">

              <button onClick={() => navigate("/login")}>
                <FaUser style={{ marginRight: "6px" }} />
                Log In / Sign Up
              </button>

            </div>
        </>
    )
}