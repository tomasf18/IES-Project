import { SideBar, Header, ConfigurationCard, StripedTable } from "../../components";
import { FaUsers, FaCode, FaHeartPulse, FaUserMinus, FaUserPlus } from "react-icons/fa6";
import { MdOutlineSensors } from "react-icons/md";
import { useLocation, useNavigate } from "react-router-dom";
import { TextInput } from "flowbite-react";


export default function AdminSensorsTrackingPage() {
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

  // /admin/teams-managing/sensors?teamId=${teamID}&teamName=${teamName}
  const searchParams = new URLSearchParams(location.search);
  const teamID = searchParams.get("teamId");
  const teamName = searchParams.get("teamName");
  let configurationCardName = teamName || "Default Team Name";

  let StripedTableWidthClass = "w-full";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["SensorId", "Player", "Option"];

  let StripedTableRows = [
    [
      "9F2X4WQ8JH",
      "Danilo Silva",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "C8Y1Z7NDQK",
      "João Silva",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,

    ],
    [
      <div className="w-full flex justify-center items-center">
        <TextInput placeholder="Add new SensorId" />
      </div>,
      <MdOutlineSensors className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
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
          activePath={"/admin/teams-managing"}
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