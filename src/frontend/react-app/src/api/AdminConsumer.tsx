import { Dispatch, SetStateAction } from "react";
import * as StompJs from "@stomp/stompjs";

interface TeamDirectors {
    teamDirectorId: number;
    name: string;
    isOfficialMember: boolean;
    registrationCode: string;
}

interface SensorsTeamWeek {
    teamName: string;
    state: string;
    value: number;
}

interface SensorsLast5Days {
    date: string;
    value: number;
}

{
    /* Section for Sensors Tracking */
}

const websocket_url =
    import.meta.env.VITE_WEBSOCKET_SCHEME +
    "://" +
    import.meta.env.VITE_HOST_NAME +
    "/" +
    import.meta.env.VITE_WEBSOCKET_ENDPOINT;

let stompClientRealTimeSensorsDay: StompJs.Client | null = null;

const connectWebSocketRealTimeSensorsDay = async (
    setRealTimeSensorsDay: Dispatch<SetStateAction<{ date: string; accesses: unknown }[]>>
) => {
    if (stompClientRealTimeSensorsDay && stompClientRealTimeSensorsDay.active) {
        console.log("WebSocket already connected");
        return; // Prevent duplicate connection
    }

    stompClientRealTimeSensorsDay = new StompJs.Client({
        brokerURL: websocket_url,
        debug: (str) => console.log(str),
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });

    // Define behavior on successful connection
    stompClientRealTimeSensorsDay.onConnect = (frame) => {
        console.log("Connected: " + frame);

        // Subscribe to the topic and listen for updates
        stompClientRealTimeSensorsDay?.subscribe(
            "/topic/sensorsDay",
            (message) => {
                const newRealTimeSensorsDay = JSON.parse(message.body);
                console.log("Received new dataqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq : ", newRealTimeSensorsDay);

                const formattedData = Object.entries(newRealTimeSensorsDay).map(([date, accesses]) => ({
                    date,
                    accesses,
                }));

                setRealTimeSensorsDay(formattedData);
            }
        );
    };

    // Handle WebSocket errors
    stompClientRealTimeSensorsDay.onWebSocketError = (error) => {
        console.error("WebSocket error: ", error);
    };

    // Handle STOMP protocol errors
    stompClientRealTimeSensorsDay.onStompError = (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);
    };

    // Activate the client
    stompClientRealTimeSensorsDay.activate();

    // Cleanup on component unmount
    return () => {
        if (stompClientRealTimeSensorsDay && stompClientRealTimeSensorsDay.active) {
            stompClientRealTimeSensorsDay.deactivate();
            console.log("WebSocket connection closed");
        }
    };
};

let stompClientSensorsLast5Days: StompJs.Client | null = null;

const connectWebSocketSensorsLast5Days = async (
    setSensorsLast5Days: Dispatch<SetStateAction<SensorsLast5Days[]>>
) => {
    if (stompClientSensorsLast5Days && stompClientSensorsLast5Days.active) {
        console.log("WebSocket already connected");
        return; // Prevent duplicate connection
    }

    stompClientSensorsLast5Days = new StompJs.Client({
        brokerURL: websocket_url,
        debug: (str) => console.log(str),
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });

    // Define behavior on successful connection
    stompClientSensorsLast5Days.onConnect = (frame) => {
        console.log("Connected: " + frame);

        // Subscribe to the topic and listen for updates
        stompClientSensorsLast5Days?.subscribe(
            "/topic/sensorsLast5Days",
            (message) => {
                const newSensorsLast5Days = JSON.parse(message.body);
                console.log("Received new data: ", newSensorsLast5Days);

                setSensorsLast5Days(newSensorsLast5Days);
            }
        );
    };

    // Handle WebSocket errors
    stompClientSensorsLast5Days.onWebSocketError = (error) => {
        console.error("WebSocket error: ", error);
    };

    // Handle STOMP protocol errors
    stompClientSensorsLast5Days.onStompError = (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);
    };

    // Activate the client
    stompClientSensorsLast5Days.activate();

    // Cleanup on component unmount
    return () => {
        if (stompClientSensorsLast5Days && stompClientSensorsLast5Days.active) {
            stompClientSensorsLast5Days.deactivate();
            console.log("WebSocket connection closed");
        }
    };
};

