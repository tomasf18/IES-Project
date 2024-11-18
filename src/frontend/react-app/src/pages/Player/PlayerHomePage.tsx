import { SideBar, StripedTable } from "../../components";
import { FaHome, FaEye, FaCircle } from "react-icons/fa";
import { Link, useLocation } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getSessionsPlayer, Session } from "../../api/PlayerConsumer";

export default function PlayerHomePage() {
  const navLinks = [{ icon: <FaHome />, to: "/player/home" }];

  const user = useUser();
  const auth = useAuth();
  const location = useLocation();
  const avatarUrl = user.profilePictureUrl;
  const [sessions, setSessions] = useState<Session[]>([]);

  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [user, auth]);

  // Fetch display data
  useEffect(() => {
    if (user?.userId) {
      getSessionsPlayer(auth.axiosInstance, user.userId)
        .then((response) => {
          setSessions(response);
        })
        .catch((error) => {
          console.error("Error fetching player sessions:", error);
        });
    }
  }, [auth.axiosInstance, user?.userId]);

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
        <div className="flex-grow flex flex-col p-8 jsutify-center items-center">
          <div className="w-4/5 flex justify-between items-center mb-6">
            <h2 className="text-3xl font-bold text-left pb-5">Sessions</h2>
          </div>
          <div className="w-full flex flex-col h-full">
            {/* Top Content */}
            <div className="flex-grow flex items-center justify-center mb-4">
              <StripedTable
                widthClass="w-4/5 justify-center mx-auto"
                heightClass="h-full"
                columnsName={[
                  "Session Name",
                  "Day",
                  "Time(min)",
                  "Participants",
                  "State",
                  "Details",
                ]}
                rows={sessions.map((session) => {
                  const duration = session.endTime
                    ? Date.parse(session.endTime) -
                      Date.parse(session.startTime)
                    : Date.now() - Date.parse(session.startTime);
                  const durationMinutes = Math.floor(duration / 60000);

                  const formattedStartTime = new Date(
                    session.startTime
                  ).toLocaleDateString("en-US", {
                    month: "short",
                    day: "numeric",
                  });

                  return [
                    <div
                      className={` ${
                        session.isMatch
                          ? "text-red-primary font-bold text-xl"
                          : "text-blue-primary"
                      }`}
                    >
                      {session.sessionName}
                    </div>,
                    formattedStartTime,
                    durationMinutes,
                    session.numParticipants,
                    session.state === "Closed" ? (
                      <>
                        <div className="flex items-center justify-center">
                          <FaCircle className="text-sm text-red-primary mr-2" />
                          <p>Closed</p>
                        </div>
                      </>
                    ) : (
                      <div className="flex items-center justify-center">
                        <FaCircle className="text-sm text-green-primary mr-2" />
                        <p>Open</p>
                      </div>
                    ),
                    <Link to = {`/player/session/${session.sessionId}`}>
                      <FaEye className="text-blue-primary cursor-pointer text-xl mx-auto hover:text-blue-darker hover:scale-125 transition-transform duration-200" />
                    </Link>,
                  ];
                })}
              />
            </div>

            {/* Lower Content */}
            <div className="flex-grow flex items-center justify-center pt-4">
              
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
