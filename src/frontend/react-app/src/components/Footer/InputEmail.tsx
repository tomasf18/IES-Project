import { TextInput } from "flowbite-react";
import { FiSend } from "react-icons/fi";

export default function Component() {
  return (
    <div className="max-w-md">
      <TextInput id="email4" type="email" rightIcon={FiSend} placeholder="Your email address" required />
    </div>
  );
}