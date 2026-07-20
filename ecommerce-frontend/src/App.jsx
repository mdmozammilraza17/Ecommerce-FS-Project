import { Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import Home from "./components/Home";
import SignUp from "./components/Signup";
import ProfileDropdown from "./components/ProfileDropdown";
import TopInfoBar from "./components/TopInfoBar";
import VerifyOtp from "./components/VerifyOtp";
import Login from "./components/Login";

export default function App() {
  return (
    <>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/" element={<ProfileDropdown />} />
        <Route path="/" element={<TopInfoBar />} />
        <Route path="/verify-otp" element={<VerifyOtp/>}/>
        <Route path="/login" element={<Login/>}/>
      </Routes>

      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop
        closeOnClick
        pauseOnHover
        draggable
        theme="colored"
      />
    </>
  );
}