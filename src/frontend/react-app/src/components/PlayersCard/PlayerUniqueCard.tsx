import {
  AreaChart,
  Area,
  ResponsiveContainer,
  XAxis,
  YAxis,
  Tooltip,
} from "recharts";

interface PlayerCardProps {
  icon?: React.ReactNode;
  playerPhotoURL?: string;
  playerName: string;
  playerId: string;
  singleValue: string;
  actualState: string;
  color: string;
  values: string[];
  metric: string;
  selected: boolean;
  handlePlayerManagement: (playerId: string) => void;
}

export default function PlayersCard({
  icon,
  playerPhotoURL,
  playerName,
  playerId,
  singleValue,
  actualState,
  color,
  values,
  metric,
  selected = false,
  handlePlayerManagement,
}: PlayerCardProps) {
  // Transform values array into data points for the chart
  const chartData = values.map((value, index) => ({
    name: index,
    value: parseFloat(value),
  }));

  // Check if the values array is empty
  const isEmptyValues = values.length === 0;

  return (
    <div
      className={`flex flex-col overflow-hidden rounded-lg shadow-lg p-5 w-64 ${
        actualState === "Critical" ? `bg-${color}-100` : ""
      } ${selected ? "border-2 border-green-t3" : ""}`}
      style={{
        height: isEmptyValues ? "155px" : "auto", // Adjusted height if values array is empty
      }}
      onClick={() => handlePlayerManagement(playerId)}
    >
      <div className="flex items-center mb-4 justify-left">
        {icon ? (
          <div className="w-12">{icon}</div>
        ) : (
          <img
            src={playerPhotoURL}
            alt={`${playerName} logo`}
            className="w-14 h-14 rounded-2xl bg-gray-100 mr-4"
          />
        )}

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
            className={`text-gray-600 text-base px-2 py-1 rounded inline-block w-100`}
            style={{
              backgroundColor: lightenColor(color, 200), // Apply lighter filter by adding opacity
              width: "fit-content",
            }}
          >
            {actualState}
          </div>

          {/* Area chart with gradient fill */}
          <div className="mt-4 w-full h-20">
            <ResponsiveContainer width="100%" height="100%">
              <AreaChart
                data={chartData}
                margin={{ top: 10, right: 0, left: 0, bottom: 0 }}
              >
                <defs>
                  {/* Gradient for diminishing red fill under the curve */}
                  <linearGradient
                    id={`color${color}`}
                    x1="0"
                    y1="0"
                    x2="0"
                    y2="1"
                  >
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

// auxiliary functions to lighten the color of the actualState and convert hex to rgb:
function lightenColor(color: string, amount: number): string {
  const colors: { [key: string]: string } = {
    red: "#FF0000",
    blue: "#0000FF",
    green: "#008000",
  };

  const hex = colors[color];
  if (!hex) return color;

  const [r, g, b] = hexToRgb(hex);
  const newColor = `rgb(${clamp(r + amount)}, ${clamp(g + amount)}, ${clamp(
    b + amount
  )})`;

  return newColor;
}

function hexToRgb(hex: string): [number, number, number] {
  const bigint = parseInt(hex.slice(1), 16);
  const r = (bigint >> 16) & 255;
  const g = (bigint >> 8) & 255;
  const b = bigint & 255;

  return [r, g, b];
}

function clamp(value: number): number {
  return Math.max(0, Math.min(255, value));
}
