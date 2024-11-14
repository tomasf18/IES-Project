import { SideBar } from "../../components";
import { FaChartBar, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";


export default function PersonalTrainerStartSessionage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/personal-trainer/start-session" },
    { icon: <FaHeartPulse />, to: "/personal-trainer/sensors" },
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
