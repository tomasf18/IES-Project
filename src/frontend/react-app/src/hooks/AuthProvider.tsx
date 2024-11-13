import { useContext, createContext, useState, ReactNode } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Cookies from "js-cookie";

interface AuthContextType {
  user: any;
  token: string;
  loginAction: (data: any) => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

const AuthProvider = ({ children }: AuthProviderProps) => {
  const [user, setUser] = useState<any>(null);
  const [token, setToken] = useState<string>(Cookies.get("site") || "");
  const navigate = useNavigate();

  const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api/v1",
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (token) {
    axiosInstance.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  }

  const loginAction = async (data: any) => {
    try {
      const response = await axiosInstance.post("/auth/sign-in", data);
      const res = response.data;
      if (res.data) {
        setUser(res.data.user);
        setToken(res.token);
        Cookies.set("site", res.token, { expires: 15, secure: true, sameSite: 'strict' });
        axiosInstance.defaults.headers.common["Authorization"] = `Bearer ${res.token}`;
        navigate("/"); // TODO: Redirect to the dashboard
        return;
      }
      throw new Error(res.message);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <AuthContext.Provider value={{ user, token, loginAction }}>
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