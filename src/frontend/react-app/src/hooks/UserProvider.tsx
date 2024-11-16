import { createContext, useContext, useState, ReactNode } from "react";

interface UserContextType {
  userId: number;
  name: string;
  username: string;
  email: string;
  profilePictureUrl: string;
  roles: string[];
  setUser: (user: User | null) => void;
}

interface User {
  userId: number;
  name: string;
  username: string;
  email: string;
  profilePictureUrl: string;
  roles: string[];
}

const UserContext = createContext<UserContextType | undefined>(undefined);

interface UserProviderProps {
  children: ReactNode;
}

const UserProvider = ({ children }: UserProviderProps) => {
  const [user, setUser] = useState<User | null>(null);

  return (
    <UserContext.Provider value={{ userId: user?.userId ?? 0, name: user?.name ?? '', username: user?.username ?? '', email: user?.email ?? '', profilePictureUrl: user?.profilePictureUrl ?? '', roles: user?.roles ?? [], setUser }}>
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