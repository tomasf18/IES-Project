import { SideBar, Header, TeamsCard } from "../../components";
import { FaUsers, FaCode, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { Google } from "../../assets";

export default function AdminTeamsManagingPage() {
  const navLinks = [
    {
      icon: <FaCode />,
      to: "/admin/endpoints",
      label: "Endpoints",
    },
    {
      icon: <FaHeartPulse />,
      to: "/admin/sensors-tracking",
      label: "Sensors Tracking",
    },
    {
      icon: <FaUsers />,
      to: "/admin/teams-managing",
      label: "Teams Managing",
    },
  ];

  const location = useLocation();

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
  }[] = [{ to: "/", label: "Sign Out", color: "primary" }];

  // Add teams array
  const teams = [
    {
      teamName: "Real Madrid",
      numberTeamMembers: 10,
      teamPhotoURL: Google,
      teamId: "team-1231",
    },
    {
      teamName: "Barcelona",
      numberTeamMembers: 12,
      teamPhotoURL: Google,
      teamId: "team-1232",
    },
    {
      teamName: "Atletico Madrid",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Porto",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Benfica",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Sporting",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Bayern Munich",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
  ];

  const handleTeamManagement = (teamId: string) => {
    console.log(`Managing team with id: ${teamId}`);
  };

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
        <div className="flex-grow p-8">
            <TeamsCard teams={teams} handleTeamManagement={handleTeamManagement} />
        </div>
      </div>
    </div>
  );
}
