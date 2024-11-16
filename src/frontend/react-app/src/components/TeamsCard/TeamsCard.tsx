

import TeamUniqueCard from "./TeamUniqueCard";

interface Team {
  teamName: string;
  numberTeamMembers: number;
  teamPhotoURL: string;
  teamId: string;
}

interface TeamsCardProps {
  teams: Team[];
  handleTeamManagement: (teamId: string) => void;
}

export default function TeamsCard({ teams, handleTeamManagement }: TeamsCardProps) {
  return (
    
      <div className="grid grid-cols-5 gap-4 justify-items-center w-full ">
        {teams.map((team) => (
          <TeamUniqueCard
            key={team.teamId}
            teamName={team.teamName}
            numberTeamMembers={team.numberTeamMembers}
            teamPhotoURL={team.teamPhotoURL}
            teamId={team.teamId}
            handleTeamManagement={handleTeamManagement}
          />
        ))}
      </div>
  );
}
