interface Session {
    sessionName: string;
    sessionId: number;
    startTime: string;
    endTime: string;
    numParticipants: number;
    state: string;
}

interface SensorAssign {
    sensorId: number;
    name: string;
    playerId: number;
}

interface PlayersWithoutSensor {
    playerId: number;
    name: string;
}

const getSessionsTeam = async (axiosInstance: any, teamId: number) => {
    try {
        const response = await axiosInstance.get("/sessions/team?teamId=" + teamId);
        
        if (response) {
            const authResponse = response.data as Session[];
            
            return authResponse;
        }
        return [];
      } catch (error) {
        console.error(error);
        return [];
      }
}

const getTeamSensors = async (axiosInstance: any, teamId: number) => {
    try {
        const response = await axiosInstance.get("/team/sensors?teamId=" + teamId);
        
        if (response) {
            const authResponse = response.data as SensorAssign[];
            
            return authResponse;
        }
        return [];
      } catch (error) {
        console.error(error);
        return [];
      }
}

const deleteTeamSensorsAssignPlayer = async (axiosInstance: any, playerId: number, sensorId: number) => {
    try {
        console.log("teamId: " + playerId + " sensorId: " + sensorId);
        const response = await axiosInstance.delete("/team/sensors/assign-player", {
            data: {
                playerId: playerId,
                sensorId: sensorId
            }
        });
        
        if (response) {
            console.log(response);
        }
      } catch (error) {
        console.error(error);
      }
}

const getPlayersWithoutSensor = async (axiosInstance: any, teamId: number) => {
    try {
        const response = await axiosInstance.get("/team/players-without-sensor?teamId=" + teamId);
        
        if (response) {
            const authResponse = response.data as PlayersWithoutSensor[];
            
            return authResponse;
        }
        return [];
      } catch (error) {
        console.error(error);
        return [];
      }
}

const postSensorPlayer = async (axiosInstance: any, playerId: number, sensorId: number) => {
    try {
        const response = await axiosInstance.post("/team/sensors/assign-player", {

                playerId: playerId,
                sensorId: sensorId

        });
        
        if (response) {
            console.log(response);
        }
      } catch (error) {
        console.error(error);
      }
}

export { getTeamSensors, deleteTeamSensorsAssignPlayer, getPlayersWithoutSensor, postSensorPlayer, getSessionsTeam };
export type { SensorAssign, PlayersWithoutSensor, Session };
