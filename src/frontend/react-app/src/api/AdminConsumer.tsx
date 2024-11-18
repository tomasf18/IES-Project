interface SensorAssign {
    sensorId: number;
    name: string;
    playerId: number;
}


const addTeamSensor = async (axiosInstance: any, newSensorId: number ,teamId: number) => {
    try {
        const response = await axiosInstance.post(
            "/team/sensors?newSensorId" + newSensorId + "&teamId=" + teamId
        );

        if (response) {
            const authResponse = response.data as SensorAssign[];

            return authResponse;
        }
        return [];
    } catch (error) {
        console.error(error);
        return [];
    }
};

export { addTeamSensor };