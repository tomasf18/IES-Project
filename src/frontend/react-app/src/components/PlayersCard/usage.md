```typescript
const players = [
    {
        playerPhotoURL: Google,
        playerName: "João Pinto",
        playerId: "123",
        singleValue: "150",
        values; ["150", "151", "152", "153"],
    }
];

const handlePlayerManagement = (playerId: string) => {
  console.log(`Player with id: ${playerId}`);
};

return (
    <div className="flex flex-wrap justify-center p-4">
      <PlayersCard players={players} handlePlayerManagement={handlePlayerManagement} />
    </div>
  );
```