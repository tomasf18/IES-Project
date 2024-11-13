```typescript
const teams = [
    {
      teamName: "Real Madrid",
      numberTeamMembers: 10,
      teamPhotoURL: Google,
      teamId: "team-1231",
    },
    {
      teamName: "Barcelona",
      numberTeamMembers: 12,
      teamPhotoURL: Google,
      teamId: "team-1232",
    },
    {
      teamName: "Atletico Madrid",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Atletico Madrid",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Atletico Madrid",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Atletico Madrid",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    {
      teamName: "Atletico Madrid",
      numberTeamMembers: 8,
      teamPhotoURL: Google,
      teamId: "team-1233",
    },
    // Add more team objects as needed
  ];

  const handleTeamManagement = (teamId: string) => {
    console.log(`Managing team with id: ${teamId}`);
  };

  return (
    <div className="flex flex-wrap justify-center p-4">
      <TeamsCard teams={teams} handleTeamManagement={handleTeamManagement} />
    </div>
  );
```