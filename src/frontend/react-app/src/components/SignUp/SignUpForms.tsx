import { useState } from "react";
import { Button, Checkbox, Label, TextInput } from "flowbite-react";
import { Link } from "react-router-dom";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import CustomSignUpTheme from "../Login/CustomLoginButtonTheme";

export default function Component() {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <form className="flex w-full max-w-xl flex-col gap-4 mx-auto">
      <h1 className="text-3xl font-semibold text-center text-gray-600">
        Welcome to Smart Training System
      </h1>
      <hr className="flex-grow border-2 border-gray-300 my-8" />
      <div>
        <div className="mb-2 block">
          <Label htmlFor="email1" value="Email" />
        </div>
        <TextInput id="email1" type="email" required />
      </div>
      <div>
        <div className="mb-2 block">
          <Label htmlFor="email1" value="Username" />
        </div>
        <TextInput id="username" type="text" required />
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
        <ul className="mt-2 text-gray-500 text-sm list-disc list-inside pl-2 flex flex-wrap gap-4">
          <li>Use 8 or more characters</li>
          <li>One uppercase character</li>
          <li>One lowercase character</li>
          <li>One special character</li>
          <li>One number</li>
        </ul>
      </div>
      <div className="flex items-center gap-2 mt-4">
        <Checkbox id="remember" className="text-gray-800 focus:ring-gray-600" />
        <Label htmlFor="remember" className="text-gray-500">
          I want to receive emails and notifications about the product and
          feature updates.
        </Label>
      </div>
      <p className="text-gray-500 my-8">
        By creating an account, you agree to the{" "}
        <span className="font-bold underline text-gray-800 hover:text-gray-600 cursor-pointer">
          Terms of use
        </span>{" "}
        and{" "}
        <span className="font-bold underline text-gray-800 hover:text-gray-600 cursor-pointer">
          Privacy Policy
        </span>
      </p>
      <Button
        type="submit"
        className="w-1/2"
        theme={CustomSignUpTheme}
        color="custom"
      >
        Create an account
      </Button>
      <p className="text-gray-500">
        Already have an account?{" "}
        <Link
          to="/login"
          className="font-bold underline text-gray-800 hover:text-gray-600"
        >
          Log in
        </Link>
      </p>
    </form>
  );
}
