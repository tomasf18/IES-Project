import { SideBar, StripedTable } from "../../components";
import { FaChartBar, FaFutbol, FaHeartPulse } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import { getSessionsTeam, Session } from "../../api";
import { FaCircle, FaEye } from "react-icons/fa";


export default function CoachSessionPage() {
  const navLinks = [
    { icon: <FaChartBar />, to: "/coach/sessions" },
    { icon: <FaFutbol />, to: "/coach/start-session?match=true" },
    { icon: <FaHeartPulse />, to: "/coach/sensors" },
  ];
  const user = useUser();
  const auth = useAuth();
  const navigate = useNavigate();
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
    <div className="flex min-h-screen h-lvh">
      <SideBar avatarUrl={avatarUrl} navLinks={navLinks} activePath={location.pathname} />

      {/* Main Content */}
      <div className="flex-grow p-8 overflow-y-auto h-full">
        {/* Logo */}
        <img
          src="/logo.png"
          alt="Logo"
          className="absolute top-8 right-8 h-24"
        />
        {/* Content */}
        <div className=" mt-28 flex-grow flex flex-col p-8 jsutify-center items-center">
            <div className="w-full max-w-[80rem] flex justify-between items-center mb-6">
              <h2 className="text-3xl font-bold text-left pb-5">Sessions</h2>
                <button className="mb-5 mx-10 bg-blue-primary text-lg rounded-3xl bg-green-t3 text-black px-12 py-2 hover:bg-green-t2 transition-colors duration-200 ml-auto"
                onClick={() => navigate("/coach/start-session")}
                >
                Create <div className="font-bold text-xl inline-block ml-1
                ">Session</div>
                </button>
                <button className="mb-5 bg-blue-primary text-lg rounded-3xl bg-red-300 text-black px-12 py-2 hover:bg-red-400 transition-colors duration-200"
                onClick={() => navigate("/coach/start-session?match=true")}
                >
                Create <div className="font-bold text-xl inline-block ml-1
                ">Match</div>
                </button>
            </div>
            <StripedTable 
                widthClass="w-4/5 justify-center mx-auto"
                heightClass="h-full"
                columnsName={["Session Name", "Day", "Time(min)", "Participants", "State", "Details"]}
                rows={
                    sessions.map((session) => {
                        const duration = session.endTime 
                          ? Date.parse(session.endTime) - Date.parse(session.startTime)
                          : Date.now() - Date.parse(session.startTime);
                        const durationMinutes = Math.floor(duration / 60000);

                        return [
                          <div className={` ${ session.isMatch ? "text-red-primary font-bold text-xl" : "text-blue-primary" }`}>
                            {session.sessionName}
                          </div>,
                          new Date(session.startTime).toLocaleDateString("en-GB", { month: "short", day: "numeric" }),
                          durationMinutes,
                          session.numParticipants,
                          session.state === "Closed" ? 
                          <>
                            <div className="flex items-center justify-center">
                              <FaCircle className="text-sm text-red-primary mr-2" />
                              <p>Closed</p>
                            </div>
                          </>
                          : 
                            <div className="flex items-center justify-center">
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
