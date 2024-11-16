import { useState } from "react";
import { Checkbox, Label, TextInput } from "flowbite-react";
import { Link } from "react-router-dom";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { useAuth } from "../../hooks/AuthProvider";
import { SimpleModal } from "../../components";

export default function Component() {
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const [openModal, setOpenModal] = useState(false);

  const [input, setInput] = useState({
    email: "",
    username: "",
    password: "",
    code: "",
  });

  const auth = useAuth();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setInput((prevInput) => ({
      ...prevInput,
      [name]: value,
    }));
  };

  const handleSubmitEvent = async () => {
    setError("");
    if (input.email !== "" && input.username !== "" && input.password !== "" && input.code !== "") {
      const res = await auth.signUpAction(input);
      if (res) {
        setError(res);
      }
      return;
    }
    setError("Please fill in all fields");
  };

  const handleConfirm = () => {
    setOpenModal(false);
    handleSubmitEvent();
  };

  const openRegistrationCodeModal = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setOpenModal(true);
  };

  return (
    <>
      <form className="flex w-full max-w-xl flex-col gap-4 mx-auto" onSubmit={e => openRegistrationCodeModal(e)}>
        <h1 className="text-3xl font-semibold text-center text-gray-600">
          Welcome to Smart Training System
        </h1>
        <hr className="flex-grow border-2 border-gray-300 my-8" />
        <div>
          <div className="mb-2 block">
            <Label htmlFor="email" value="Email" />
          </div>
          <TextInput
            id="email"
            name="email"
            type="email"
            value={input.email}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <div className="mb-2 block">
            <Label htmlFor="username" value="Username" />
          </div>
          <TextInput
            id="username"
            name="username"
            type="text"
            value={input.username}
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
        {error && <div className="text-red-500 font-bold text-center">{error}</div>}
        <button
          type="submit"
          className="bg-gray-600 w-full h-full text-white py-2 px-4 rounded-lg hover:bg-gray-500"
        >
          Create an account
        </button>
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
      <SimpleModal
        show={openModal}
        onClose={() => setOpenModal(false)}
        content={
          <>
            <h2 className="text-center font-bold text-lg">Join the Smart Training System</h2>
            <h5 className="text-center">Insert your access code.</h5>
            <TextInput
              id="code"
              name="code"
              type="text"
              value={input.code}
              placeholder="Access code"
              onChange={(e) => handleChange(e)}
              required
            />
          </>
        }
        buttonText="Yes, I'm sure"
        buttonClass="bg-gray-600"
        onConfirm={handleConfirm}
      />
    </>
  );
}