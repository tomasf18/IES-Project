import { Dispatch, SetStateAction } from "react";
import * as StompJs from "@stomp/stompjs";

interface Session {
    sessionName: string;
    sessionId: number;
    startTime: string;
    endTime: string;
    numParticipants: number;
    state: string;
    isMatch: boolean;
}

interface SessionHistoricalInfo {
    sessionName: string;
    date: string;
    time: number;
    participants: number;
    historicalDataPlayers: [
        {
            heartRateData: [];
            bodyTemperatureData: [];
            respiratoryRateData: [];
            playerId: number;
            playerName: string;
        }
    ];
    averageHeartRate: number;
    averageBodyTemperature: number;
    averageRespiratoryRate: number;
    opponentTeam: string;
    type: string;
    location: string;
    weather: string;
}

interface SessionRealTimeInfo {
    sessionName: string;
    date: string;
    time: number;
    participants: number;
    historicalDataPlayers: [
        {
            heartRateData: [];
            bodyTemperatureData: [];
            respiratoryRateData: [];
            playerId: number;
            playerName: string;
        }
    ];
    lastHeartRate: number;
    lastBodyTemperature: number;
    lastRespiratoryRate: number;
    opponentTeam: string;
    type: string;
    location: string;
    weather: string;
}

let stompClientRealTimeInfo: StompJs.Client | null = null;

const connectPlayerWebSocketRealTimeInfo = async (setSessionInfo: Dispatch<SetStateAction<SessionHistoricalInfo | SessionRealTimeInfo | null>>, playerId: string) => {

    if (stompClientRealTimeInfo && stompClientRealTimeInfo.active) {
        console.log("WebSocket already connected");
        return; // Prevent duplicate connection
    }

    stompClientRealTimeInfo = new StompJs.Client({
        brokerURL: "ws://localhost:8080/backend-ws",
        debug: (str) => console.log(str),
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
    });

    // Define behavior on successful connection
    stompClientRealTimeInfo.onConnect = (frame) => {
        console.log("Connected: " + frame);

        // Subscribe to the topic and listen for updates
        const endpoint = `/topic/realTimeExtraDetails/${playerId}`;
        stompClientRealTimeInfo?.subscribe(endpoint, (message) => {
            const newPlayerRealTimeInfo = JSON.parse(message.body);
            console.log("Received new data: ", newPlayerRealTimeInfo);

            setSessionInfo(newPlayerRealTimeInfo);
        });
    };

    // Handle WebSocket errors
    stompClientRealTimeInfo.onWebSocketError = (error) => {
        console.error("WebSocket error: ", error);
    };

    // Handle STOMP protocol errors
    stompClientRealTimeInfo.onStompError = (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);
    };

    // Activate the client
    stompClientRealTimeInfo.activate();

    // Cleanup on component unmount
    return () => {
        if (stompClientRealTimeInfo && stompClientRealTimeInfo.active) {
            stompClientRealTimeInfo.deactivate();
            console.log("WebSocket connection closed");
        }
    };
};

// sessions all days of year is like:
// {
//   "playerId": 4,
//   "year": 2024,
//   "sessionsPerDay": {
//     "2024-11-26": 1,
//     "2024-11-19": 1,
//     "2024-11-18": 1,
//     "2024-11-17": 6
//   }
// }

interface SessionsAllDaysOfYear {
    playerId: number;
    year: number;
    sessionsPerDay: Record<string, number>;
}

const getSessionsAllDaysOfYear = async (
    axiosInstance: any,
    playerId: number,
    year: number
): Promise<SessionsAllDaysOfYear | null> => {
    try {
        const response = await axiosInstance.get(
            "/player/sessions/all-days-of-year?playerId=" +
                playerId +
                "&year=" +
                year
        );

        if (response) {
            const authResponse = response.data as SessionsAllDaysOfYear;
            return authResponse;
        }
        return null;
    } catch (error) {
        console.error(error);
        return null;
    }
};

const getSessionsPlayer = async (axiosInstance: any, playerId: number) => {
    try {
        const response = await axiosInstance.get(
            "/sessions/player?playerId=" + playerId
        );

        if (response) {
            const authResponse = response.data as Session[];

            return authResponse;
        }
        return [];
    } catch (error) {
        console.error(error);
        return [];
    }
};

const getSessionHistoricalInfo = async (
    axiosInstance: any,
    sessionId: number,
    playerId: number
): Promise<SessionHistoricalInfo | null> => {
    try {
        const response = await axiosInstance.get(
            "/sessions/historical-extra-details?sessionId=" +
                sessionId +
                "&playerId=" +
                playerId
        );

        if (response) {
            const authResponse = response.data as SessionHistoricalInfo;
            return authResponse;
        }
        return null;
    } catch (error) {
        console.error(error);
        return null;
    }
};

const getSessionRealTimeInfo = async (
    axiosInstance: any,
    sessionId: number,
    playerId: number
): Promise<SessionRealTimeInfo | null> => {
    try {
        const response = await axiosInstance.get(
            "/sessions/real-time-extra-details?sessionId=" +
                sessionId +
                "&playerId=" +
                playerId
        );

        if (response) {
            const authResponse = response.data as SessionRealTimeInfo;
            return authResponse;
        }
        return null;
    } catch (error) {
        console.error(error);
        return null;
    }
};


export {
    connectPlayerWebSocketRealTimeInfo, 
    getSessionsPlayer,
    getSessionHistoricalInfo,
    getSessionRealTimeInfo,
    getSessionsAllDaysOfYear,
};
export type {
    Session,
    SessionHistoricalInfo,
    SessionRealTimeInfo,
    SessionsAllDaysOfYear,
};
