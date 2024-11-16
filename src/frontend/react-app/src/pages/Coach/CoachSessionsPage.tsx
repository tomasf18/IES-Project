import { SideBar, StripedTable } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect } from "react";


export default function CoachSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-match" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  const user = useUser();
  const auth = useAuth();

  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [user, auth]);

  const avatarUrl = user.profilePictureUrl;
  const location = useLocation();

  let widthClass = "w-full px-20";
  let heightClass = "h-full";
  let columnsName = ["Session Name", "Day", "Time(min)", "Participants", "State", "Details"];
  let rows = [
      ["Apple MacBook Pro 17\"", "Sliver", "Laptop", "$2999"],
      ["Microsoft Surface Pro", "White", "Laptop PC", "$1999"],
      ["Magic Mouse 2", "Black", "Accessories", "$99"],
      ["Google Pixel Phone", "Gray", "Phone", "$799"],
      ["Google Pixel Phone", "Gray", "Phone", "$799"],
      ["Google Pixel Phone", "Gray", "Phone", "$799"],
      ["Google Pixel Phone", "Gray", "Phone", "$799"],
  ];

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
        <div className="flex flex-col p-4">
            <StripedTable 
                widthClass={widthClass} 
                heightClass={heightClass}
                columnsName={columnsName}
                rows={rows}
            />
        </div>
      </div>
    </div>
  );
}
