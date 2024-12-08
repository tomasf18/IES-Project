import { SideBar, Header, ConfigurationCard, StripedTable } from "../../components";
import { FaUsers, FaHeartPulse, FaUserMinus } from "react-icons/fa6";
import { MdOutlineSensors } from "react-icons/md";
import { useLocation } from "react-router-dom";
import { TextInput } from "flowbite-react";
import { useAuth } from "../../hooks";
import { useEffect, useState } from "react";
import { 
  getTeamSensors,
  SensorAssign,
  deleteTeamSensor,
  addTeamSensor
} from "../../api";

export default function AdminSensorsTrackingPage() {
  const navLinks = [
    { icon: <FaHeartPulse />, to: "/admin/sensors-tracking", label: "Sensors Tracking"},
    { icon: <FaUsers />, to: "/admin/teams-managing", label: "Teams Managing"},
  ];

  const auth = useAuth();
  const location = useLocation();

  const [sensorsAssign, setSensorsAssign] = useState<SensorAssign[]>([]);
  const [newSensorId, setNewSensorId] = useState('');

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
    onClick?: () => void;
  }[] = [{ to: "/", label: "Sign Out", color: "primary", onClick:() => auth.logOut() }];

  let configurationCardWidthClass = "w-[80rem]";
  let configurationCardHeightClass = "h-[50rem]";

  // URL: /admin/teams-managing/sensors?teamId=${teamID}&teamName=${teamName}
  const searchParams = new URLSearchParams(location.search);
  const teamID = searchParams.get("teamId");
  const teamName = searchParams.get("teamName");
  let configurationCardName = teamName || "Default Team Name";

  // Fetch display data
  useEffect(() => {
    if (teamID) {
        getTeamSensors(auth.axiosInstance, Number(teamID))
            .then((response) => {
                setSensorsAssign(response);
            })
            .catch((error) => {
                console.error("Error fetching team sensors:", error);
            });
    }
  }, [auth.axiosInstance, teamID]);

  let StripedTableWidthClass = "w-full";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["SensorId", "Player", "Option"];

  let StripedTableRows = sensorsAssign.map((sensorAssign) => [
    sensorAssign.sensorId,
    sensorAssign.name,
    <button
        onClick={async () => {
            await deleteTeamSensor(
                auth.axiosInstance,
                sensorAssign.sensorId
            );
            // refresh
            try {
                const response = await getTeamSensors(
                    auth.axiosInstance,
                    Number(teamID)
                );
                setSensorsAssign(response);
            } catch (error) {
                console.error(
                    "Error fetching team sensors:",
                    error
                );
            }
        }}
        >
        <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />
    </button>
  ]);

// Append the "Add new SensorId" row
StripedTableRows.push([
  <div className="w-full flex justify-center items-center">
      <TextInput
          placeholder="Add new SensorId"
          value={newSensorId}
          onChange={(e) => setNewSensorId(e.target.value)}
      />
  </div>,
  '', // Empty second column (or you can add content if needed)
  <button
      onClick={async () => {
          try {
              // Call the function to add a new sensor (ensure this function exists)
              console.log("Adding new sensor:", newSensorId);
              await addTeamSensor(auth.axiosInstance, Number(newSensorId),  Number(teamID));
              // Refresh the sensors list
              const response = await getTeamSensors(auth.axiosInstance, Number(teamID));
              setSensorsAssign(response);
              // Reset the input field
              setNewSensorId('');
          } catch (error) {
              console.error("Error adding new sensor:", error);
          }
      }}
  >
      <MdOutlineSensors className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />
  </button>,
]);


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