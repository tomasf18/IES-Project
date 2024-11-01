import { Link } from "react-router-dom";
import { LoginSignUpImage } from "../assets";
import { LoginForms } from "../components";

export default function LoginPage() {
  return (
    <div className="flex h-screen">
      {/* Left side with image */}
      <div className="flex-none h-full w-auto hidden xl:block">
        <img src={LoginSignUpImage} alt="Login" className="h-full object-cover" />
      </div>
      {/* Right side with form */}
      <div className="flex-grow flex flex-col items-start justify-center px-8 bg-gray-100">
        {/* Logo and name aligned to the left */}
        <Link to="/" className="absolute top-8 flex items-center">
          <img src="./logo.png" alt="Logo" className="h-20 w-auto mr-4" />
          <h1 className="text-3xl font-semibold text-gray-900">
            Smart Training System
          </h1>
        </Link>

        {/* Login form, centered horizontally */}
        <div className="w-full flex justify-center">
          <LoginForms />
        </div>
      </div>
    </div>
  );
}
