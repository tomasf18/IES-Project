import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Checkbox, Label, TextInput } from "flowbite-react";
import { Button } from "../../components";
import { Google } from "../../assets";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { useAuth, useUser } from "../../hooks";

export default function Component() {
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");

  const [input, setInput] = useState({
    usernameOrEmail: "",
    password: "",
  });

  const auth = useAuth();
  const user = useUser();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setInput((prevInput) => ({
      ...prevInput,
      [name]: value,
    }));
  };

  const handleSubmitEvent = async (e: React.FormEvent<HTMLFormElement>) => {
    setError("");
    e.preventDefault();
    if (input.usernameOrEmail !== "" && input.password !== "") {
      const res = await auth.loginAction(input);
      if (res) {
        setError(res);
      }
      return;
    }
    setError("Please fill in all fields");
  };

  // check if the token is available, if so, redirect
  useEffect(() => {
    if (auth.token) {
      if (user.username === "") {
        auth.authMe();
      }
    }
  }, [auth.token]);

  useEffect(() => {
    if (user.username !== "") {
      user.redirectHomeByUserType();
    }
  }, [user.username]);

  return (
    <form
      className="flex w-full max-w-xl flex-col gap-4 mx-auto"
      onSubmit={handleSubmitEvent}
    >
      <h1 className="text-3xl font-semibold text-center text-gray-600 mb-4">
        Log In
      </h1>
      <div className="relative">
        <Button color="white" className="w-full cursor-not-allowed">
          <img
            src={Google}
            alt="Google"
            className="w-5 h-5 mr-3 transition duration-400 group-hover:invert"
          />
          Continue with Google
          <span className="absolute inset-x-0 bottom-[-2rem] text-center text-sm text-gray-500">
            (In the future...)
          </span>
        </Button>
      </div>

      <div className="flex items-center my-6">
        <hr className="flex-grow border-2 border-gray-300" />
        <span className="mx-4 text-gray-500">OR</span>
        <hr className="flex-grow border-2 border-gray-300" />
      </div>
      <div>
        <div className="mb-2 block">
          <Label htmlFor="usernameOrEmail" value="Email address or username" />
        </div>
        <TextInput
          id="usernameOrEmail"
          name="usernameOrEmail"
          type="text"
          value={input.usernameOrEmail}
          onChange={handleChange}
          required
        />
      </div>
      <div className="relative">
        <div className="mb-2 block">
          <Label htmlFor="password" value="Password" />
        </div>
        <TextInput
          id="password"
          name="password"
          type={showPassword ? "text" : "password"}
          value={input.password}
          onChange={handleChange}
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
      {error && (
        <div className="text-red-500 font-bold text-center">{error}</div>
      )}
      <button
        type="submit"
        className="bg-gray-600 w-full h-full text-white py-2 px-4 rounded-lg hover:bg-gray-500"
      >
        Log in
      </button>
      <hr className="my-6 border-2 border-gray-300" />
      <h1 className="text-xl font-semibold text-center text-gray-600 mb-4">
        Don't have an account?
      </h1>
      <Link to="/signup">
        <Button color="white" className="w-full">
          Sign Up
        </Button>
      </Link>
    </form>
  );
}
