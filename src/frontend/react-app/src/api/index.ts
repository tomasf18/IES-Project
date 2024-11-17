export { getSessionsTeam, getTeamSensors, deleteTeamSensorsAssignPlayer, getPlayersWithoutSensor, postSensorPlayer, postSessions, postSessionsAssignPlayer, getTeamPlayersAvailableReaTimeInfo } from "./CoachConsumer";
export type { SensorAssign, PlayersWithoutSensor, Session, RealTimeInfo } from "./CoachConsumer";
export { getTeamMembers, deleteRegistrationCode, deleteUser, refreshRegistrationCode } from "./TeamDirectorConsumer";
export type { TeamMembers } from "./TeamDirectorConsumer";