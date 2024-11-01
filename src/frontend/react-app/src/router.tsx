import { Route, Routes } from "react-router-dom";
import { LandingPage, LoginPage, SignUpPage } from "./pages";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignUpPage />} />
    </Routes>
  );
}
