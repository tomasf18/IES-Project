import { SideBar, Header, ConfigurationCard } from "../../components";
import { FaUsers, FaCode, FaHeartPulse } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../../hooks";
import { createTeam } from "../../api";

export default function AdminAddTeam() {

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

  const auth = useAuth();
  const location = useLocation();

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
  }[] = [{ to: "/", label: "Sign Out", color: "primary" }];

  let configurationCardWidthClass = "w-[80rem]";
  let configurationCardHeightClass = "h-[50rem]";
  let configurationCardName = "Add Team";

  const navigate = useNavigate();

  const [teamName, setTeamName] = useState('');

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const response = await createTeam(auth.axiosInstance, teamName);
    if (response) {
        navigate("/admin/teams-managing");
    } else {
        console.error("Error creating new team");
    }
};

  // Updated configurationCardRightContent
  let configurationCardRightContent = (

      <form className="flex flex-col items-center justify-center w-1/2 mx-auto space-y-8"  onSubmit={handleSubmit}>

        {/* Team Name Label and Input */}
        <div className="flex flex-col w-full">
          <label className="text-base text-gray-700 mb-2" htmlFor="teamName">
            Team Name
          </label>
          <input
            id="teamName"
            type="text"
            placeholder="Text"
            className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
            value={teamName}
            onChange={(e) => setTeamName(e.target.value)}
            required
          />
        </div>
  
        {/* Create Team Button */}
        <button
          type="submit"
          className="px-16 py-3 bg-gray-300 text-black text-lg font-medium rounded-full shadow hover:bg-gray-400 transition duration-300"
        >
          Create Team
        </button>
        
      </form>

    );
  
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
        <div className="flex-grow flex flex-col p-8 justify-center items-center">
        <ConfigurationCard
            widthClass={configurationCardWidthClass}
            heightClass={configurationCardHeightClass}
            name={configurationCardName}
            rightContent={configurationCardRightContent}
          />
        </div>
      </div>
    </div>
  );
}
