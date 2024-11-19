import { SideBar, Header, TeamsCard, ButtonWithIcon } from "../../components";
import { FaUsers, FaCode, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { useAuth } from "../../hooks";
import { Google } from "../../assets";
import { getTeamsInfo } from "../../api";
import { useEffect, useState } from "react";

export default function AdminTeamsManagingPage() {
  const auth = useAuth();
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

  const [teams, setTeams] = useState([]);

  const location = useLocation();

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
    onClick?: () => void;
  }[] = [{ to: "#", label: "Sign Out", color: "primary", onClick:() => auth.logOut() }];

  // Fetch display data
  useEffect(() => {
    getTeamsInfo(auth.axiosInstance)
      .then((response) => {
        const mappedTeams = response.map((team: any) => ({
          teamName: team.name,
          numberTeamMembers: team.numberOfMembers,
          teamId: team.teamId.toString(), // Convert to string if necessary
        }));
        setTeams(mappedTeams);
      })
      .catch((error) => {
        console.error("Error fetching team sensors:", error);
      });
  }
  , [auth.axiosInstance]);

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
          <ButtonWithIcon color="green"/>
          <div className="mt-14">
            <TeamsCard teams={teams} />
          </div>
        </div>
      </div>
    </div>
  );
}
