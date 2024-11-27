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
      "/player/sessions/all-days-of-year?playerId=" + playerId + "&year=" + year
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
}

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
}

export { getSessionsPlayer, getSessionHistoricalInfo, getSessionRealTimeInfo, getSessionsAllDaysOfYear };
export type { Session, SessionHistoricalInfo, SessionRealTimeInfo, SessionsAllDaysOfYear };
