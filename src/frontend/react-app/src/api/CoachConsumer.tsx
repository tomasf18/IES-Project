interface Session {
    sessionName: string;
    sessionId: number;
    startTime: string;
    endTime: string;
    numParticipants: number;
    state: string;
    isMatch: boolean;
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

interface RealTimeInfo {
    playerId: number;
    playerName: string;
    playerProfilePictureUrl: string;
    heartRateData: [
        {
            value: number;
            idTimestamp: string;
        }
    ],
    heartRate: number;
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

const postSessions = async (axiosInstance: any, sessionName: string, trainerId: number) => {
    try {
        const response = await axiosInstance.post("/sessions", {
            sessionName: sessionName,
            trainerId: trainerId
        });
        
        if (response) {
            console.log(response);
        }
      } catch (error) {
        console.error(error);
      }
}

const postSessionsAssignPlayer = async (axiosInstance: any, sessionId: number, playerId: number) => {
    try {
        const response = await axiosInstance.post("/sessions/assign-player", {
            sessionId: sessionId,
            playerId: playerId
        });
        
        if (response) {
            console.log(response);
        }
      } catch (error) {
        console.error(error);
      }
}

const getTeamPlayersAvailableReaTimeInfo = async (axiosInstance: any, teamId: number) => {
    try {
        const response = await axiosInstance.get("/team/players-available/real-time-info?teamId=" + teamId);
        
        if (response) {
            const authResponse = response.data as RealTimeInfo[];

            return authResponse;
        }
        return [];
    } catch (error) {
        console.error(error);
        return [];
    }
        
}

export { getSessionsTeam, getTeamSensors, deleteTeamSensorsAssignPlayer, getPlayersWithoutSensor, postSensorPlayer, postSessions, postSessionsAssignPlayer, getTeamPlayersAvailableReaTimeInfo };
export type { Session, SensorAssign, PlayersWithoutSensor, RealTimeInfo };
