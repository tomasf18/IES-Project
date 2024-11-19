interface TeamMembers {
  id: number;
  name: string;
  profilePictureUrl: string;
  userTypeId: number;
  registrationCode: string;
}

const getTeamMembers = async (axiosInstance: any, teamId: number) => {
  try {
    const response = await axiosInstance.get(
      "/team/team-members?teamId=" + teamId
    );

    if (response) {
      const authResponse = response.data as TeamMembers[];

      return authResponse;
    }
    return [];
  } catch (error) {
    console.error(error);
    return [];
  }
};

const changeProfilePictureUrl = async (
  axiosInstance: any,
  userId: number,
  profilePictureUrl: string
) => {
  try {
    const response = await axiosInstance.put(
      "/users?userId=" + userId + "&profilePictureUrl=" + profilePictureUrl
    );

    if (response) {
      console.log(response);
    }
  } catch (error) {
    console.error(error);
  }
};

const deleteRegistrationCode = async (axiosInstance: any, code: string) => {
  try {
    console.log("code: " + code);
    const response = await axiosInstance.delete("/team/registration-code", {
      data: {
        code: code,
      },
    });

    if (response) {
      console.log(response);
    }
  } catch (error) {
    console.error(error);
  }
};

const refreshRegistrationCode = async (axiosInstance: any, code: string) => {
  try {
    console.log("code: " + code);
    const response = await axiosInstance.put(
      "/team/registration-code/refresh",
      {
        code: code,
      }
    );

    if (response) {
      console.log(response);
      return response.data.code;
    }
  } catch (error) {
    console.error(error);
  }
};

const deleteUser = async (axiosInstance: any, userId: number) => {
  try {
    console.log("userId: " + userId);
    const response = await axiosInstance.delete("/users?userId=" + userId);

    if (response) {
      console.log(response);
    }
  } catch (error) {
    console.error(error);
  }
};

export {
  getTeamMembers,
  deleteRegistrationCode,
  deleteUser,
  refreshRegistrationCode,
  changeProfilePictureUrl,
};
export type { TeamMembers };
