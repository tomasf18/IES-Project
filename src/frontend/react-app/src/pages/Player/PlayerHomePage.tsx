import { SideBar } from "../../components";
import { FaHome } from "react-icons/fa";
import { useLocation } from "react-router-dom";

export default function PlayerHomePage() {
  const navLinks = [
    { icon: <FaHome />, to: "/player/home" },
  ];

  const avatarUrl = "https://via.placeholder.com/80";
  const location = useLocation();

  return (
    <div className="flex min-h-screen">
      <SideBar avatarUrl={avatarUrl} navLinks={navLinks} activePath={location.pathname} />

      {/* Main Content */}
      <div className="flex-grow p-8">
        {/* Logo */}
        <img
          src="/logo.png"
          alt="Logo"
          className="absolute top-8 right-8 h-24"
        />
        {/* Content */}
      </div>
    </div>
  );
}
