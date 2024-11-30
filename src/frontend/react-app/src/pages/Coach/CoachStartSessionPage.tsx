import { PlayersCard, SideBar, Player } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import {
    getTeamPlayersAvailableReaTimeInfo,
    postMatch,
    postSessions,
    postSessionsAssignPlayer,
    RealTimeInfo,
    connectWebSocketRealTimeInfo,
} from "../../api";
import { Checkbox, Label } from "flowbite-react";

export default function CoachStartSessionPage() {
    const navLinks = [
        { icon: <FaChartBar />, to: "/coach/sessions" },
        { icon: <FaFutbol />, to: "/coach/start-session?match=true" },
        { icon: <FaHeartPulse />, to: "/coach/sensors" },
    ];
    
    let heartRateThreshold = 100;
    const user = useUser();
    const auth = useAuth();
    const navigate = useNavigate();
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const isMatch = searchParams.get("match");
    const [playersRealTimeInfo, setPlayersRealTimeInfo] = useState<
        RealTimeInfo[]
    >([]);
    const [selectedPlayers, setSelectedPlayers] = useState<string[]>([]);
    const [sessionName, setSessionName] = useState("");
    const [opponentTeam, setOpponentTeam] = useState("");
    const [type, setType] = useState("");
    const [locationMatch, setLocationMatch] = useState("");
    const [weather, setWeather] = useState("");

    // Fetch user authentication details
    useEffect(() => {
        if (user?.username === "") {
            console.log("Fetching user authentication details...");
            auth.authMe();
        }
    }, [user, auth]);

    // Fetch real-time team player information and connect WebSocket
    useEffect(() => {
        const fetchDataAndConnectWebSocket = async () => {
            await fetchTeamRealTimeInfo();

            console.log("Connecting to WebSocket for real-time player info...");
            const cleanupWebSocket = await connectWebSocketRealTimeInfo(
                user.teamId,
                setPlayersRealTimeInfo
            );

            return cleanupWebSocket;
        };

        const cleanupWebSocketPromise = fetchDataAndConnectWebSocket();

        return () => {
            cleanupWebSocketPromise.then((cleanupWebSocket) => {
                console.log(
                    "Cleaning up WebSocket for real-time player info..."
                );
                cleanupWebSocket?.(); // Cleanup WebSocket connection
            });
        };
    }, [auth.axiosInstance, user?.teamId]);

    // Fetch team real-time information
    const fetchTeamRealTimeInfo = async () => {
        try {
            if (user?.teamId) {
                console.log("Fetching real-time team player info...");
                const response = await getTeamPlayersAvailableReaTimeInfo(
                    auth.axiosInstance,
                    user.teamId
                );
                setPlayersRealTimeInfo(response);
                console.log("Fetched team player info:", response);
            }
        } catch (error) {
            console.error("Error fetching team sensors:", error);
        }
    };

    const avatarUrl = user.profilePictureUrl;

    const handlePlayerManagement = (playerId: string) => {
        setSelectedPlayers((prevSelected) =>
            prevSelected.includes(playerId)
                ? prevSelected.filter((id) => id !== playerId)
                : [...prevSelected, playerId]
        );
    };

    const handleSelectAllPlayers = () => {
        if (selectedPlayers.length === playersRealTimeInfo.length) {
            setSelectedPlayers([]);
        } else {
            setSelectedPlayers(
                playersRealTimeInfo.map((player) => player.playerId.toString())
            );
        }
    };

    const createSession = async () => {
        if (user?.teamId) {
            var sessionId = 0;
            if (isMatch === "true") {
                sessionId = await postMatch(
                    auth.axiosInstance,
                    sessionName,
                    user.userId,
                    opponentTeam,
                    type,
                    locationMatch,
                    weather
                ).catch((error) => {
                    console.error("Error creating match:", error);
                });
            } else {
                sessionId = await postSessions(
                    auth.axiosInstance,
                    sessionName,
                    user.userId
                ).catch((error) => {
                    console.error("Error creating session:", error);
                });
            }
            selectedPlayers.forEach((playerId) => {
                postSessionsAssignPlayer(
                    auth.axiosInstance,
                    sessionId,
                    parseInt(playerId)
                ).catch((error) => {
                    console.error("Error assigning player to session:", error);
                });
            });

            navigate("/coach/sessions/" + sessionId);
        }
    };

    return (
        <div className="flex min-h-screen">
            <SideBar
                avatarUrl={avatarUrl}
                navLinks={navLinks}
                activePath={location.pathname + "?match=true"}
            />

            {/* Main Content */}
            <div className="flex-grow p-8 overflow-y-auto h-full">
                {/* Logo */}
                <img
                    src="/logo.png"
                    alt="Logo"
                    className="absolute top-8 right-8 h-24"
                />
                {/* Content */}
                <div className="grid grid-cols-7 gap-4 mt-16">
                    <div className="col-span-5">
                        <div className="w-full flex justify-between mb-6 text-2xl">
                            <div>
                                <h1 className="font-bold">
                                    Real-time Players Data
                                </h1>
                            </div>
                        </div>
                        {playersRealTimeInfo.length === 0 ? (
                            <div className="flex justify-center items-center h-96">
                                <h1 className="text-2xl font-bold text-gray-400">
                                    No players available
                                </h1>
                            </div>
                        ) : (
                            <PlayersCard
                                players={playersRealTimeInfo.map(
                                    (player) =>
                                        ({
                                            playerPhotoURL:
                                                player.playerProfilePictureUrl,
                                            playerName: player.playerName,
                                            playerId:
                                                player.playerId.toString(),
                                            singleValue:
                                                player.heartRateData.length > 0
                                                    ? player.heartRateData[
                                                          player.heartRateData
                                                              .length - 1
                                                      ].value?.toString() ||
                                                      "N/A"
                                                    : "N/A",
                                            actualState:
                                                player.heartRateData.length > 0
                                                    ? player.heartRateData[
                                                          player.heartRateData
                                                              .length - 1
                                                      ].value >
                                                      heartRateThreshold
                                                        ? "Critical"
                                                        : "Normal"
                                                    : "N/A",
                                            color: "red",
                                            values: player.heartRateData.map(
                                                (data) => data.value.toString()
                                            ),
                                            metric: "bpm",
                                        } as Player)
                                )}
                                handlePlayerManagement={handlePlayerManagement}
                                selectedPlayers={selectedPlayers}
                            />
                        )}
                    </div>
                    <div className="col-span-2 mx-10 mt-2">
                        <div className="w-full flex justify-between mb-6 text-2xl">
                            <div>
                                <h1 className="font-bold">
                                    {isMatch ? "Match Info" : "Session Info"}
                                </h1>
                            </div>
                        </div>
                        <form
                            className="flex flex-col items-center justify-center w-full mx-auto space-y-8"
                            onSubmit={async (e) => {
                                e.preventDefault();
                                await createSession();
                            }}
                        >
                            <div className="flex flex-col w-full">
                                <label
                                    className="text-base text-gray-700"
                                    htmlFor="teamName"
                                >
                                    Session Name
                                </label>
                                <input
                                    id="teamName"
                                    type="text"
                                    placeholder="Text"
                                    value={sessionName}
                                    onChange={(e) =>
                                        setSessionName(e.target.value)
                                    }
                                    className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                                />
                            </div>
                            {isMatch ? (
                                <>
                                    <div className="flex flex-col w-full">
                                        <label
                                            className="text-base text-gray-700"
                                            htmlFor="opponentTeam"
                                        >
                                            Opponent Team
                                        </label>
                                        <input
                                            id="opponentTeam"
                                            type="text"
                                            placeholder="Text"
                                            value={opponentTeam}
                                            onChange={(e) =>
                                                setOpponentTeam(e.target.value)
                                            }
                                            className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                                        />
                                    </div>

                                    <div className="flex flex-col w-full">
                                        <label
                                            className="text-base text-gray-700"
                                            htmlFor="type"
                                        >
                                            Type (Friendly or Tournament Match)
                                        </label>
                                        <input
                                            id="type"
                                            type="text"
                                            placeholder="Text"
                                            value={type}
                                            onChange={(e) =>
                                                setType(e.target.value)
                                            }
                                            className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                                        />
                                    </div>

                                    <div className="flex flex-col w-full">
                                        <label
                                            className="text-base text-gray-700"
                                            htmlFor="location"
                                        >
                                            Location
                                        </label>
                                        <input
                                            id="location"
                                            type="text"
                                            placeholder="Text"
                                            value={locationMatch}
                                            onChange={(e) =>
                                                setLocationMatch(e.target.value)
                                            }
                                            className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                                        />
                                    </div>

                                    <div className="flex flex-col w-full">
                                        <label
                                            className="text-base text-gray-700"
                                            htmlFor="weather"
                                        >
                                            Weather
                                        </label>
                                        <input
                                            id="weather"
                                            type="text"
                                            placeholder="Text"
                                            value={weather}
                                            onChange={(e) =>
                                                setWeather(e.target.value)
                                            }
                                            className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                                        />
                                    </div>
                                </>
                            ) : (
                                <></>
                            )}

                            <div className="grid grid-cols-3">
                                <div className="flex items-center gap-2 col-span-2">
                                    <Checkbox
                                        id="selectAllPlayers"
                                        className="text-gray-800 focus:ring-gray-600 text-lg"
                                        checked={
                                            selectedPlayers.length ===
                                            playersRealTimeInfo.length
                                        }
                                        onChange={handleSelectAllPlayers}
                                    />
                                    <Label
                                        htmlFor="selectAllPlayers"
                                        className="text-lg"
                                    >
                                        Select all players
                                    </Label>
                                </div>
                                <button
                                    type="submit"
                                    className="px-10 py-3 mx-auto bg-green-t3 text-black text-lg font-medium rounded-full shadow hover:bg-gray-400 transition duration-300"
                                >
                                    Start
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
