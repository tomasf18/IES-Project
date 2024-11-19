interface SensorAssign {
    sensorId: number;
    name: string;
    playerId: number;
}

interface Team {
    teamName: string;
    numberTeamMembers: number;
    teamId: string;
  }


const addTeamSensor = async (axiosInstance: any, sensorId: number ,teamId: number) => {
    try {
        const response = await axiosInstance.post("/team/sensors", {
            sensorId: sensorId,
            teamId: teamId,
        });

        if (response) {
            const authResponse = response.data;
            return authResponse;
        }
        return null;

    } catch (error) {
        console.error("Error adding new sensor:", error);
        return null;
    }
};

const deleteTeamSensor = async (
    axiosInstance: any, 
    sensorId: number 
) => {
    try {
        console.log("Deleting sensor:", sensorId);

        const response = await axiosInstance.delete(
            "/team/sensors?sensorId=" + sensorId
        );
        
        if (response) {
            console.log(response);
        }

    } catch (error) {
        console.error("Error deleting sensor :", sensorId);
        return null;
    }
};

const getTeamsInfo = async (
    axiosInstance: any
) => {
    try {
        const response = await axiosInstance.get("/team/teams-info");

        if (response) {
            const authResponse = response.data;
            console.log("Teams info:", authResponse);
            return authResponse;
        }

        return null;

    } catch (error) {
        console.error("Error fetching teams info:", error);
        return null;
    }
}

export { addTeamSensor, deleteTeamSensor, getTeamsInfo };
