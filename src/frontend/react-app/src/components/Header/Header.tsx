import { Navbar } from "flowbite-react";
import { Link } from "react-router-dom";
import { Button } from "../../components";
import customHeaderTheme from "./CustomHeaderTheme";

interface HeaderProps {
  links?: { href: string; label: string }[];
  buttons?: { to: string; label: string; color: "primary" | "secondary" }[];
}

export default function Header({ links = [], buttons = [] }: HeaderProps) {
  return (
    <Navbar fluid rounded theme={customHeaderTheme}>
      <Navbar.Brand>
        <img src="/logo.png" className="mr-3 h-6 sm:h-10" alt="STS Logo" />
        <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
          Smart Training System
        </span>
      </Navbar.Brand>

      {/* Buttons */}
      <div className="flex md:order-2 space-x-2">
        {buttons.map((button, index) => (
          <Link to={button.to} key={index}>
            <Button color={button.color}>{button.label}</Button>
          </Link>
        ))}
        <Navbar.Toggle />
      </div>

      {/* Links */}
      <Navbar.Collapse>
        {links.map((link, index) => (
          <Navbar.Link href={link.href} key={index}>
            {link.label}
          </Navbar.Link>
        ))}
      </Navbar.Collapse>
    </Navbar>
  );
}
