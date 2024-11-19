import axios from "axios";

interface TeamDirectors {
    directorId: number;
    name: string;
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
            return authResponse;
        }

        return null;

    } catch (error) {
        console.error("Error fetching teams info:", error);
        return null;
    }
}


const createTeam = async (
    axiosInstance: any,
    teamName: string
) => {
    try {
        const response = await axiosInstance.post("/team", {
            name: teamName,
        });

        if (response) {
            console.log("Team created:", response.data);
            const authResponse = response.data;
            return authResponse;
        }
        return null;

    } catch (error) {
        console.error("Error creating new team:", error);
        return null;
    }
}

const getTeamDirectors = async (
    axiosInstance: any,
    teamId: number
) => {
    try {
        const response = await axiosInstance.get(
            "/team/team-directors" + "?teamId=" + teamId
        );

        if (response) {
            const authResponse = response.data as TeamDirectors[];
            return authResponse;
        }
        return [];

    } catch (error) {
        console.error("Error fetching team directors:", error);
        return [];
    }
}

const deleteTeamDirector = async (
    axiosInstance: any,
    teamId: number,
    directorId: number
) => {
    try {
        // TODO: implement endpoint
        const response = await axiosInstance.delete(
            "/team/team-directors" + "?teamId=" + teamId + "&directorId=" + directorId
        );

        if (response) {
            console.log(response);
        }

    } catch (error) {
        console.error("Error deleting team director:", error);
        return null;
    }
}

const addTeamDirector = async (
    axiosInstance: any,
    teamId: number,
    name: string,
    profilePictureUrl: string,
    userTypeId: number
) => {
    try {
        const response = await axiosInstance.post("/team/registration-code", {
            teamId: teamId,
            name: name,
            profilePictureUrl: profilePictureUrl,
            userTypeId: userTypeId,
        });

        if (response) {
            console.log(response);
        }

        return null;
    } catch (error) {
        console.error("Error adding team director:", error);
        return null;
    }
}

const deleteTeam = async (
    axiosInstance: any,
    teamId: number
) => {
    try {
        const response = await axiosInstance.delete(
            "/team" + "?teamId=" + teamId
        );

        if (response) {
            console.log(response);
        }
        return null;
    }
    catch (error) {
        console.error("Error deleting team:", error);
        return null;
    }
}


export { addTeamSensor, deleteTeamSensor, getTeamsInfo, createTeam, getTeamDirectors, deleteTeamDirector, addTeamDirector, deleteTeam };
export type { TeamDirectors };
