import { Navbar } from "flowbite-react";
import { Link } from "react-router-dom";
import { Button } from "../../components";
import customHeaderTheme from "./CustomHeaderTheme";

export default function Header() {
  return (
    <Navbar fluid rounded theme={customHeaderTheme}>
      <Navbar.Brand>
        <img src="/logo.png" className="mr-3 h-6 sm:h-10" alt="STS Logo" />
        <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
          Smart Training System
        </span>
      </Navbar.Brand>
      <div className="flex md:order-2 space-x-2">
        <Link to="/login">
          <Button color="secondary">
            Login
          </Button>
        </Link>
        <Link to="/signup">
          <Button color="primary">
            Sign Up
          </Button>
        </Link>

        <Navbar.Toggle />
      </div>
      <Navbar.Collapse>
        <Navbar.Link href="#home">Home</Navbar.Link>
        <Navbar.Link href="#service">Service</Navbar.Link>
        <Navbar.Link href="#feature">Feature</Navbar.Link>
        <Navbar.Link href="#product">Product</Navbar.Link>
        <Navbar.Link href="#security">Security</Navbar.Link>
      </Navbar.Collapse>
    </Navbar>
  );
}
