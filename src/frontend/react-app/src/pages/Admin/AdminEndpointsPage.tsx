import { SideBar, Header, StripedTable, Navbar } from "../../components";
import { FaUsers, FaCode, FaHeartPulse } from "react-icons/fa6";
import { FaChartBar } from "react-icons/fa";
import { useLocation } from "react-router-dom";

export default function AdminEndpointsPage() {
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

  let StripedTableWidthClass = "w-3/4";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["Name", "State", "Accesses"];
  let StripedTableRows = [
    [
      "/admin/endpoints",
      "Critical",
      "10"
    ],
    [
      "/admin/endpoints",
      "Flooded",
      "7"
    ],
    [
      "/admin/endpoints",
      "Normal",
      "1"
    ],
  ];

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
        <div className="flex-grow p-8 grid grid-cols-3 gap-4">{/* Content */}
        <div className="col-span-1 flex space-x-4">
          <FaChartBar className="text-3xl text-black-600" />
          <h1 className="text-3xl font-bold text-black-800">Total Requests</h1>
        </div>


          <div className="col-span-2">
            <Navbar options={['GET', 'POST', 'PUT', 'DELETE']} color="purple" size='w-1/3'/>
          
          <div className="flex p-8 justify-center items-center" >
              <StripedTable
                widthClass={StripedTableWidthClass}
                heightClass={StripedTableHeightClass}
                columnsName={StripedTableColumnsName}
                rows={StripedTableRows}
              />
          </div>
          </div>
        </div>
      </div>
    </div>
  );
}
