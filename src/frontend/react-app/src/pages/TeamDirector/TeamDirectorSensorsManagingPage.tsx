import {
  SideBar,
  Header,
  ConfigurationCard,
  StripedTable,
  SimpleModal,
} from "../../components";
import {
  FaUsers,
  FaHeartPulse,
  FaUserPlus,
  FaUserMinus,
} from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getTeamSensors, SensorAssign, PlayersWithoutSensor, postSensorPlayer, deleteTeamSensorsAssignPlayer, getPlayersWithoutSensor } from "../../api";

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

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
    onClick?: () => void;
  }[] = [{ to: "#", label: "Sign Out", color: "primary", onClick:() => auth.logOut() }];

  const user = useUser();
  const auth = useAuth();
  const location = useLocation();
  const [sensorsAssign, setSensorsAssign] = useState<SensorAssign[]>([]);
  const [openModal, setOpenModal] = useState(false);
  const [playersWithoutSensor, setPlayersWithoutSensor] = useState<PlayersWithoutSensor[]>([]);
  const [currentlySelectedSensor, setCurrentlySelectedSensor] = useState<number | null>(null);
  const [selectedPlayerId, setSelectedPlayerId] = useState<number | null>(null);

  // Fetch user data from token
  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [auth, user?.username]);

  // Fetch display data
  useEffect(() => {
    if (user?.teamId) {
      getTeamSensors(auth.axiosInstance, user.teamId)
        .then((response) => {
          setSensorsAssign(response);
        })
        .catch((error) => {
          console.error("Error fetching team sensors:", error);
        });
    }
  }, [auth.axiosInstance, user?.teamId]);

  const openModalHandler = (sensorId: number) => {
    setCurrentlySelectedSensor(sensorId);
    setOpenModal(true);
    getPlayersWithoutSensor(auth.axiosInstance, user.teamId)
      .then((response) => {
        setPlayersWithoutSensor(response);
      })
      .catch((error) => {
        console.error("Error fetching players without sensor:", error);
      });
  };

  const handleConfirm = async () => {
    if (selectedPlayerId !== null && currentlySelectedSensor !== null) {
      setOpenModal(false);
      try {
        await postSensorPlayer(auth.axiosInstance, selectedPlayerId, currentlySelectedSensor);
        const response = await getTeamSensors(auth.axiosInstance, user.teamId);
        setSensorsAssign(response);
      } catch (error) {
        console.error("Error assigning sensor to player:", error);
      }
      setCurrentlySelectedSensor(null);
      setSelectedPlayerId(null);
    }
  };

  let configurationCardRightContent = (
    <div className="max-h-[45rem] overflow-y-auto">
      <StripedTable
        widthClass="w-full"
        heightClass="h-full"
        columnsName={["SensorId", "Player", "Option"]}
        rows={sensorsAssign.map((sensorAssign) => [
          sensorAssign.sensorId,
          sensorAssign.name,
          sensorAssign.name === null ? (
            <button
              onClick={() => {
                openModalHandler(sensorAssign.sensorId);
              }}
            >
              <FaUserPlus className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />
            </button>
          ) : (
            <button
              onClick={async () => {
                await deleteTeamSensorsAssignPlayer(auth.axiosInstance, sensorAssign.playerId, sensorAssign.sensorId);
                try {
                  const response = await getTeamSensors(auth.axiosInstance, user.teamId);
                  setSensorsAssign(response);
                } catch (error) {
                  console.error("Error fetching team sensors:", error);
                }
              }}
            >
              <FaUserMinus className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200" />
            </button>
          ),
        ])}
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
        <div className="flex-grow flex flex-col p-8 justify-center items-center">
          <div className="w-full max-w-[80rem]">
            <h2 className="text-3xl mb-6 text-left">Sensors Managing</h2>
          </div>
          <ConfigurationCard
            widthClass="w-[80rem]"
            heightClass="h-[50rem]"
            name={user.name}
            rightContent={configurationCardRightContent}
          />
        </div>
      </div>
      <SimpleModal
        show={openModal}
        onClose={() => {
          setOpenModal(false);
          setCurrentlySelectedSensor(null);
          setSelectedPlayerId(null);
        }}
        content={
          <>
            <h2 className="text-center font-bold text-lg">Players Available</h2>
            <form className="max-w-sm mx-auto">
              <label htmlFor="players" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Select a player</label>
              <select
                id="players"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                value={selectedPlayerId ?? ""}
                onChange={(e) => setSelectedPlayerId(Number(e.target.value))}
              >
                <option value="" disabled>Choose a player</option>
                {playersWithoutSensor.map((player) => (
                  <option key={player.playerId} value={player.playerId}>{player.name}</option>
                ))}
              </select>
            </form>
          </>
        }
        buttonText="Yes, I'm sure"
        buttonClass={`${selectedPlayerId === null ? "bg-gray-200 cursor-not-allowed" : "bg-gray-300 hover:bg-gray-600"} rounded rounded-2xl px-10`}
        onConfirm={handleConfirm}
      />
    </div>
  );
}
