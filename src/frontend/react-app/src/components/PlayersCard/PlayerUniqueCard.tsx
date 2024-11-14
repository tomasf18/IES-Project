import React from 'react';
import { AreaChart, Area, ResponsiveContainer, XAxis, YAxis, Tooltip } from 'recharts';

interface PlayerCardProps {
    playerPhotoURL: string;
    playerName: string;
    playerId: string;
    singleValue: string;
    actualState: string;
    color: string;
    values: string[];
    metric: string;
    handlePlayerManagement: (playerId: string) => void;
}

export default function PlayersCard({
    playerPhotoURL,
    playerName,
    playerId,
    singleValue,
    actualState,
    color,
    values,
    metric,
    handlePlayerManagement
}: PlayerCardProps) {
    // Transform values array into data points for the chart
    const chartData = values.map((value, index) => ({
        name: index,
        value: parseFloat(value)
    }));

    // Check if the values array is empty
    const isEmptyValues = values.length === 0;

    return (
        <div
            className={`flex flex-col overflow-hidden rounded-lg shadow-lg p-6 w-64 ${
                actualState === "Critical" ? `bg-${color}-100` : ""
            }`}
            style={{
                height: isEmptyValues ? "140px" : "auto", // Adjusted height if values array is empty
            }}
        >
            <div className="flex items-center mb-4 justify-left">
                <img
                    src={playerPhotoURL}
                    alt={`${playerName} logo`}
                    className="w-12 h-14 rounded bg-gray-100 p-2 mr-4"
                    onClick={() => handlePlayerManagement(playerId)}
                />
                <h3 className="text-lg font-bold">{playerName}</h3>
            </div>
            <p className="text-3xl font-semibold">
                {singleValue}
                <span className="text-gray-600 text-base"> {metric} </span>
            </p>
            
            {/* Conditional rendering of actualState and chart */}
            {!isEmptyValues && (
                <>
                    {/* Display actual state with dynamic width */}
                    <div
                        className={`text-gray-600 text-base px-2 py-1 rounded inline-block bg-red-100 w-100`}
                        style={{
                            backgroundColor: color,
                            width: 'fit-content'
                        }}
                    >
                        {actualState}
                    </div>

                    {/* Area chart with gradient fill */}
                    <div className="mt-4 w-full h-20">
                        <ResponsiveContainer width="100%" height="100%">
                            <AreaChart data={chartData} margin={{ top: 10, right: 0, left: 0, bottom: 0 }}>
                                <defs>
                                    {/* Gradient for diminishing red fill under the curve */}
                                    <linearGradient id={`color${color}`} x1="0" y1="0" x2="0" y2="1">
                                        <stop offset="5%" stopColor={color} stopOpacity={0.6} />
                                        <stop offset="95%" stopColor={color} stopOpacity={0} />
                                    </linearGradient>
                                </defs>
                                <XAxis dataKey="name" hide />
                                <YAxis hide />
                                <Tooltip />
                                <Area
                                    type="monotone"
                                    dataKey="value"
                                    stroke={color}
                                    fillOpacity={1}
                                    fill={`url(#color${color})`}
                                />
                            </AreaChart>
                        </ResponsiveContainer>
                    </div>
                </>
            )}
        </div>
    );
}