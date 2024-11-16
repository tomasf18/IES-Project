import { useNavigate } from "react-router-dom";
import axios from "axios";
import Cookies from "js-cookie";
import { createContext, useContext, useState, ReactNode } from "react";
import { useUser } from "./UserProvider";

interface AuthContextType {
  token: string;
  loginAction: (data: AuthRequestProps) => Promise<string|undefined>
  logOut: () => void;
  signUpAction: (data: SignUpRequestProps) => Promise<string|undefined>;
  authMe: () => void;
}

interface AuthRequestProps {
  usernameOrEmail: string;
  password: string;
}

interface SignUpRequestProps {
  email: string;
  username: string;
  password: string;
}

interface AuthResponseProps {
  token: string;
  userId: number;
  name: string;
  username: string;
  email: string;
  profilePictureUrl: string;
  teamId: number;
  roles: string[];
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

const AuthProvider = ({ children }: AuthProviderProps) => {
  const tokenName = "sts_token";
  const [token, setToken] = useState<string>(Cookies.get(tokenName) || "");
  const navigate = useNavigate();
  const user = useUser();

  const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api/v1",
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (token) {
    axiosInstance.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  }

  const signUpAction = async (data: SignUpRequestProps) => {
    try {
      const response = await axiosInstance.post("/auth/sign-up", data);
      if (response) {
        console.log(response);
        navigate("/login"); // TODO: Redirect to the dashboard
      }
    } catch (error) {
      console.error(error);
      return "Sign up failed";
    }
  }

  const loginAction = async (data: any) => {
    try {
      const response = await axiosInstance.post("/auth/sign-in", data);
      if (response) {
        const authResponse = response.data as AuthResponseProps;

        setToken(authResponse.token);
        Cookies.set(tokenName, authResponse.token, { expires: 15, secure: false, sameSite: 'strict' });
        axiosInstance.defaults.headers.common["Authorization"] = `Bearer ${authResponse.token}`;
        user.setUser({
          userId: authResponse.userId,
          name: authResponse.name,
          username: authResponse.username,
          email: authResponse.email,
          profilePictureUrl: authResponse.profilePictureUrl,
          teamId: authResponse.teamId,
          roles: authResponse.roles,
        });
      }
    } catch (error) {
      console.error(error);
      return "Password or username is incorrect";
    }
  };

  const logOut = () => {
    user.setUser(null);
    setToken("");
    Cookies.remove(tokenName);
    navigate("/login");
    console.log("Logged out");
  };

  const authMe = async () => {
    try {
      const response = await axiosInstance.get("/auth/me");
      if (response) {
        const authResponse = response.data as AuthResponseProps;
        user.setUser({
          userId: authResponse.userId,
          name: authResponse.name,
          username: authResponse.username,
          email: authResponse.email,
          profilePictureUrl: authResponse.profilePictureUrl,
          teamId: authResponse.teamId,
          roles: authResponse.roles,
        });
      }
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <AuthContext.Provider value={{ token, loginAction, logOut, signUpAction, authMe }}>
      {children}
    </AuthContext.Provider>
  );
};

const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

export { AuthProvider, useAuth };
