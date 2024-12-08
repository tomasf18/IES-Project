import { SideBar, Header, ConfigurationCard, StripedTable } from "../../components";
import { FaUsers, FaHeartPulse, FaUserMinus, FaUserPlus, FaRegCopy, FaTrashCan } from "react-icons/fa6";
import { useLocation, useNavigate } from "react-router-dom";
import { TextInput } from "flowbite-react";
import { SimpleModal } from "../../components";
import { useEffect, useState } from "react";
import { useAuth } from "../../hooks";
import { 
  getTeamDirectors, 
  TeamDirectors, 
  deleteUser, 
  addTeamDirector, 
  deleteTeam, 
  refreshRegistrationCode,
  deleteRegistrationCode
} from "../../api";

export default function AdminManageTeam() {
  const auth = useAuth();

  const [teamDirectors, setTeamDirectors] = useState<TeamDirectors[]>([]);
  const [newTeamDirector, setNewTeamDirector] = useState('');
  const [openModalRegistrationCode, setOpenModalRegistrationCode] = useState(false);
  const [registrationCode, setRegistrationCode] = useState("");

  const openModalHandlerRegistrationCode = (code: string) => {
    setRegistrationCode(code);
    setOpenModalRegistrationCode(true);
  }

  const handleConfirmRegistrationCode = async () => {
    setOpenModalRegistrationCode(false);
  }

  const navLinks = [
    {
      icon: <FaHeartPulse />,
      to: "/admin/sensors-tracking",
      label: "Sensors Tracking",
    },
    {
      icon: <FaUsers />,
      to: "/admin/teams-managing",
      label: "Teams Managing",
    },
  ];

  const headerButtons: {
    to: string;
    label: string;
    color: "primary" | "secondary";
    onClick?: () => void;
  }[] = [{ to: "/", label: "Sign Out", color: "primary", onClick:() => auth.logOut() }];

  let configurationCardWidthClass = "w-[80rem]";
  let configurationCardHeightClass = "h-[50rem]";

  // /admin/teams-managing/config?teamId=${teamId}&teamName=${teamName}
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const teamID = searchParams.get("teamId");
  const teamName = searchParams.get("teamName");
  let configurationCardName = teamName || "Default Team Name";

  // Fetch display data
  useEffect(() => {
    if (teamID) {
      getTeamDirectors(auth.axiosInstance, Number(teamID))
        .then((response) => {
          setTeamDirectors(response);
        })
        .catch((error) => {
          console.error("Error fetching team directors:", error);
        });
    }
  }, [auth.axiosInstance, teamID]);


  let StripedTableWidthClass = "w-full";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["Team Director", "Option"];

  let StripedTableRows = teamDirectors.map((teamDirector) => [
    teamDirector.name,
    teamDirector.registrationCode ? (
      <div className="flex justify-center items-center space-x-4">
        <FaRegCopy 
          className="text-black-primary cursor-pointer text-2xl hover:text-black-darker hover:scale-125 transition-transform duration-200" 
          onClick={async () => {
            const newCode = await refreshRegistrationCode(
              auth.axiosInstance,
              teamDirector.registrationCode
            );
            
            openModalHandlerRegistrationCode(newCode);
            try {
              const response = await getTeamDirectors(
                auth.axiosInstance,
                Number(teamID)
              );
              setTeamDirectors(response);
            } catch (error) {
              console.error("Error fetching team directors:", error);
            }
          }}
        />
        <FaTrashCan
          className="text-red-primary cursor-pointer text-2xl hover:text-red-600 hover:scale-125 transition-transform duration-200"
          onClick={async () => {
            await deleteRegistrationCode(
              auth.axiosInstance,
              teamDirector.registrationCode
            );
            try {
              const response = await getTeamDirectors(
                auth.axiosInstance,
                Number(teamID)
              );
              setTeamDirectors(response);
            } catch (error) {
              console.error("Error fetching team directors:", error);
            }
          }}
        />
      </div>
    ) : (
      <FaUserMinus
        className="text-red-primary cursor-pointer text-2xl mx-auto hover:text-red-600 hover:scale-125 transition-transform duration-200"
        onClick={async () => {
          await deleteUser(auth.axiosInstance, teamDirector.teamDirectorId);
          try {
            const response = await getTeamDirectors(
              auth.axiosInstance,
              Number(teamID)
            );
            setTeamDirectors(response);
          } catch (error) {
            console.error("Error fetching team directors:", error);
          }
        }}
      />
    ),
  ]);

  // Append the "Add Team Director" row
  StripedTableRows.push([
    <div className="w-full flex justify-center items-center">
      <TextInput 
        placeholder="Add Team Director"
        value={newTeamDirector}
        onChange={(e) => setNewTeamDirector(e.target.value)}
      />
    </div>,
    <button
        onClick={async () => {
            try {
                // Call the function to add a new team director (ensure this function exists)
                console.log("Adding new team director:", newTeamDirector);
                
                const code_response = await addTeamDirector(
                  auth.axiosInstance, 
                  Number(teamID), 
                  String(newTeamDirector), 
                  '', 
                  2
                );
                openModalHandlerRegistrationCode(code_response.data.code);
                
                // Refresh the team directors list
                const response = await getTeamDirectors(auth.axiosInstance, Number(teamID));
                setTeamDirectors(response);
                // Reset the input field
                setNewTeamDirector('');
            } catch (error) {
                console.error("Error adding new team director:", error);
            }
        }}
    >
      <FaUserPlus className="text-green-primary cursor-pointer text-2xl mx-auto hover:text-green-darker hover:scale-125 transition-transform duration-200" />
    </button>,
  ]);  

  const navigate = useNavigate();

  const handleDeleteTeam = async () => {
    console.log("Deleting team:", teamID);
    try {
      const response = await deleteTeam(auth.axiosInstance, Number(teamID)); // Assuming `deleteTeam` is an API function
      if (response) {
        console.log("Team deleted successfully");
        return true;
      }
      return false;
    } catch (error) {
      console.error("Error deleting the team:", error);
      return false;
    }
  };
  

  let configurationCardRightContent = (
    <div className="flex flex-col min-h-[45rem]">

      <div className="flex-grow max-h-[45rem] overflow-y-auto">
        <StripedTable
          widthClass={StripedTableWidthClass}
          heightClass={StripedTableHeightClass}
          columnsName={StripedTableColumnsName}
          rows={StripedTableRows}
        />
      </div>

      {/* Buttons at the bottom */}
      <div className="flex flex-col items-center space-y-4 mt-8">
        {/* Sensors Managing Button */}
        <button
          type="submit"
          className="px-16 py-3 bg-green-t3 text-black text-lg font-medium rounded-full shadow hover:bg-gray-400 transition duration-300"
          onClick={() => navigate(`/admin/teams-managing/sensors?teamId=${teamID}&teamName=${teamName}`)}
        >
          Sensors Managing
        </button>

        {/* Delete Team Button */}
        <button
          type="submit"
          className="px-16 py-3 bg-red-primary text-black text-lg font-medium rounded-full shadow hover:bg-gray-400 transition duration-300"
          onClick={async () => {
            const deleted = await handleDeleteTeam(); // Call the function to handle delete
            if (deleted) {
              navigate(`/admin/teams-managing/`); // Navigate after successful deletion
            } else {
              console.error("Failed to delete the team.");
            }
          }}
        >
          Delete Team
        </button>
      </div>
  
    </div>
    
  );
  
  return (
    <>
      <div className="flex flex-col min-h-screen">
        <Header buttons={headerButtons} />
        <div className="flex flex-grow">
          <SideBar
            navLinks={navLinks}
            width="w-62"
            isLargeBar={true}
            activePath={"/admin/teams-managing"}
          />
          {/* Main Content */}
          <div className="flex-grow flex flex-col p-8 justify-center items-center">
          <ConfigurationCard
              widthClass={configurationCardWidthClass}
              heightClass={configurationCardHeightClass}
              name={configurationCardName}
              rightContent={configurationCardRightContent}
            />
          </div>
        </div>
      </div>
    
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
        buttonClass={"bg-gray-600 w-full h-full text-white py-2 px-4 rounded-lg hover:bg-gray-500"}
        onConfirm={handleConfirmRegistrationCode}
      />
    
    </>
  );
}
