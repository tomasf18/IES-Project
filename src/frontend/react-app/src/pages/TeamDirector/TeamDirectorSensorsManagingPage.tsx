import {
  SideBar,
  Header,
  ConfigurationCard,
  StripedTable,
} from "../../components";
import { FaUsers, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";

export default function TeamDirectorSensorsManagingPage() {
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

  let StripedTableWidthClass = "w-full";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = [
    "SensorId",
    "State",
    "Player",
    "Option",
  ];
  let StripedTableRows = [
    ['9F2X4WQ8JH', "On", "Danilo Silva", "Remove"],
    ["9F2X4WQ8JH", "Off", "Pedro Pinto", "Remove"],
    ["9F2X4WQ8JH", "On", "João Pinto", "Remove"],
    ["9F2X4WQ8JH", "On", "Tomás Fernandes", "Remove"],
    ["9F2X4WQ8JH", "Off", "Guilherme Ferreira", "Remove"],
    ["9F2X4WQ8JH", "Off", "Jorge", "Remove"],
    ["9F2X4WQ8JH", "Off", "Daniel Silva", "Remove"],
  ];

  let configurationCardWidthClass = "w-[80rem]";
  let configurationCardHeightClass = "h-[50rem]";
  let configurationCardName = "António Mendes";
  let configurationCardRightContent = (
    <StripedTable
      widthClass={StripedTableWidthClass}
      heightClass={StripedTableHeightClass}
      columnsName={StripedTableColumnsName}
      rows={StripedTableRows}
    />
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
        <div className="flex-grow flex flex-col p-8 jsutify-center items-center">
          <div className="w-full max-w-[80rem]">
            <h2 className="text-2xl mb-6 text-left">Sensors Managing</h2>
          </div>
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
