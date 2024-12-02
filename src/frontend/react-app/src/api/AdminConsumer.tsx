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

const getSensorsTeamWeek = async (axiosInstance: any) => {
  try {
    const response = await axiosInstance.get(
      "/administrator/sensors-team-weak"
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
    const response = await axiosInstance.delete("/team" + "?teamId=" + teamId);

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
