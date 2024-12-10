export {
    getSessionInfo,
    getSessionsTeam,
    postMatch,
    getTeamSensors,
    deleteTeamSensorsAssignPlayer,
    getPlayersWithoutSensor,
    postSensorPlayer,
    postSessions,
    postSessionsAssignPlayer,
    getTeamPlayersAvailableReaTimeInfo,
    getSessionRealTimeData,
    getBySessionHistoricalData,
    endSession,
    getBySessionRealTimeData,
    connectWebSocketRealTimeData,
    connectWebSocketRealTimeInfo,
} from "./CoachConsumer";
export type {
    SensorAssign,
    PlayersWithoutSensor,
    Session,
    RealTimeInfo,
    SessionRealTimeData,
    SessionHistoricalData,
    SessionInfo,
} from "./CoachConsumer";
export {
    getTeamMembers,
    deleteRegistrationCode,
    deleteUser,
    refreshRegistrationCode,
    changeProfilePictureUrl,
} from "./TeamDirectorConsumer";
export type { TeamMembers } from "./TeamDirectorConsumer";
export {
    getSessionsPlayer,
    getSessionHistoricalInfo,
    getSessionRealTimeInfo,
    getSessionsAllDaysOfYear,
    connectPlayerWebSocketRealTimeInfo,
} from "./PlayerConsumer";
export type {
    SessionHistoricalInfo,
    SessionRealTimeInfo,
    SessionsAllDaysOfYear,
} from "./PlayerConsumer";
export {
    connectWebSocketRealTimeSensorsDay,
    connectWebSocketSensorsLast5Days,
    connectWebSocketSensorsTeamWeek,
    addTeamSensor,
    deleteTeamSensor,
    getTeamsInfo,
    createTeam,
    getTeamDirectors,
    addTeamDirector,
    deleteTeam,
    getSensorsTeamWeek,
    getSensorsLast5Days,
    getSensorsDay,
} from "./AdminConsumer";
export type {
    TeamDirectors,
    SensorsTeamWeek,
    SensorsLast5Days,
} from "./AdminConsumer";
export { postSignUpValidation } from "./AuthConsumer";
export type { Auth } from "./AuthConsumer";
