import { useEffect, useState } from 'react';
import PlayersCard from "../components/PlayersCard/PlayersCard";
import { Google } from "../assets";

export default function TestPage() {
  const [players, setPlayers] = useState([
    {
      playerPhotoURL: Google,
      playerName: "Heart Rate",
      playerId: "123",
      singleValue: "150",
      actualState: "Normal",
      color: "red",
      values: ["150", "170", "130", "123", "177", "190", "145", "210"],
      metric: "bpm"
    }, 
    {
      playerPhotoURL: Google,
      playerName: "João Pinto",
      playerId: "1123",
      singleValue: "150",
      actualState: "Critical",
      color: "green",
      values: ["123", "124", "67", "123", "145", "167", "145", "210"],
      metric: "bpm"
    },
    {
      playerPhotoURL: Google,
      playerName: "João Pinto",
      playerId: "112223",
      singleValue: "150",
      actualState: "Critical",
      color: "blue",
      values: ["123", "124", "67", "123", "145", "167", "145", "210"],
      metric: "bpm"
    },
    {
      playerPhotoURL: Google,
      playerName: "João Pinto",
      playerId: "11323",
      singleValue: "150",
      actualState: "Critical",
      color: "red",
      values: [],
      metric: "bpm"
    },
  ]);

  // Add random value every 3 seconds
  useEffect(() => {
    const interval = setInterval(() => {
      setPlayers((prevPlayers) => {
        const newPlayers = [...prevPlayers]; 
        // newPlayers.forEach(player => player.values.push((Math.floor(Math.random() * 100) + 100).toString())); // Add new random value as a string
        // add new random value as a string for the first player
        if (newPlayers[0].values) {
          newPlayers[0].values.push((Math.floor(Math.random() * 100) + 100).toString());
        }
        return newPlayers;
      });
    }, 300);

    return () => clearInterval(interval); // Clear the interval on component unmount
  }, []);

const handlePlayerManagement = (playerId: string) => {
  console.log(`Player with id: ${playerId}`);
};

return (
    <div className="flex flex-wrap justify-center p-4">
      <PlayersCard players={players} handlePlayerManagement={handlePlayerManagement} />
    </div>
  );
}
