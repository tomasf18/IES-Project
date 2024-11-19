import {
  SideBar,
  Header,
  ConfigurationCard,
  StripedTable,
  Button,
  SimpleModal,
} from "../../components";
import {
  FaUsers,
  FaHeartPulse,
  FaPen,
  FaUserMinus,
  FaRegCopy,
  FaTrashCan,
} from "react-icons/fa6";
import { useLocation } from "react-router-dom";
import { useAuth, useUser } from "../../hooks";
import { useEffect, useState } from "react";
import {
  getTeamMembers,
  TeamMembers,
  deleteRegistrationCode,
  deleteUser,
  refreshRegistrationCode,
  changeProfilePictureUrl,
} from "../../api";

export default function TeamDirectorTeamManagingPage() {
  const navLinks = [
    {
      icon: <FaUsers />,
      to: "/team-director/team-managing",
      label: "Team Managing",
    },
    {
      icon: <FaHeartPulse />,
      to: "/team-director/sensors-managing",
      label: "Sensors Managing",
    },
  ];

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
    onClick?: () => void;
  }[] = [
    {
      to: "#",
      label: "Sign Out",
      color: "primary",
      onClick: () => auth.logOut(),
    },
  ];

  const auth = useAuth();
  const user = useUser();
  const location = useLocation();
  const [teamMembers, setTeamMembers] = useState<TeamMembers[]>([]);
  const [openModalProfileUrl, setOpenModalProfileUrl] = useState(false);
  const [openModalRegistrationCode, setOpenModalRegistrationCode] = useState(false);
  const [profilePictureUrl, setProfilePictureUrl] = useState("");
  const [selectedUserId, setSelectedUserId] = useState<number | null>(null);
  const [registrationCode, setRegistrationCode] = useState("");

  // Fetch user data from token
  useEffect(() => {
    if (user?.username === "") {
      auth.authMe();
    }
  }, [auth, user?.username]);

  // Fetch display data
  useEffect(() => {
    if (user?.teamId) {
      getTeamMembers(auth.axiosInstance, user.teamId)
        .then((response) => {
          setTeamMembers(response);
        })
        .catch((error) => {
          console.error("Error fetching team sensors:", error);
        });
    }
  }, [auth.axiosInstance, user?.teamId]);

  const openModalHandlerProfileUrl = (userId: number, profilePictureUrl: string) => {
    setSelectedUserId(userId);
    setProfilePictureUrl(profilePictureUrl || "");
    setOpenModalProfileUrl(true);
  };

  const openModalHandlerRegistrationCode = (code: string) => {
    setRegistrationCode(code);
    setOpenModalRegistrationCode(true);
  }

  const handleConfirmProfileUrl = async () => {
    if (selectedUserId !== null) {
      try {
        await changeProfilePictureUrl(auth.axiosInstance, selectedUserId, profilePictureUrl);
        const response = await getTeamMembers(auth.axiosInstance, user.teamId);
        setTeamMembers(response);
        setOpenModalProfileUrl(false);
      } catch (error) {
        console.error("Error changing profile picture URL:", error);
      }
    }
  };

  const handleConfirmRegistrationCode = async () => {
    setOpenModalRegistrationCode(false);
  }

  let configurationCardRightContent = (
    <div className="max-h-[45rem] overflow-y-auto">
      <StripedTable
        widthClass="w-full"
        heightClass="h-full"
        columnsName={["Team Members", "", "Role", "Options "]}
        rows={teamMembers.map((teamMember) => [
          teamMember.name,
          teamMember.profilePictureUrl ? (
            <FaPen
              className="text-green-primary cursor-pointer text-xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200"
              onClick={() => openModalHandlerProfileUrl(teamMember.id, teamMember.profilePictureUrl)}
            />
          ) : (
            <Button color="primary" className="mx-auto" onClick={() => openModalHandlerProfileUrl(teamMember.id, teamMember.profilePictureUrl)}>
              Add Photo
            </Button>
          ),
          teamMember.userTypeId === 1
            ? "Player"
            : teamMember.userTypeId === 3
            ? "Coach"
            : teamMember.userTypeId === 4
            ? "Personal Trainer"
            : "Unknown",
          teamMember.registrationCode ? (
            <div className="flex justify-center items-center space-x-4">
              <FaRegCopy 
                className="text-black-primary cursor-pointer text-2xl hover:text-black-darker hover:scale-125 transition-transform duration-200" 
                onClick={async () => {
                  const newCode = await refreshRegistrationCode(
                    auth.axiosInstance,
                    teamMember.registrationCode
                  );
                  
                  openModalHandlerRegistrationCode(newCode);
                  try {
                    const response = await getTeamMembers(
                      auth.axiosInstance,
                      user.teamId
                    );
                    setTeamMembers(response);
                  } catch (error) {
                    console.error("Error fetching team members:", error);
                  }
                }}
              />
              <FaTrashCan
                className="text-red-primary cursor-pointer text-2xl hover:text-red-600 hover:scale-125 transition-transform duration-200"
                onClick={async () => {
                  await deleteRegistrationCode(
                    auth.axiosInstance,
                    teamMember.registrationCode
                  );
                  try {
                    const response = await getTeamMembers(
                      auth.axiosInstance,
                      user.teamId
                    );
                    setTeamMembers(response);
                  } catch (error) {
                    console.error("Error fetching team members:", error);
                  }
                }}
              />
            </div>
          ) : (
            <FaUserMinus
              className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200"
              onClick={async () => {
                await deleteUser(auth.axiosInstance, teamMember.id);
                try {
                  const response = await getTeamMembers(
                    auth.axiosInstance,
                    user.teamId
                  );
                  setTeamMembers(response);
                } catch (error) {
                  console.error("Error fetching team members:", error);
                }
              }}
            />
          ),
        ])}
      />
    </div>
  );

  return (
    <div className="flex flex-col min-h-screen">
      <Header buttons={headerButtons} />
      <div className="flex flex-grow">
        <SideBar
          navLinks={navLinks}
          width="w-62"
          isLargeBar={true}
          activePath={location.pathname}
        />
        {/* Main Content */}
        <div className="flex-grow flex flex-col p-8 justify-center items-center">
          <div className="w-full max-w-[80rem]">
            <h2 className="text-3xl mb-6 text-left">Team Managing</h2>
          </div>
          <ConfigurationCard
            widthClass="w-[80rem]"
            heightClass="h-[50rem]"
            name={user.name}
            rightContent={configurationCardRightContent}
          />
        </div>
      </div>
      <SimpleModal
        show={openModalProfileUrl}
        onClose={() => {
          setOpenModalProfileUrl(false);
        }}
        content={
          <>
            <h2 className="text-center font-bold text-lg">
              Profile Picture Url
            </h2>
            <form className="max-w-sm mx-auto">
              <label
                htmlFor="profilePictureUrl"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Edit URL
              </label>
              <input
                id="profilePictureUrl"
                type="text"
                value={profilePictureUrl}
                onChange={(e) => setProfilePictureUrl(e.target.value)}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Enter profile picture URL"
              />
            </form>
          </>
        }
        buttonText="Change Url"
        buttonClass={"bg-gray-600"}
        onConfirm={handleConfirmProfileUrl}
      />
      <SimpleModal
        show={openModalRegistrationCode}
        onClose={() => {
          setOpenModalRegistrationCode(false);
        }}
        content={
            <h2 className="text-center font-bold text-lg">
              New Registration Code: {registrationCode}
            </h2>
        }
        buttonText="Ok"
        buttonClass={"bg-gray-600"}
        onConfirm={handleConfirmRegistrationCode}
      />
    </div>
  );
}
