import {
  AreaChart,
  Area,
  ResponsiveContainer,
  XAxis,
  YAxis,
  Tooltip,
} from "recharts";

interface LineChartComponentProps {
  data: { name: number; value: number }[];
  strokeColor: string;
  height?: number;
}

const LineChartComponent: React.FC<LineChartComponentProps> = ({
  data,
  strokeColor,
  height = 600,
}) => {
  return (
    <ResponsiveContainer width="85%" height={height}>
      <AreaChart data={data}>
        <defs>
          <linearGradient id={`gradient-${strokeColor}`} x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor={strokeColor} stopOpacity={0.6} />
            <stop offset="95%" stopColor={strokeColor} stopOpacity={0} />
          </linearGradient>
        </defs>
        <XAxis dataKey="name" strokeWidth={2} />
        <YAxis strokeWidth={2} />
        <Tooltip />
        <Area
          type="monotone"
          dataKey="value"
          stroke={strokeColor}
          fill={`url(#gradient-${strokeColor})`} // Use dynamic gradient ID
          fillOpacity={1}
          isAnimationActive={false}
        />
      </AreaChart>
    </ResponsiveContainer>

  );
};

export default LineChartComponent;
