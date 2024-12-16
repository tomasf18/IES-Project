import { SideBar, Header, StripedTable } from "../../components";
import { FaUsers, FaHeartPulse } from "react-icons/fa6";
import { FaChartBar } from "react-icons/fa";
import { useLocation } from "react-router-dom";
import {
    BarChart,
    Bar,
    XAxis,
    Tooltip,
    ResponsiveContainer,
    Cell,
} from "recharts";
import { AreaChart, Area } from "recharts";
import { useAuth } from "../../hooks";
import { useEffect, useState } from "react";
import { format } from "date-fns";
import {
    getSensorsTeamWeek,
    getSensorsLast5Days,
    getSensorsDay,
    SensorsTeamWeek,
    SensorsLast5Days,
    connectWebSocketSensorsTeamWeek,
    connectWebSocketSensorsLast5Days,
    connectWebSocketRealTimeSensorsDay,
} from "../../api";
import { useRef } from "react";

export default function AdminSensorsTrackingPage() {
    const auth = useAuth();
    const [sensorsTeamWeek, setSensorsTeamWeek] = useState<SensorsTeamWeek[]>(
        []
    );
    const [sensorsLast5Days, setSensorsLast5Days] = useState<
        SensorsLast5Days[]
    >([]);
    const [sensorsDay, setSensorsDay] = useState<
        { date: string; accesses: unknown }[]
    >([]);
    const [selectedDay, setSelectedDay] = useState<number | null>(null);
    const [loading, setLoading] = useState(true);

    const cleanupWebSocketRef = useRef<(() => void) | null | undefined>(null);

    useEffect(() => {
        const fetchDataAndConnectWebSocket = async () => {
            if (selectedDay === null || !sensorsLast5Days[selectedDay]) return;
    
            const selectedDate = sensorsLast5Days[selectedDay].date;
    
            // Fetch daily data
            try {
                const response = await getSensorsDay(auth.axiosInstance, selectedDate);
                if (response) {
                    const formattedData = Object.entries(response).map(([date, accesses]) => ({
                        date,
                        accesses,
                    }));
                    setSensorsDay(formattedData);
                }
            } catch (error) {
                console.error("Error fetching sensors day:", error);
            }
    
            // Cleanup existing WebSocket if it exists
            if (cleanupWebSocketRef.current) {
                cleanupWebSocketRef.current();
                cleanupWebSocketRef.current = null;
            }
    
            // Connect WebSocket only if selectedDay is the last day (real-time data)
            if (selectedDay === sensorsLast5Days.length - 1) {
                console.log("Connecting WebSocket for sensorsDay...");
                cleanupWebSocketRef.current = await connectWebSocketRealTimeSensorsDay(
                    setSensorsDay
                ) || null; // Fallback to null if undefined is returned
            }
        };
    
        fetchDataAndConnectWebSocket();
    
        return () => {
            // Cleanup WebSocket on unmount or before re-running
            if (cleanupWebSocketRef.current) {
                console.log("Cleaning up WebSocket connection...");
                cleanupWebSocketRef.current();
                cleanupWebSocketRef.current = null;
            }
        };
    }, [selectedDay, auth.axiosInstance]);
    

    useEffect(() => {
        const fetchAndConnectWebSocketSensorsLast5Days = async () => {
            setLoading(true);

            // Fetch initial data
            await getSensorsLast5Days(auth.axiosInstance)
                .then((response) => {
                    if (response) {
                        setSensorsLast5Days(response);
                        setSelectedDay(response.length - 1); // Only update if response changes
                    }
                })
                .catch((error) => {
                    console.error("Error fetching sensors last 5 days:", error);
                });

            setLoading(false);

            // Connect WebSocket for real-time updates
            console.log("Connecting WebSocket for sensorsLast5Days...");
            const cleanupWebSocket = await connectWebSocketSensorsLast5Days(
                setSensorsLast5Days
            );

            return cleanupWebSocket;
        };

        const cleanupWebSocketPromise =
            fetchAndConnectWebSocketSensorsLast5Days();

        return () => {
            cleanupWebSocketPromise.then((cleanupWebSocket) => {
                console.log("Cleaning up WebSocket connection...");
                cleanupWebSocket?.();
            });
        };
    }, [auth.axiosInstance]); 

    useEffect(() => {
        const fetchAndConnectWebSocketSensorsTeamWeek = async () => {
            setLoading(true);

            // Fetch initial weekly data
            await getSensorsTeamWeek(auth.axiosInstance)
                .then((response) => {
                    if (response) {
                        setSensorsTeamWeek(response);
                    }
                })
                .catch((error) => {
                    console.error("Error fetching team sensors:", error);
                });

            setLoading(false);

            // Connect WebSocket for real-time updates
            console.log("Connecting WebSocket for sensorsTeamWeek...");
            const cleanupWebSocket = await connectWebSocketSensorsTeamWeek(
                setSensorsTeamWeek
            );

            return cleanupWebSocket;
        };

        const cleanupWebSocketPromise =
            fetchAndConnectWebSocketSensorsTeamWeek();

        return () => {
            cleanupWebSocketPromise.then((cleanupWebSocket) => {
                console.log("Cleaning up WebSocket connection...");
                cleanupWebSocket?.();
            });
        };
    }, [auth.axiosInstance]); // Only depends on `auth.axiosInstance`

    /* -------------------------------------------------------------------------------- */

    // Fetch Sensors Last 5 Days data

    const formattedSensorsLast5Days = sensorsLast5Days.map((item) => ({
        ...item,
        formattedDate: format(new Date(item.date), "MMM dd"),
    }));

    const color = "#82ca9d"; // Highlight color for the selected bar

    const navLinks = [
        {
            icon: <FaHeartPulse />,
            to: "/admin/sensors-tracking",
            label: "Sensors Tracking",
        },
        {
            icon: <FaUsers />,
            to: "/admin/teams-managing",
            label: "Teams Managing",
        },
    ];

    const location = useLocation();

    const headerButtons: {
        to: string;
        label: string;
        color: "primary" | "secondary";
        onClick?: () => void;
    }[] = [
        {
            to: "#",
            label: "Sign Out",
            color: "primary",
            onClick: () => auth.logOut(),
        },
    ];

    let StripedTableWidthClass = "w-3/4";
    let StripedTableHeightClass = "h-full";
    let StripedTableColumnsName = ["Name", "State", "Values"];

    const getStateColor = (state: string) => {
        switch (state.toLowerCase()) {
            case "critical":
                return "bg-red-primary"; // red
            case "flooded":
                return "bg-yellow-500"; // yellow
            case "normal":
                return "bg-green-t1"; // green
            default:
                return "bg-gray-500";
        }
    };

    let StripedTableRows = sensorsTeamWeek.map((sensor) => [
        sensor.teamName,
        <div className="flex items-center justify-center space-x-2">
            <span
                className={`w-3 h-3 rounded-full ${getStateColor(
                    sensor.state
                )}`}
            />
            <span>{sensor.state}</span>
        </div>,
        sensor.value,
    ]);

    return loading ? (
        <div className="flex justify-center items-center h-screen">
            <div className="animate-spin rounded-full h-32 w-32 border-t-2 border-b-2 border-gray-900"></div>
        </div>
    ) : (
        <div className="flex flex-col min-h-screen">
            <Header buttons={headerButtons} />
            <div className="flex flex-grow">
                <SideBar
                    navLinks={navLinks}
                    width="w-62"
                    isLargeBar={true}
                    activePath={location.pathname}
                />
                {/* Main Content */}
                <div className="flex-grow p-8 grid grid-cols-10 gap-4">
                    {/* Content */}

                    <div className="items-center col-span-4 flex flex-col space-y-2">
                        <div className="flex flex-col mt-3">
                            <div className="flex space-x-4">
                                <FaChartBar className="text-3xl text-black-600" />
                                <h1 className="text-3xl font-bold text-black-800">
                                    Total Sensors Data
                                </h1>
                            </div>

                            <p className="text-xl text-gray-600">
                                Balance of sensors data of the last 5 days
                            </p>
                            <p className="text-xl text-gray-600">
                                in the company
                            </p>

                            <>
                                {/* BarChart */}
                                <ResponsiveContainer
                                    width={"100%"}
                                    height={350}
                                    className="mt-10"
                                >
                                    <BarChart
                                        data={formattedSensorsLast5Days}
                                        margin={{
                                            top: 5,
                                            right: 30,
                                            left: 20,
                                            bottom: 5,
                                        }}
                                    >
                                        <XAxis dataKey="formattedDate" />
                                        <Tooltip
                                            cursor={{ fill: "transparent" }}
                                        />
                                        <Bar dataKey="value">
                                            {formattedSensorsLast5Days.map(
                                                (_, index) => (
                                                    <Cell
                                                        key={`cell-${index}`}
                                                        fill={
                                                            index ===
                                                            selectedDay
                                                                ? color
                                                                : "#d3d3d3"
                                                        }
                                                        onClick={() =>
                                                            setSelectedDay(
                                                                index
                                                            )
                                                        }
                                                    />
                                                )
                                            )}
                                        </Bar>
                                    </BarChart>
                                </ResponsiveContainer>

                                {/* AreaChart for the Selected Day */}

                                {selectedDay !== null && (
                                    <div
                                        className={`mt-10 flex flex-col overflow-hidden rounded-lg shadow-lg p-6 w-full bg-green-50 justify-center`}
                                    >
                                        {/* Title */}
                                        <h3 className="text-xl font-semibold text-center mb-4">
                                            Daily Comparison - Number of
                                            accesses
                                        </h3>

                                        <div className=" w-full h-80">
                                            <ResponsiveContainer
                                                width="100%"
                                                height="100%"
                                            >
                                                <AreaChart
                                                    data={sensorsDay}
                                                    margin={{
                                                        top: 10,
                                                        right: 0,
                                                        left: 0,
                                                        bottom: 0,
                                                    }}
                                                >
                                                    <defs>
                                                        {/* Gradient for diminishing color fill under the curve */}
                                                        <linearGradient
                                                            id={`color${selectedDay}`}
                                                            x1="0"
                                                            y1="0"
                                                            x2="0"
                                                            y2="1"
                                                        >
                                                            <stop
                                                                offset="5%"
                                                                stopColor={
                                                                    color
                                                                }
                                                                stopOpacity={
                                                                    0.6
                                                                }
                                                            />
                                                            <stop
                                                                offset="95%"
                                                                stopColor={
                                                                    color
                                                                }
                                                                stopOpacity={0}
                                                            />
                                                        </linearGradient>
                                                    </defs>
                                                    <XAxis
                                                        dataKey="hour"
                                                        tick={false}
                                                        label={{
                                                            value: "Hour of the day",
                                                            position:
                                                                "insideBottomRight",
                                                        }}
                                                    />
                                                    <Tooltip />
                                                    <Area
                                                        type="monotone"
                                                        dataKey="accesses"
                                                        stroke={color}
                                                        fillOpacity={1}
                                                        fill={`url(#color${selectedDay})`}
                                                    />
                                                </AreaChart>
                                            </ResponsiveContainer>
                                        </div>
                                    </div>
                                )}
                            </>
                        </div>
                    </div>

                    <div className="col-span-6 max-h-[45rem] overflow-y-auto">
                        <StripedTable
                            widthClass={StripedTableWidthClass}
                            heightClass={StripedTableHeightClass}
                            columnsName={StripedTableColumnsName}
                            rows={StripedTableRows}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

