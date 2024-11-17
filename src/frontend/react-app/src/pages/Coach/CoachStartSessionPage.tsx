import { PlayersCard, SideBar, Player } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getTeamPlayersAvailableReaTimeInfo, RealTimeInfo } from "../../api";

export default function CoachStartSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-session" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  let refreshRate = 1000;
  let heartRateThreshold = 100;
  const user = useUser();
  const auth = useAuth();
  const navigate = useNavigate();
  const [playersRealTimeInfo, setPlayersRealTimeInfo] = useState<RealTimeInfo[]>([]);
  const isMatch = useLocation().pathname === "/coach/start-session";

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
  }, []);

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
  const location = useLocation();

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
        <div className="flex flex-wrap justify-center p-4">
          <PlayersCard 
            players={
              playersRealTimeInfo.map((player) => ({
                playerPhotoURL: player.playerProfilePictureUrl,
                playerName: player.playerName,
                playerId: player.playerId.toString(),
                singleValue: player.heartRateData.length > 0 ? player.heartRateData[player.heartRateData.length - 1].value?.toString() || "N/A" : "N/A",
                actualState: player.heartRate < heartRateThreshold  ? "Normal" : "Critical",
                color: "red",
                values: player.heartRateData.map((data) => data.value.toString()),
                metric: "bpm",
              } as Player))
            } 
            handlePlayerManagement={() => navigate("/coach/sensors")}
          />
        </div>
      </div>
    </div>
  );
}
