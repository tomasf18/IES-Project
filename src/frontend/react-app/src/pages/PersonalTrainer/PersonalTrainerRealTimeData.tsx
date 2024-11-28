import { useState, useEffect } from "react";
import { SideBar, PlayerUniqueCard, SimpleModal } from "../../components"; // Ensure these components exist
import { FaFutbol } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import {
    getSessionRealTimeData,
    SessionRealTimeData,
    endSession,
} from "../../api";

export default function PersonalTrainerRealTimeData() {
    const navLinks = [
        { icon: <FaFutbol />, to: "/personal-trainer/session" },
    ];

    let refreshRate = 1000;
    let heartRateThreshold = 200;
    let bodyTemperatureThreshold = 60;
    let respiratoryRateThreshold = 30;

    const user = useUser();
    const auth = useAuth();

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [sessionRealTimeData, setSessionRealTimeData] =
        useState<SessionRealTimeData>();

    useEffect(() => {
        if (user?.username === "") {
            auth.authMe();
        }
    }, [user, auth]);

    const avatarUrl = user.profilePictureUrl;
    const location = useLocation();
    const navigate = useNavigate();

    // Fetch session real-time data
    useEffect(() => {
        if (user?.teamId) {
            getSessionRealTimeData(auth.axiosInstance, user.userId)
                .then((response) => {
                    if (response) {
                        console.log(response);
                        setSessionRealTimeData(response);
                    }
                })
                .catch((error) => {
                    console.error("Error fetching team sensors:", error);
                });
        }
    }, [auth.axiosInstance, user?.teamId, user?.userId]);

    useEffect(() => {
        const interval = setInterval(() => {
          if (user?.teamId) {
            getSessionRealTimeData(auth.axiosInstance, user.userId)
              .then((response) => {
                if (response) {
                    setSessionRealTimeData(response);
                    console.log(response);
                }
              })
              .catch((error) => {
                console.error("Error fetching team sensors:", error);
              });
          }
        }, refreshRate);

        return () => clearInterval(interval);
      }, [auth.axiosInstance, user?.teamId, refreshRate]);

    const handlePlayerManagement = (playerId: string) => {
        navigate(`/personal-trainer/session/${sessionRealTimeData?.sessionId}/player/${playerId}`);
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
        const sessionId = sessionRealTimeData?.sessionId;
        if (sessionId !== undefined) {
            endSession(auth.axiosInstance, sessionId)
                .then(() => {
                    navigate("/personal-trainer/start-session");
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
        <div className="flex min-h-screen h-lvh">
            <SideBar
                avatarUrl={avatarUrl}
                navLinks={navLinks}
                activePath={location.pathname}
            />

            {/* Main Content */}
            <div className="flex-grow p-8 overflow-y-auto h-full">
                {/* Logo */}
                <img
                    src="/logo.png"
                    alt="Logo"
                    className="absolute top-8 right-8 h-24"
                />
                <div className="w-full pl-2 pr-20">
                    {/* Header */}
                    {/* "End Session" Button */}
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
                                            playerName="Heart Rate"
                                            playerId={player.playerId.toString()}
                                            singleValue={
                                                player.heartRateData
                                                    .at(-1)
                                                    ?.value?.toString() || "N/A"
                                            }
                                            actualState={
                                                (player.heartRateData.at(-1)
                                                    ?.value ?? 0) >
                                                heartRateThreshold
                                                    ? "Critical"
                                                    : "Normal"
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
                                            playerName="Body Temperature"
                                            playerId={player.playerId.toString()}
                                            singleValue={
                                                player.bodyTemperatureData
                                                    .at(-1)
                                                    ?.value.toString() || "N/A"
                                            }
                                            actualState={
                                                (player.bodyTemperatureData.at(
                                                    -1
                                                )?.value ?? 0) >
                                                bodyTemperatureThreshold
                                                    ? "Critical"
                                                    : "Normal"
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
                                            playerName="Respiratory Rate"
                                            playerId={player.playerId.toString()}
                                            singleValue={
                                                player.respiratoryRateData
                                                    .at(-1)
                                                    ?.value.toString() || "N/A"
                                            }
                                            actualState={
                                                (player.respiratoryRateData.at(
                                                    -1
                                                )?.value ?? 0) >
                                                respiratoryRateThreshold
                                                    ? "Critical"
                                                    : "Normal"
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
