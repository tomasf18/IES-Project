interface SensorAssign {
    sensorId: number;
    name: string;
    playerId: number;
}


const addTeamSensor = async (axiosInstance: any, newSensorId: number ,teamId: number) => {
    try {
        const response = await axiosInstance.post("/team/sensors", {
            sensorId: newSensorId,
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

export { addTeamSensor };