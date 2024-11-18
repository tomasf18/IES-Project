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

export { getSessionsPlayer, getSessionHistoricalInfo };
export type { Session, SessionHistoricalInfo };
