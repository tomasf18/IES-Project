

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
    <div className="flex flex-wrap justify-center gap-16">
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
