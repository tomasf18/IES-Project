import { PlayersCard, SideBar, Player } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getTeamPlayersAvailableReaTimeInfo, postMatch, postSessions, postSessionsAssignPlayer, RealTimeInfo } from "../../api";
import { Checkbox, Label } from "flowbite-react";

export default function CoachStartSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-session?match=true" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  let refreshRate = 1000;
  let heartRateThreshold = 100;
  const user = useUser();
  const auth = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [playersRealTimeInfo, setPlayersRealTimeInfo] = useState<RealTimeInfo[]>([]);
  const [selectedPlayers, setSelectedPlayers] = useState<string[]>([]);
  const [sessionName, setSessionName] = useState("");
  const [opponentTeam, setOpponentTeam] = useState("");
  const [type, setType] = useState("");
  const [locationMatch, setLocationMatch] = useState("");
  const [weather, setWeather] = useState("");

  const searchParams = new URLSearchParams(location.search);
  const isMatch = searchParams.get("match");

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
        getTeamPlayersAvailableReaTimeInfo(auth.axiosInstance, user.teamId)
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
    console.log(selectedPlayers)

  };

  const handleSelectAllPlayers = () => {
    if (selectedPlayers.length === playersRealTimeInfo.length) {
      setSelectedPlayers([]);
    } else {
      setSelectedPlayers(playersRealTimeInfo.map((player) => player.playerId.toString()));
    }
  };

  const createSession = () => {
    if (user?.teamId) {
      let sessionId = 0;
      if (isMatch === "true") {
        postMatch(auth.axiosInstance, sessionName, user.userId, opponentTeam, type, locationMatch, weather)
          .then((s) => {
            sessionId = s;
          })
          .catch((error) => {
            console.error("Error creating match:", error);
          });
      } else {
        postSessions(auth.axiosInstance, sessionName, user.userId)
          .then((s) => {
            sessionId = s;
          })
          .catch((error) => {
            console.error("Error creating session:", error);
          });
      }
      selectedPlayers.forEach((playerId) => {
        postSessionsAssignPlayer(auth.axiosInstance, sessionId, parseInt(playerId))
          .catch((error) => {
            console.error("Error assigning player to session:", error);
          });
      });

      navigate("/coach/sessions/" + sessionId);
    }
  }

  return (
    <div className="flex min-h-screen">
      <SideBar avatarUrl={avatarUrl} navLinks={navLinks} activePath={location.pathname} />

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

            <PlayersCard 
              players={
                playersRealTimeInfo.map((player) => ({
                  playerPhotoURL: player.playerProfilePictureUrl,
                  playerName: player.playerName,
                  playerId: player.playerId.toString(),
                  singleValue: player.heartRateData.length > 0 ? player.heartRateData[player.heartRateData.length - 1].value?.toString() || "N/A" : "N/A",
                  actualState: player.heartRateData.length > 0 ? player.heartRateData[player.heartRateData.length - 1].value > heartRateThreshold ? "Critical" : "Normal" : "N/A",
                  color: "red",
                  values: player.heartRateData.map((data) => data.value.toString()),
                  metric: "bpm"
                } as Player))
              } 
              handlePlayerManagement={handlePlayerManagement}
              selectedPlayers={selectedPlayers}
            />
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
              onSubmit={() => createSession()}
              >
              <div className="flex flex-col w-full">
                <label className="text-base text-gray-700" htmlFor="teamName">
                  Session Name
                </label>
                <input
                  id="teamName"
                  type="text"
                  placeholder="Text"
                  value={sessionName}
                  onChange={(e) => setSessionName(e.target.value)}
                  className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                />
              </div>
              { isMatch ? 
              <>
                <div className="flex flex-col w-full">
                  <label className="text-base text-gray-700" htmlFor="opponentTeam">
                    Opponent Team
                  </label>
                  <input
                    id="opponentTeam"
                    type="text"
                    placeholder="Text"
                    value={opponentTeam}
                    onChange={(e) => setOpponentTeam(e.target.value)}
                    className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                  />
                </div>

                <div className="flex flex-col w-full">
                  <label className="text-base text-gray-700" htmlFor="type">
                    Type (Friendly or Tournament Match)
                  </label>
                  <input
                    id="type"
                    type="text"
                    placeholder="Text"
                    value={type}
                    onChange={(e) => setType(e.target.value)}
                    className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                  />
                </div>

                <div className="flex flex-col w-full">
                  <label className="text-base text-gray-700" htmlFor="location">
                    Location
                  </label>
                  <input
                    id="location"
                    type="text"
                    placeholder="Text"
                    value={locationMatch}
                    onChange={(e) => setLocationMatch(e.target.value)}
                    className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                  />
                </div>

                <div className="flex flex-col w-full">
                  <label className="text-base text-gray-700" htmlFor="weather">
                    Weather
                  </label>
                  <input
                    id="weather"
                    type="text"
                    placeholder="Text"
                    value={weather}
                    onChange={(e) => setWeather(e.target.value)}
                    className="w-full p-3 border rounded-lg bg-green-50 placeholder-gray-400"
                  />
                </div>
              </>
              : <></>}
              
              <div className="grid grid-cols-3">
              <div className="flex items-center gap-2 col-span-2">
                <Checkbox
                  id="selectAllPlayers"
                  className="text-gray-800 focus:ring-gray-600 text-lg"
                  checked={selectedPlayers.length === playersRealTimeInfo.length}
                  onChange={handleSelectAllPlayers}
                />
                <Label htmlFor="selectAllPlayers" className="text-lg">Select all players</Label>
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