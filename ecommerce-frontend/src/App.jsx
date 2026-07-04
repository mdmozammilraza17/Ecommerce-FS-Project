import { Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import SignUp from "./components/Signup";
import ProfileDropdown from "./components/ProfileDropdown";
import TopInfoBar from "./components/TopInfoBar";

export default function App() {
  return (
    <Routes>
      <Route path="/home" element={<Home />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/" element={<ProfileDropdown/>}/>
      <Route path="/" element={<TopInfoBar/>}/>
    </Routes>
  );
}