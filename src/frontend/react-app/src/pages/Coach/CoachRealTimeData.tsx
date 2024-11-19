import { PlayersCard, SideBar, Player, PlayerUniqueCard, SimpleModal } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { endSession, getBySessionHistoricalData, getBySessionRealTimeData, getSessionInfo, postMatch, postSessions, postSessionsAssignPlayer, RealTimeInfo, SessionRealTimeData } from "../../api";
import { Checkbox, Label } from "flowbite-react";

export default function CoachStartSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-session?match=true" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  let refreshRate = 1000;
  let heartRateThreshold = 100;
  let bodyTemperatureThreshold = 60;
  let respiratoryRateThreshold = 30;

  const { sessionId } = useParams();
  const user = useUser();
  const auth = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [sessionRealTimeData, setSessionRealTimeData] = useState<SessionRealTimeData>();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isHistorical, setIsHistorical] = useState(false);

  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [user, auth]);

  // Fetch display data
  useEffect(() => {
    const fetchData = async () => {
      if (user?.teamId && sessionId) {
        var sessionInfo = await getSessionInfo(auth.axiosInstance, Number(sessionId));

        if (sessionInfo?.endTime === null) {
          // run-time data
          setIsHistorical(false);
          getBySessionRealTimeData(auth.axiosInstance, Number(sessionId))
            .then((response) => {
              if (response) {
                setSessionRealTimeData(response);
              }
            })
            .catch((error) => {
              console.error("Error fetching team sensors:", error);
            });
        } else {
          // historical data
          setIsHistorical(true);
          getBySessionHistoricalData(auth.axiosInstance, Number(sessionId))
            .then((response) => {
              if (response) {
                setSessionRealTimeData(response);
                  }
              }
            )
            .catch((error) => {
              console.error("Error fetching team sensors:", error);
            });
        }
    };}

    fetchData();
  }, [auth.axiosInstance, user?.teamId]);

  useEffect(() => {
    if (isHistorical) {
      return;
    }

    const interval = setInterval(() => {
      if (user?.teamId && sessionId) {
        getBySessionRealTimeData(auth.axiosInstance, Number(sessionId))
          .then((response) => {
            if (response) {
              setSessionRealTimeData(response);
            }
          })
          .catch((error) => {
            console.error("Error fetching team sensors:", error);
          });
      }
    }, refreshRate);

    return () => clearInterval(interval);
  }, [auth.axiosInstance, user?.teamId, refreshRate]);

  const avatarUrl = user.profilePictureUrl;

  const handlePlayerManagement = (playerId: string) => {
    console.log(`Player with id: ${playerId}`);
  };

  // Handle "End Session" click
  const handleEndSessionClick = () => {
      setIsModalOpen(true); // Open the modal
  };

  // Handle modal close (cancel)
  const handleModalClose = () => {
      setIsModalOpen(false);
  };

  // Handle modal confirmation
  const handleConfirmEndSession = () => {
      setIsModalOpen(false); // Close the modal
      if (sessionId !== undefined) {
          endSession(auth.axiosInstance, Number(sessionId))
              .then(() => {
                  navigate("/coach/sessions");
              })
              .catch((error) => {
                  console.error("Error ending session:", error);
                  alert("Failed to end session. Please try again.");
              });
      } else {
          console.error("Session ID is undefined");
          alert("Failed to end session. Session ID is missing.");
      }
  };

  return (
    <div className="flex min-h-screen">
      <SideBar
          avatarUrl={avatarUrl}
          navLinks={navLinks}
          activePath={location.pathname}
      />

      {/* Main Content */}
      <div className="flex-grow p-8">
          {/* Logo */}
          <img
              src="/logo.png"
              alt="Logo"
              className="absolute top-8 right-8 h-24"
          />
          <div className="w-full pl-2 pr-20">
              {/* Header */}
              <button
                  onClick={handleEndSessionClick}
                  className="bg-red-500 text-white px-4 py-2 rounded-lg my-5"
              >
                  <strong>End Session</strong>
              </button>

              {/* SimpleModal for confirmation */}
              <SimpleModal
                show={isModalOpen}
                onClose={handleModalClose}
                onConfirm={handleConfirmEndSession}
                content={
                    <p className="text-lg font-semibold">
                        Are you sure you want to end this session?
                    </p>
                }
                buttonText="End Session"
                buttonClass="bg-red-500 text-white px-20 py-2 rounded-lg hover:bg-red-600"
            />


              <div className="w-full flex justify-between mb-6 text-2xl">
                  <div>
                      <h1 className="font-bold">
                          Real-time Players Data
                      </h1>
                  </div>
                  <div>
                      <p>
                          <span className="font-bold">Name:</span>{" "}
                          {sessionRealTimeData?.sessionName || "Unknown"}
                      </p>
                  </div>
                  <div>
                      <p>
                          <span className="font-bold">Date:</span>{" "}
                          {sessionRealTimeData?.date || "Unknown"}
                      </p>
                  </div>
                  <div>
                      <p>
                          <span className="font-bold">Participants:</span>{" "}
                          {sessionRealTimeData?.historicalDataPlayers
                              .length || 0}
                      </p>
                  </div>
              </div>

              {/* Players Cards */}
              <div className="grid grid-cols-2 gap-6">
                  {sessionRealTimeData?.historicalDataPlayers.map(
                      (player) => (
                          <div
                              key={player.playerId} // Unique for each player
                              className="w-full m-3"
                          >
                              <h2 className="text-xl font-bold">
                                  {player.playerName}
                              </h2>
                              <div className="w-full flex justify-start gap-4">
                                  {/* Heart Rate Card */}
                                  <PlayerUniqueCard
                                      key={`${player.playerId}-heart`} // Unique key for Heart Rate Card
                                      playerPhotoURL="https://via.placeholder.com/100" // Replace with actual photo URL if available
                                      playerName={!isHistorical ? "Heart Rate" : "Average Heart Rate"}
                                      playerId={player.playerId.toString()}
                                      singleValue={
                                        !isHistorical?
                                          player.heartRateData
                                              .at(-1)
                                              ?.value?.toString() || "N/A"
                                          :(player.heartRateData.length > 0 ? (player.heartRateData.reduce((acc, curr) => acc + curr.value, 0) / player.heartRateData.length).toString() : "N/A")
                                      }
                                      actualState={
                                        !isHistorical?
                                          (player.heartRateData.at(-1)
                                              ?.value ?? 0) >
                                          heartRateThreshold
                                              ? "Critical"
                                              : "Normal"
                                          : (player.heartRateData.length > 0 ? (player.heartRateData.reduce((acc, curr) => acc + curr.value, 0) / player.heartRateData.length) > heartRateThreshold ? "Critical" : "Normal" : "N/A")
                                      }
                                      color="red"
                                      values={player.heartRateData.map(
                                          (d) => d.value.toString()
                                      )}
                                      metric="bpm"
                                      handlePlayerManagement={
                                          handlePlayerManagement
                                      }
                                      selected={false}
                                  />

                                  {/* Body Temperature Card */}
                                  <PlayerUniqueCard
                                      key={`${player.playerId}-temperature`} // Unique key for Body Temperature Card
                                      playerPhotoURL="https://via.placeholder.com/100"
                                      playerName={!isHistorical ? "Body Temperature" : "Average Body Temperature"}
                                      playerId={player.playerId.toString()}
                                      singleValue={
                                        !isHistorical?
                                          player.bodyTemperatureData
                                              .at(-1)
                                              ?.value.toString() || "N/A"
                                          : (player.bodyTemperatureData.length > 0 ? (player.bodyTemperatureData.reduce((acc, curr) => acc + curr.value, 0) / player.bodyTemperatureData.length).toString() : "N/A")
                                      }
                                      actualState={
                                        !isHistorical?
                                          (player.bodyTemperatureData.at(
                                              -1
                                          )?.value ?? 0) >
                                          bodyTemperatureThreshold
                                              ? "Critical"
                                              : "Normal"
                                          : (player.bodyTemperatureData.length > 0 ? (player.bodyTemperatureData.reduce((acc, curr) => acc + curr.value, 0) / player.bodyTemperatureData.length) > bodyTemperatureThreshold ? "Critical" : "Normal" : "N/A")
                                      }
                                      color="blue"
                                      values={player.bodyTemperatureData.map(
                                          (d) => d.value.toString()
                                      )}
                                      metric="°C"
                                      handlePlayerManagement={
                                          handlePlayerManagement
                                      }
                                      selected={false}
                                  />

                                  {/* Respiratory Rate Card */}
                                  <PlayerUniqueCard
                                      key={`${player.playerId}-respiratory`} // Unique key for Respiratory Rate Card
                                      playerPhotoURL="https://via.placeholder.com/100"
                                      playerName={!isHistorical ? "Respiratory Rate" : "Average Respiratory Rate"}
                                      playerId={player.playerId.toString()}
                                      singleValue={
                                        !isHistorical?
                                          player.respiratoryRateData
                                              .at(-1)
                                              ?.value.toString() || "N/A"
                                          : (player.respiratoryRateData.length > 0 ? (player.respiratoryRateData.reduce((acc, curr) => acc + curr.value, 0) / player.respiratoryRateData.length).toString() : "N/A")
                                      }
                                      actualState={
                                        !isHistorical?
                                          (player.respiratoryRateData.at(
                                              -1
                                          )?.value ?? 0) >
                                          respiratoryRateThreshold
                                              ? "Critical"
                                              : "Normal"
                                          : (player.respiratoryRateData.length > 0 ? (player.respiratoryRateData.reduce((acc, curr) => acc + curr.value, 0) / player.respiratoryRateData.length) > respiratoryRateThreshold ? "Critical" : "Normal" : "N/A")
                                      }
                                      color="Orange"
                                      values={player.respiratoryRateData.map(
                                          (d) => d.value.toString()
                                      )}
                                      metric="rpm"
                                      handlePlayerManagement={
                                          handlePlayerManagement
                                      }
                                      selected={false}
                                  />
                              </div>
                          </div>
                      )
                  )}
              </div>
          </div>
      </div>
  </div>
  );
}