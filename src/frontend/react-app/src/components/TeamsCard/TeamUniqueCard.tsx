import { Button } from 'flowbite-react';
import { useNavigate } from "react-router-dom";

interface TeamCardProps {
  teamName: string;
  numberTeamMembers: number;
  teamPhotoURL: string;
  teamId: string;
}

export default function TeamUniqueCard({
  teamName,
  numberTeamMembers,
  teamPhotoURL,
  teamId
}: TeamCardProps) {    

    const navigate = useNavigate();
    
    return (
        <div className={`flex flex-col overflow-hidden rounded-lg shadow-lg p-6 w-64 h-auto`}>
          <div className="flex items-center mb-4 justify-left">
            <img
              src={teamPhotoURL}
              alt={`${teamName} logo`}
              className="w-12 h-14 rounded bg-gray-100 p-2 mr-4"
            />
            <h3 className="text-lg font-bold">{teamName}</h3>
          </div>
          <p className="text-3xl font-semibold">{numberTeamMembers} <span className="text-gray-600 text-base">members</span></p>
          <div className="text-left mt-auto justify-end">
            <Button className="mt-4 w-full bg-green-t3" onClick={() => navigate(`/admin/teams-managing/config?teamId=${teamId}&teamName=${teamName}`) }> 
            {/* ?${teamId} */}
              Manage
            </Button>
          </div>
        </div>
      );
}
