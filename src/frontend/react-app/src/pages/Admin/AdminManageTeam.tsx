import { SideBar, Header, ConfigurationCard, StripedTable, Button } from "../../components";
import { FaUsers, FaRegCopy, FaCode, FaHeartPulse, FaUserMinus, FaUserPlus } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { TextInput } from "flowbite-react";

export default function AdminManageTeam() {
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

  let configurationCardWidthClass = "w-[80rem]";
  let configurationCardHeightClass = "h-[50rem]";

  const searchParams = new URLSearchParams(location.search);
  
  // /admin/teams-managing/config?teamId=${teamId}&teamName=${teamName}
  const teamID = searchParams.get("teamId");
  const teamName = searchParams.get("teamName");
  let configurationCardName = teamName || "Default Team Name";

  let StripedTableWidthClass = "w-full";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["Team Director", "Option"];
  let StripedTableRows = [
    [
      "Danilo Silva",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "João Silva",
      <div className="flex justify-center items-center space-x-4">
        <FaRegCopy className="text-black-primary cursor-pointer text-2xl hover:text-black-darker hover:scale-125 transition-transform duration-200"/>
        <FaUserMinus className="text-red-primary cursor-pointer text-2xl hover:text-red-600 hover:scale-125 transition-transform duration-200" />
      </div>,
    ],
    [
      <div className="w-full flex justify-center items-center">
        <TextInput placeholder="Add Team Director" />
      </div>,
      <FaUserPlus className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
    ],

  ];

  const navigate = useNavigate();

  let configurationCardRightContent = (
    <div className="flex flex-col min-h-[45rem]">

      <div className="flex-grow max-h-[45rem] overflow-y-auto">
        <StripedTable
          widthClass={StripedTableWidthClass}
          heightClass={StripedTableHeightClass}
          columnsName={StripedTableColumnsName}
          rows={StripedTableRows}
        />
      </div>

      {/* Buttons at the bottom */}
      <div className="flex flex-col items-center space-y-4 mt-8">
        {/* Sensors Managing Button */}
        <button
          type="submit"
          className="px-16 py-3 bg-green-t3 text-black text-lg font-medium rounded-full shadow hover:bg-gray-400 transition duration-300"
          onClick={() => navigate(`/admin/teams-managing/sensors?teamId=${teamID}&teamName=${teamName}`)}
        >
          Sensors Managing
        </button>

        {/* Delete Team Button */}
        <button
          type="submit"
          className="px-16 py-3 bg-red-primary text-black text-lg font-medium rounded-full shadow hover:bg-gray-400 transition duration-300"
        >
          Delete Team
        </button>
      </div>
  
    </div>
    
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
