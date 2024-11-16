import {
  SideBar,
  Header,
  ConfigurationCard,
  StripedTable,
} from "../../components";
import {
  FaUsers,
  FaHeartPulse,
  FaUserPlus,
  FaUserMinus,
  FaCircle,
} from "react-icons/fa6";
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
  let StripedTableColumnsName = ["SensorId", "State", "Player", "Option"];
  let StripedTableRows = [
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-green-primary mr-2" />
        On
      </>,
      "Danilo Silva",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-red-primary mr-2" />
        Off
      </>,
      "--------------",
      <FaUserPlus className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-green-primary mr-2" />
        On
      </>,
      "João Pinto",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-green-primary mr-2" />
        On
      </>,
      "Tomás Fernandes",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-red-primary mr-2" />
        Off
      </>,
      "--------------",
      <FaUserPlus className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-red-primary mr-2" />
        Off
      </>,
      "Jorge",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "9F2X4WQ8JH",
      <>
        <FaCircle className="inline-block text-sm text-red-primary mr-2" />
        Off
      </>,
      "Daniel Silva",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
  ];

  let configurationCardWidthClass = "w-[80rem]";
  let configurationCardHeightClass = "h-[50rem]";
  let configurationCardName = "António Mendes";
  let configurationCardRightContent = (
    <div className="max-h-[45rem] overflow-y-auto">
      <StripedTable
        widthClass={StripedTableWidthClass}
        heightClass={StripedTableHeightClass}
        columnsName={StripedTableColumnsName}
        rows={StripedTableRows}
      />
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
        <div className="flex-grow flex flex-col p-8 jsutify-center items-center">
          <div className="w-full max-w-[80rem]">
            <h2 className="text-3xl mb-6 text-left">Sensors Managing</h2>
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
