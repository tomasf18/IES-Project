import { BrowserRouter } from "react-router-dom";
import AppRoutes from "./router";
import { AuthProvider } from "./hooks/AuthProvider";

export default function App() {
  return (
    <BrowserRouter>
      <AuthProvider> {/* Authentication Provider for JWT */}
        <AppRoutes />
      </AuthProvider>
    </BrowserRouter>
  );
}
