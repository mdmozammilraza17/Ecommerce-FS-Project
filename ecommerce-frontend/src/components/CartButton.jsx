import { FaShoppingCart } from "react-icons/fa";

export default function CartButton() {
    return (
        <>
            <div className="cart-icon">
                <button>
                    <FaShoppingCart style={{ marginRight: "6px" }} />
                    Cart (0)
                </button>
            </div>
        </>
    )
}