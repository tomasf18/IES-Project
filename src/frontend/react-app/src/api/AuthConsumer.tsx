interface Auth {
    username: string;
    email: string;
}

const postSignUpValidation = async (axiosInstance: any, auth: Auth) => {
    try {
        const res = await axiosInstance.post("/auth/sign-up/validation", auth);
        return res.data;
    } catch (error) {
        console.log(error);
    }
}

export { postSignUpValidation };
export type { Auth };