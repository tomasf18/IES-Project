import { Route, Routes } from "react-router-dom";
import { LandingPage, LoginPage, SignUpPage, TestPage } from "../pages";
import PrivateRoute from "./PrivateRoute";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignUpPage />} />
      <Route element={<PrivateRoute/>}>
        <Route path="/test" element={<TestPage />} />
      </Route>
    </Routes>
  );
}
