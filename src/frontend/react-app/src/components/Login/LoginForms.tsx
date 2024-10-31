import { useState } from "react";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { Button, Checkbox, Label, TextInput } from "flowbite-react";
import { Link } from "react-router-dom";
import GoogleIcon from "../../assets/google_icon.svg";
import CustomLoginButtonTheme from "./CustomLoginButtonTheme";
import CustomSignUpGoogleTheme from "./CustomSignUpButtonTheme";

export default function Component() {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <form className="flex w-full max-w-xl flex-col gap-4 mx-auto">
      <h1 className="text-3xl font-semibold text-center text-gray-600 mb-4">
        Log In
      </h1>
      <Button theme={CustomSignUpGoogleTheme} color="custom" className="group">
        <img
          src={GoogleIcon}
          alt="Google"
          className="w-5 h-5 mr-3 transition duration-400 group-hover:invert"
        />
        Continue with Google
      </Button>
      <div className="flex items-center my-6">
        <hr className="flex-grow border-2 border-gray-300" />
        <span className="mx-4 text-gray-500">OR</span>
        <hr className="flex-grow border-2 border-gray-300" />
      </div>
      <div>
        <div className="mb-2 block">
          <Label htmlFor="email1" value="Email address or username" />
        </div>
        <TextInput id="email1" type="email" required />
      </div>
      <div className="relative">
        <div className="mb-2 block">
          <Label htmlFor="password1" value="Password" />
        </div>
        <TextInput
          id="password1"
          type={showPassword ? "text" : "password"}
          required
        />
        <button
          type="button"
          onClick={() => setShowPassword(!showPassword)}
          className="absolute right-3 top-11 text-gray-700 hover:text-gray-900"
        >
          {showPassword ? <AiOutlineEyeInvisible /> : <AiOutlineEye />}
        </button>
      </div>
      <div className="flex items-center gap-2">
        <Checkbox id="remember" className="text-gray-800 focus:ring-gray-600" />
        <Label htmlFor="remember">Remember me</Label>
      </div>
      <Button theme={CustomLoginButtonTheme} color="custom" type="submit">
        Login
      </Button>
      <hr className="my-6 border-2 border-gray-300" />
      <h1 className="text-xl font-semibold text-center text-gray-600 mb-4">
        Don't have an account?
      </h1>
      <Link to="/signup">
        <Button
          theme={CustomSignUpGoogleTheme}
          color="custom"
          className="w-full"
        >
          Sign Up
        </Button>
      </Link>
    </form>
  );
}
