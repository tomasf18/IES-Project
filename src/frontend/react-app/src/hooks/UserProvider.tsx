import { createContext, useContext, useState, ReactNode } from "react";
import { useNavigate } from "react-router-dom";

interface UserContextType {
  userId: number;
  name: string;
  username: string;
  email: string;
  profilePictureUrl: string;
  teamId: number;
  roles: string[];
  setUser: (user: User | null) => void;
  redirectHomeByUserType: () => void;
}

interface User {
  userId: number;
  name: string;
  username: string;
  email: string;
  profilePictureUrl: string;
  teamId: number;
  roles: string[];
}

const UserContext = createContext<UserContextType | undefined>(undefined);

interface UserProviderProps {
  children: ReactNode;
}

const UserProvider = ({ children }: UserProviderProps) => {
  const [user, setUser] = useState<User | null>(null);
  const navigate = useNavigate();

  const redirectHomeByUserType = () => {
    // navigate by user type
    if (user?.roles.includes("ROLE_ADMIN")) navigate("/admin/endpoints");
    else if (user?.roles.includes("ROLE_player")) navigate("/player/home");
    else if (user?.roles.includes("ROLE_COACH")) navigate("/coach/sessions");
    else if (user?.roles.includes("ROLE_PERSONAL_TRAINER")) navigate("/personal-trainer/start-session");
    else if (user?.roles.includes("ROLE_TEAM_DIRECTOR")) navigate("/team-director/team-managing");
    else navigate("/login");
  };


  return (
    <UserContext.Provider value={{ userId: user?.userId ?? 0, name: user?.name ?? '', username: user?.username ?? '', email: user?.email ?? '', profilePictureUrl: user?.profilePictureUrl ?? '', teamId: user?.teamId ?? 0, roles: user?.roles ?? [], setUser, redirectHomeByUserType }}>
      {children}
    </UserContext.Provider>
  );
};

const useUser = () => {
  const context = useContext(UserContext);
  if (context === undefined) {
    throw new Error("useUser must be used within a UserProvider");
  }
  return context;
};

export { UserProvider, useUser };