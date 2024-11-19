import { BrowserRouter } from "react-router-dom";
import AppRoutes from "./routers/router";
import { AuthProvider } from "./hooks/AuthProvider";
import { UserProvider } from "./hooks/UserProvider";

export default function App() {
  return (
    <BrowserRouter>
      <UserProvider> {/* User Provider for User Data */}
        <AuthProvider> {/* Authentication Provider for JWT */}
          <AppRoutes />
        </AuthProvider>
      </UserProvider>
    </BrowserRouter>
  );
}
