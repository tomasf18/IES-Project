import {
  SideBar,
  Header,
  ConfigurationCard,
  StripedTable,
  Button,
} from "../../components";
import { TextInput, Select } from "flowbite-react";
import {
  FaUsers,
  FaHeartPulse,
  FaPen,
  FaUserPlus,
  FaUserMinus,
} from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { useAuth } from "../../hooks";

export default function TeamDirectorTeamManagingPage() {
  const auth = useAuth();
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
    onClick?: () => void;
  }[] = [{ to: "#", label: "Sign Out", color: "primary", onClick:() => auth.logOut() }];

  let StripedTableWidthClass = "w-full";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["Team Members", "", "Role", "Options "];
  let StripedTableRows = [
    [
      "Danilo Silva",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Maria Oliveira",
      <Button color="primary" className="mx-auto">
        Add Photo
      </Button>,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "José Lima",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Coach",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Danilo Silva",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Maria Oliveira",
      <Button color="primary" className="mx-auto">
        Add Photo
      </Button>,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "José Lima",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Coach",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Danilo Silva",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Maria Oliveira",
      <Button color="primary" className="mx-auto">
        Add Photo
      </Button>,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "José Lima",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Coach",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Danilo Silva",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Maria Oliveira",
      <Button color="primary" className="mx-auto">
        Add Photo
      </Button>,
      "Player",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "José Lima",
      <FaPen className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
      "Coach",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      "Ana Martins",
      <Button color="primary" className="mx-auto">
        Add Photo
      </Button>,
      "Personal Trainer",
      <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />,
    ],
    [
      <TextInput placeholder="Add Team Member" size={4} />,
      <Button color="primary" className="mx-auto">
        Add Photo
      </Button>,
      <Select id="role" required className="w-40 mx-auto">
        <option>Coach</option>
        <option>Personal Trainer</option>
        <option>Player</option>
      </Select>,
      <FaUserPlus className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />,
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
            <h2 className="text-3xl mb-6 text-left">Team Managing</h2>
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
