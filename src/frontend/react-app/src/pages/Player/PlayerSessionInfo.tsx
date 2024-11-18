import { SideBar, StripedTable } from "../../components";
import {
  FaHome,
  FaHeart,
  FaTemperatureHigh,
  FaHeadSideCough,
} from "react-icons/fa";
import { useLocation, useParams } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getSessionHistoricalInfo, SessionHistoricalInfo } from "../../api";

import {
  LineChart,
  ResponsiveContainer,
  XAxis,
  YAxis,
  Tooltip,
  Line,
} from "recharts";

export default function PlayerSessionInfo() {
  const navLinks = [{ icon: <FaHome />, to: "/player/home" }];

  const user = useUser();
  const auth = useAuth();
  const location = useLocation();
  const avatarUrl = user.profilePictureUrl;
  const { sessionId } = useParams();
  // parse sessionId to int
  const sessionIdInt = sessionId ? parseInt(sessionId) : null;
  const [sessionInfo, setSessionInfo] = useState<SessionHistoricalInfo | null>(
    null
  );

  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [user, auth]);

  // Fetch display data
  useEffect(() => {
    if (user?.userId && sessionId) {
      if (sessionIdInt !== null) {
        getSessionHistoricalInfo(auth.axiosInstance, sessionIdInt, user.userId)
          .then((response) => {
            if (response) {
              setSessionInfo(response);
            } else {
              setSessionInfo(null);
            }
          })
          .catch((error) => {
            console.error("Error fetching player sessions:", error);
            setSessionInfo(null);
          });
      }
    }
  }, [auth.axiosInstance, user?.userId, sessionIdInt]);

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
          <div className="border-4 w-4/5 p-6 mb-12">
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
          {/* Sec */}
          <div className="w-full space-y-6">
            {/* Seção 1 */}
            <div className="flex items-center p-4">
              {/* Card Heart Rate */}
              <div className="w-1/6 flex flex-col p-4 bg-white rounded-lg shadow-xl">
                <div className="flex items-center">
                  <FaHeart className="text-red-primary text-5xl mr-4" />
                  <h2 className="text-2xl font-bold text-gray-800">
                    Average Heart Rate
                  </h2>
                </div>
                <h2 className="mt-4 text-3xl font-bold text-gray-800">
                  {parseInt(
                    sessionInfo?.averageHeartRate?.toString() || "0",
                    10
                  )}{" "}
                  bpm
                </h2>
              </div>

              {/* Graphic */}
              <div className="flex-grow p-4 ml-20">
                {heartRateData && heartRateData.length > 0 ? (
                  <ResponsiveContainer width="100%" height={300}>
                    <LineChart data={heartRateData}>
                      <XAxis dataKey="name" />
                      <YAxis />
                      <Tooltip />
                      <Line type="monotone" dataKey="value" stroke="#8884d8" />
                    </LineChart>
                  </ResponsiveContainer>
                ) : (
                  <p>No data available</p>
                )}
              </div>
            </div>

            {/* Seção 2 */}
            <div className="flex items-center bg-gray-100 p-4">
              {/* Card Body Temperature */}
              <div className="w-1/6 flex flex-col p-4 bg-white rounded-lg shadow-xl">
                <div className="flex items-center">
                  <FaTemperatureHigh className="text-cyan-500 text-5xl mr-4" />
                  <h2 className="text-2xl font-bold text-gray-800">
                    Average Body Temperature
                  </h2>
                </div>
                <h2 className="mt-4 text-3xl font-bold text-gray-800">
                  {sessionInfo?.averageBodyTemperature
                    ? parseFloat(
                        sessionInfo.averageBodyTemperature.toString()
                      ).toFixed(1)
                    : 0.0}{" "}
                  °C
                </h2>
              </div>

              {/* Graphic */}
              <div className="flex-grow p-4 ml-20">
                {bodyTemperatureData && bodyTemperatureData.length > 0 ? (
                  <ResponsiveContainer width="100%" height={300}>
                    <LineChart data={bodyTemperatureData}>
                      <XAxis dataKey="name" />
                      <YAxis />
                      <Tooltip />
                      <Line type="monotone" dataKey="value" stroke="#8884d8" />
                    </LineChart>
                  </ResponsiveContainer>
                ) : (
                  <p>No data available</p>
                )}
              </div>
            </div>

            {/* Seção 3 */}
            <div className="flex items-center p-4">
              <div className="w-1/6 flex flex-col p-4 bg-white rounded-lg shadow-xl">
                <div className="flex items-center">
                  <FaHeadSideCough className="text-violet-500 text-5xl mr-4" />
                  <h2 className="text-2xl font-bold text-gray-800">
                    Average Respiratory Rate
                  </h2>
                </div>
                <h2 className="mt-4 text-3xl font-bold text-gray-800">
                  {parseInt(
                    sessionInfo?.averageRespiratoryRate?.toString() || "0",
                    10
                  )}{" "}
                  rpm
                </h2>
              </div>

              {/* Graphic */}
              <div className="flex-grow p-4 ml-20">
                {respiratoryRateData && respiratoryRateData.length > 0 ? (
                  <ResponsiveContainer width="100%" height={300}>
                    <LineChart data={respiratoryRateData}>
                      <XAxis dataKey="name" />
                      <YAxis />
                      <Tooltip />
                      <Line type="monotone" dataKey="value" stroke="#8884d8" />
                    </LineChart>
                  </ResponsiveContainer>
                ) : (
                  <p>No data available</p>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
