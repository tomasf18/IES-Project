import { SideBar, Header } from "../../components";
import { FaUsers, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";

export default function TeamDirectorTeamManagingPage() {
  const navLinks = [
    {
      icon: <FaUsers />,
      to: "/team-director/team-managing",
      label: "Team Managing",
    },
    {
      icon: <FaHeartPulse />,
      to: "/team-director/sensors-managing",
      label: "Sensors Managing",
    },
  ];

  const location = useLocation();

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
  }[] = [{ to: "/", label: "Sign Out", color: "primary" }];

  return (
    <div className="flex flex-col min-h-screen">
      <Header buttons={headerButtons} />
      <div className="flex flex-grow">
        <SideBar
          navLinks={navLinks}
          width="w-62"
          isLargeBar={true}
          activePath={location.pathname}
        />
        {/* Main Content */}
        <div className="flex-grow p-8">{/* Content */}</div>
      </div>
    </div>
  );
}
