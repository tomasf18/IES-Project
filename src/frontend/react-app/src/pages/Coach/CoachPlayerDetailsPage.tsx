import { SideBar, ChartSection, StripedTable } from "../../components";
import {
  FaArrowLeft,
  FaChartBar,
  FaFutbol,
  FaHeadSideCough,
  FaHeart,
  FaHeartPulse,
  FaTemperatureHigh,
} from "react-icons/fa6";
import { Link, useParams } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import {
  getSessionHistoricalInfo,
  getSessionInfo,
  getSessionRealTimeInfo,
  SessionHistoricalInfo,
  SessionRealTimeInfo,
} from "../../api";

export default function CoachStartSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-session?match=true" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  let refreshRate = 1000;

  const { sessionId } = useParams();
  const user = useUser();
  const auth = useAuth();

  const [isHistorical, setIsHistorical] = useState(false);

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
    const fetchData = async () => {
      let interval: NodeJS.Timeout | null = null;

      var sessionInfo = await getSessionInfo(
        auth.axiosInstance,
        Number(sessionId)
      );

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

      if (sessionId !== null) {
        if (sessionInfo?.endTime !== null) {
          setIsHistorical(true);
          fetchHistoricalData();
        } else {
          setIsHistorical(false);
          fetchRealTimeData(); // Fetch initial data
          interval = setInterval(fetchRealTimeData, refreshRate);
        }
      }

      return () => {
        if (interval) {
          clearInterval(interval);
        }
      };
    };

    fetchData();
  }, [auth.axiosInstance, user.userId, sessionId]);

  const avatarUrl = user.profilePictureUrl;

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
    sessionInfo && isHistorical && "averageHeartRate" in sessionInfo
      ? parseInt(sessionInfo.averageHeartRate.toString(), 10)
      : sessionInfo && "lastHeartRate" in sessionInfo
      ? parseInt(sessionInfo.lastHeartRate.toString(), 10)
      : 2;

  const bodyTemperatureValue =
    sessionInfo && isHistorical && "averageBodyTemperature" in sessionInfo
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
    sessionInfo && isHistorical && "averageRespiratoryRate" in sessionInfo
      ? parseInt(sessionInfo.averageRespiratoryRate.toString(), 10)
      : sessionInfo && "lastRespiratoryRate" in sessionInfo
      ? parseInt(sessionInfo.lastRespiratoryRate.toString(), 10)
      : 0;

  return (
    <div className="flex min-h-screen h-lvh">
      <SideBar
        avatarUrl={avatarUrl}
        navLinks={navLinks}
        activePath={"/coach/sessions"}
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
        <div className="flex-grow flex p-6 flex-col jsutify-center items-center">
          <div className="flex w-full items-start mb-20">
            <Link to={`/coach/sessions/${sessionId}`}>
              <button className="bg-green-t3 hover:bg-gray-400 transition duration-300 text-white px-4 py-2 rounded-lg my-5">
                <FaArrowLeft className="inline-block mr-2" />
                <strong>Back</strong>
              </button>
            </Link>
            <div className="border-4 w-4/5 p-6 ml-20">
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
          </div>

          <div className="w-full space-y-6">
            {/* heartRateData */}
            <ChartSection
              bgClass=""
              icon={<FaHeart className="text-red-primary text-5xl mr-4" />}
              title={isHistorical ? "Average Heart Rate" : "Last Heart Rate"}
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
                isHistorical
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
                isHistorical
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
