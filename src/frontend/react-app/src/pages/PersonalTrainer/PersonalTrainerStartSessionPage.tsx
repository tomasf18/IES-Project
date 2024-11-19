import { PlayersCard, SideBar, Player } from "../../components";
import { FaChartBar, FaHeartPulse, FaFutbol } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import {
    getTeamPlayersAvailableReaTimeInfo,
    postSessions,
    postSessionsAssignPlayer,
    RealTimeInfo,
} from "../../api";
import { Checkbox, Label } from "flowbite-react";

export default function PersonalTrainerStartSessionage() {
    const navLinks = [
        { icon: <FaChartBar />, to: "/personal-trainer/start-session" },
        { icon: <FaHeartPulse />, to: "/personal-trainer/sensors" },
    ];
    let refreshRate = 1000;
    let heartRateThreshold = 100;
    const user = useUser();
    const auth = useAuth();
    const navigate = useNavigate();
    const location = useLocation();
    const [playersRealTimeInfo, setPlayersRealTimeInfo] = useState<
        RealTimeInfo[]
    >([]);
    const [selectedPlayers, setSelectedPlayers] = useState<string[]>([]);
    const [sessionName, setSessionName] = useState("");

    useEffect(() => {
        if (user?.username === "") {
            auth.authMe();
        }
    }, [user, auth]);

    // Fetch display data
    useEffect(() => {
        if (user?.teamId) {
            getTeamPlayersAvailableReaTimeInfo(auth.axiosInstance, user.teamId)
                .then((response) => {
                    setPlayersRealTimeInfo(response);
                })
                .catch((error) => {
                    console.error("Error fetching team sensors:", error);
                });
        }
    }, [auth.axiosInstance, user?.teamId]);

    useEffect(() => {
        const interval = setInterval(() => {
            if (user?.teamId) {
                getTeamPlayersAvailableReaTimeInfo(
                    auth.axiosInstance,
                    user.teamId
                )
                    .then((response) => {
                        setPlayersRealTimeInfo(response);
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
            sessionId = await postSessions(
                auth.axiosInstance,
                sessionName,
                user.userId
            ).catch((error) => {
                console.error("Error creating session:", error);
            });
            selectedPlayers.forEach((playerId) => {
                postSessionsAssignPlayer(
                    auth.axiosInstance,
                    sessionId,
                    parseInt(playerId)
                ).catch((error) => {
                    console.error("Error assigning player to session:", error);
                });
            });

            navigate("/personal-trainer/session");
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
                                <h1 className="font-bold">Session Info</h1>
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
