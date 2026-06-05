import { useState } from "react";
import Login from "./components/Login";
import SignUp from "./components/Signup";


export default function App ()
{
  const [showSignup, setSignup] = useState(false);
  function handleShowSignup ()
    {
        setSignup(true);
    }

  function handleShowLogin ()
  {
    setSignup(false);
  }

  return (

   <>
      {showSignup ? (
        <SignUp  onLogin={handleShowLogin} />
      ) : (
        <Login onSignUp={handleShowSignup} />
      )
      }
    </>
  )
}