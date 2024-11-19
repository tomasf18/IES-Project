import { SideBar, StripedTable, ChartSection } from "../../components";
import {
  FaHome,
  FaHeart,
  FaTemperatureHigh,
  FaHeadSideCough,
} from "react-icons/fa";
import { useLocation, useParams } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import {
  getSessionHistoricalInfo,
  SessionHistoricalInfo,
  getSessionRealTimeInfo,
  SessionRealTimeInfo,
} from "../../api";

export default function PlayerSessionInfo() {
  const navLinks = [{ icon: <FaHome />, to: "/player/home" }];

  const user = useUser();
  const auth = useAuth();
  const location = useLocation();
  const { sessionState } = location.state || {};
  const avatarUrl = user.profilePictureUrl;
  const { sessionId } = useParams();
  // parse sessionId to int
  const sessionIdInt = sessionId ? parseInt(sessionId) : null;
  const [sessionInfo, setSessionInfo] = useState<
    SessionHistoricalInfo | SessionRealTimeInfo | null
  >(null);

  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [user, auth]);

  // Fetch display data
  useEffect(() => {
    let interval: NodeJS.Timeout | null = null;

    const fetchHistoricalData = async () => {
      try {
        const response = await getSessionHistoricalInfo(
          auth.axiosInstance,
          sessionIdInt!,
          user.userId
        );
        setSessionInfo(response);
      } catch (error) {
        console.error("Error fetching historical session info:", error);
        setSessionInfo(null);
      }
    };

    const fetchRealTimeData = async () => {
      try {
        const response = await getSessionRealTimeInfo(
          auth.axiosInstance,
          sessionIdInt!,
          user.userId
        );
        setSessionInfo(response);
      } catch (error) {
        console.error("Error fetching real-time session info:", error);
        setSessionInfo(null);
      }
    };

    if (sessionIdInt !== null) {
      if (sessionState === "Closed") {
        fetchHistoricalData();
      } else {
        fetchRealTimeData(); // Fetch initial data
        interval = setInterval(fetchRealTimeData, 2000);
      }
    }

    return () => {
      if (interval) {
        clearInterval(interval);
      }
    };
  }, [auth.axiosInstance, user.userId, sessionIdInt, sessionState]);

  const heartRateData = sessionInfo?.historicalDataPlayers[0].heartRateData.map(
    (item: { value: number }, index: number) => ({
      name: index,
      value: item.value,
    })
  );

  const bodyTemperatureData =
    sessionInfo?.historicalDataPlayers[0].bodyTemperatureData.map(
      (item: { value: number }, index: number) => ({
        name: index,
        value: item.value,
      })
    );

  const respiratoryRateData =
    sessionInfo?.historicalDataPlayers[0].respiratoryRateData.map(
      (item: { value: number }, index: number) => ({
        name: index,
        value: item.value,
      })
    );

  const heartRateValue =
    sessionInfo &&
    sessionState === "Closed" &&
    "averageHeartRate" in sessionInfo
      ? parseInt(sessionInfo.averageHeartRate.toString(), 10)
      : sessionInfo && "lastHeartRate" in sessionInfo
      ? parseInt(sessionInfo.lastHeartRate.toString(), 10)
      : 0;

  const bodyTemperatureValue =
    sessionInfo &&
    sessionState === "Closed" &&
    "averageBodyTemperature" in sessionInfo
      ? parseFloat(
          (
            sessionInfo as SessionHistoricalInfo
          ).averageBodyTemperature.toString()
        ).toFixed(1)
      : sessionInfo && "lastBodyTemperature" in sessionInfo
      ? parseFloat(
          (sessionInfo as SessionRealTimeInfo).lastBodyTemperature.toString()
        ).toFixed(1)
      : 0.0;

  const respiratoryRateValue =
    sessionInfo &&
    sessionState === "Closed" &&
    "averageRespiratoryRate" in sessionInfo
      ? parseInt(sessionInfo.averageRespiratoryRate.toString(), 10)
      : sessionInfo && "lastRespiratoryRate" in sessionInfo
      ? parseInt(sessionInfo.lastRespiratoryRate.toString(), 10)
      : 0;

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
        <div className="flex-grow flex p-6 flex-col jsutify-center items-center">
          <div className="border-4 w-4/5 p-6 mb-40">
            <StripedTable
              widthClass="justify-center mx-auto"
              heightClass="h-full"
              columnsName={[
                "Session Name",
                "Date",
                "Time(min)",
                "Participants",
              ]}
              rows={
                sessionInfo
                  ? [
                      [
                        sessionInfo.sessionName,
                        sessionInfo.date,
                        sessionInfo.time,
                        sessionInfo.participants,
                      ],
                    ]
                  : []
              }
            />
          </div>

          <div className="w-full space-y-6">
            {/* heartRateData */}
            <ChartSection
              bgClass=""
              icon={<FaHeart className="text-red-primary text-5xl mr-4" />}
              title={
                sessionState === "Closed"
                  ? "Average Heart Rate"
                  : "Last Heart Rate"
              }
              value={heartRateValue}
              unit="bpm"
              data={heartRateData || []}
              strokeColor="#E46C6C"
            />

            {/* bodyTemperatureData */}
            <ChartSection
              bgClass="bg-gray-100"
              icon={
                <FaTemperatureHigh className="text-cyan-500 text-5xl mr-4" />
              }
              title={
                sessionState === "Closed"
                  ? "Average Body Temperature"
                  : "Last Body Temperature"
              }
              value={bodyTemperatureValue}
              unit="°C"
              data={bodyTemperatureData || []}
              strokeColor="#25bfd9"
            />

            {/* respiratoryRateData */}
            <ChartSection
              bgClass=""
              icon={
                <FaHeadSideCough className="text-violet-500 text-5xl mr-4" />
              }
              title={
                sessionState === "Closed"
                  ? "Average Respiratory Rate"
                  : "Last Respiratory Rate"
              }
              value={respiratoryRateValue}
              unit="rpm"
              data={respiratoryRateData || []}
              strokeColor="#8884d8"
            />
          </div>
        </div>
      </div>
    </div>
  );
}