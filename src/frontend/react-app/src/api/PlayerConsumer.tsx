interface Session {
  sessionName: string;
  sessionId: number;
  startTime: string;
  endTime: string;
  numParticipants: number;
  state: string;
  isMatch: boolean;
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


export { getSessionsPlayer };
export type { Session };