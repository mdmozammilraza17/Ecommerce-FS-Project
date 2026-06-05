import ImageBasket from "../assets/women-shopping.png";

export default function SignUp ({onLogin})
{
    return (
        <>
            <div className="login-container">
            
                            <div className="login-image">
                                <img src={ImageBasket} alt="basket image" />
                            </div>
            
            
                            <div className="login-main">
                                <h1>Create Account</h1>
            
                                <div className="form-group">
                                    <input type="text" placeholder="Enter Email ID" />
                                </div>

                                <div className="form-group">
                                    <input type="text" placeholder="Enter First Name" />
                                </div>

                                <div className="form-group">
                                    <input type="text" placeholder="Enter Last Name" />
                                </div>
            
                                <div className="form-group">
                                    <input type="password" placeholder="Enter password" />
                                </div>
                                <button>Create Account</button>
                                <p id="already-account">Already have an account?</p>
                                <button onClick={onLogin} id="login-button">Log In</button>
                                
                            </div>
                        </div>
        </>
    )
}