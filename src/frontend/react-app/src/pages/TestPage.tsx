import { SideBar } from "../components";
import { FaHome, FaUser, FaCog, FaBell } from "react-icons/fa";

export default function TestPage() {
  const navLinks = [
    { icon: <FaHome />, to: "/home", label: "Home" },
    { icon: <FaUser />, to: "/profile", label: "Sensors Tracking" },
    { icon: <FaCog />, to: "/settings", label: "Settings" },
    { icon: <FaBell />, to: "/notifications", label: "Notifications" },
  ];

  return (
    <div className="flex min-h-screen">
      <SideBar navLinks={navLinks} width="w-60" isLargeBar={true} />

      {/* Main Content */}
      <div className="flex-grow p-8 ml-60"></div>
    </div>
  );
}