let stompClientSensorsTeamWeek: StompJs.Client | null = null;

const connectWebSocketSensorsTeamWeek = async (
    setSensorsTeamWeek: Dispatch<SetStateAction<SensorsTeamWeek[]>>
) => {
    if (stompClientSensorsTeamWeek && stompClientSensorsTeamWeek.active) {
        console.log("WebSocket already connected");
        return; // Prevent duplicate connection
    }

    stompClientSensorsTeamWeek = new StompJs.Client({
        brokerURL: websocket_url,
        debug: (str) => console.log(str),
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });

    // Define behavior on successful connection
    stompClientSensorsTeamWeek.onConnect = (frame) => {
        console.log("Connected: " + frame);

        // Subscribe to the topic and listen for updates
        stompClientSensorsTeamWeek?.subscribe(
            "/topic/sensorsTeamWeek",
            (message) => {
                const newSensorsTeamWeek = JSON.parse(message.body);
                console.log("Received new data: ", newSensorsTeamWeek);

                setSensorsTeamWeek(newSensorsTeamWeek);
            }
        );
    };

    // Handle WebSocket errors
    stompClientSensorsTeamWeek.onWebSocketError = (error) => {
        console.error("WebSocket error: ", error);
    };

    // Handle STOMP protocol errors
    stompClientSensorsTeamWeek.onStompError = (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);
    };

    // Activate the client
    stompClientSensorsTeamWeek.activate();

    // Cleanup on component unmount
    return () => {
        if (stompClientSensorsTeamWeek && stompClientSensorsTeamWeek.active) {
            stompClientSensorsTeamWeek.deactivate();
            console.log("WebSocket connection closed");
        }
    };
};

const getSensorsTeamWeek = async (axiosInstance: any) => {
    try {
        const response = await axiosInstance.get(
            "/administrator/sensors-team-week"
        );

        if (response) {
            return response.data as SensorsTeamWeek[];
        }
        return null;
    } catch (error) {
        console.error("Error fetching sensors team week:", error);
        return null;
    }
};

const getSensorsLast5Days = async (axiosInstance: any) => {
    try {
        const response = await axiosInstance.get(
            "/administrator/sensors-last-5-days"
        );

        if (response) {
            return response.data as SensorsLast5Days[];
        }
        return null;
    } catch (error) {
        console.error("Error fetching sensors last 5 days:", error);
        return null;
    }
};

const getSensorsDay = async (axiosInstance: any, date: string) => {
    try {
        const response = await axiosInstance.get(
            "/administrator/sensors-day" + "?date=" + date
        );

        if (response) {
            return response.data;
        }
        return null;
    } catch (error) {
        console.error("Error fetching sensors day:", error);
        return null;
    }
};

{
    /* Section for Teams Managing */
}

const addTeamSensor = async (
    axiosInstance: any,
    sensorId: number,
    teamId: number
) => {
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

const deleteTeamSensor = async (axiosInstance: any, sensorId: number) => {
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

const getTeamsInfo = async (axiosInstance: any) => {
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
};

const createTeam = async (axiosInstance: any, teamName: string) => {
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
};

const getTeamDirectors = async (axiosInstance: any, teamId: number) => {
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
};

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
            return response;
        }

        return null;
    } catch (error) {
        console.error("Error adding team director:", error);
        return null;
    }
};

const deleteTeam = async (axiosInstance: any, teamId: number) => {
    try {
        const response = await axiosInstance.delete(
            "/team" + "?teamId=" + teamId
        );

        if (response) {
            console.log(response);
            return response;
        }
        return null;
    } catch (error) {
        console.error("Error deleting team:", error);
        return null;
    }
};

export {
    connectWebSocketRealTimeSensorsDay,
    connectWebSocketSensorsLast5Days,
    connectWebSocketSensorsTeamWeek,
    addTeamSensor,
    deleteTeamSensor,
    getTeamsInfo,
    createTeam,
    getTeamDirectors,
    addTeamDirector,
    deleteTeam,
    getSensorsTeamWeek,
    getSensorsLast5Days,
    getSensorsDay,
};
export type { TeamDirectors, SensorsTeamWeek, SensorsLast5Days };
