import { Route, Routes } from "react-router-dom";
import {
  LandingPage,
  LoginPage,
  SignUpPage,
  TestPage,
  PlayerHomePage,
  AdminEndpointsPage,
  AdminSensorsTrackingPage,
  AdminTeamsManagingPage,
  TeamDirectorTeamManagingPage,
  TeamDirectorSensorsManagingPage,
  CoachSessionsPage,
  CoachStartMatchPage,
  CoachSensorsPage,
  PersonalTrainerStartSessionPage,
  PersonalTrainerSensorsPage,
} from "./pages";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignUpPage />} />
      <Route path="/test" element={<TestPage />} />
      {/* Player Routes Group */}

      <Route path="/player">
        <Route path="home" element={<PlayerHomePage />} />
      </Route>
      {/* Admin Routes Group */}
      <Route path="/admin">
        <Route path="endpoints" element={<AdminEndpointsPage />} />
        <Route path="sensors-tracking" element={<AdminSensorsTrackingPage />} />
        <Route path="teams-managing" element={<AdminTeamsManagingPage />} />
      </Route>
      {/* Team Director Routes Group */}
      <Route path="/team-director">
        <Route path="team-managing" element={<TeamDirectorTeamManagingPage />} />
        <Route path="sensors-managing" element={<TeamDirectorSensorsManagingPage />} />
      </Route>
      {/* Coach Routes Group */}
      <Route path="/coach">
        <Route path="sessions" element={<CoachSessionsPage />} />
        <Route path="start-match" element={<CoachStartMatchPage />} />
        <Route path="sensors" element={<CoachSensorsPage />} />
      </Route>
      {/* Personal Trainer Routes Group */}
      <Route path="/personal-trainer">
        <Route path="start-session" element={<PersonalTrainerStartSessionPage />} />
        <Route path="sensors" element={<PersonalTrainerSensorsPage/>} />
      </Route>
    </Routes>
  );
}
