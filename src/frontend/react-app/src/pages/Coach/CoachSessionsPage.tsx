import { SideBar, StripedTable } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getSessionsTeam, Session } from "../../api";
import { FaCircle, FaEye } from "react-icons/fa";


export default function CoachSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-match" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  const user = useUser();
  const auth = useAuth();
  const [sessions, setSessions] = useState<Session[]>([]);
  
  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [user, auth]);

  // Fetch display data
  useEffect(() => {
    if (user?.teamId) {
      getSessionsTeam(auth.axiosInstance, user.teamId)
        .then((response) => {
          setSessions(response);
        })
        .catch((error) => {
          console.error("Error fetching team sensors:", error);
        });
    }
  }, [auth.axiosInstance, user?.teamId]);

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
        <div className="flex flex-col p-4">
            <StripedTable 
                widthClass="w-3/4 justify-center mx-auto"
                heightClass="h-full"
                columnsName={["Session Name", "Day", "Time(min)", "Participants", "State", "Details"]}
                rows={
                    sessions.map((session) => {
                        const duration = session.endTime 
                          ? Date.parse(session.endTime) - Date.parse(session.startTime)
                          : Date.now() - Date.parse(session.startTime);
                        const durationMinutes = Math.floor(duration / 60000);

                        return [
                          session.sessionName,
                          session.sessionId,
                          durationMinutes,
                          session.numParticipants,
                          session.state === "Closed" ? 
                          <>
                            <div className="flex items-center">
                              <FaCircle className="text-sm text-red-primary mr-2" />
                              <p>Closed</p>
                            </div>
                          </>
                          : 
                            <div className="flex items-center">
                              <FaCircle className="text-sm text-green-primary mr-2" />
                              <p>Open</p>
                            </div>,
                          <FaEye className="text-blue-primary cursor-pointer text-xl mx-auto hover:text-blue-darker hover:scale-125 transition-transform duration-200" />
                        ];
                    })
                }
            />
        </div>
      </div>
    </div>
  );
}
