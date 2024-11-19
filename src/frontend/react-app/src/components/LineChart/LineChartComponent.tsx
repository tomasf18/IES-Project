import React from "react";
import {
  LineChart,
  ResponsiveContainer,
  XAxis,
  YAxis,
  Tooltip,
  Line,
} from "recharts";

interface LineChartComponentProps {
  data: { name: number; value: number }[];
  strokeColor: string;
  height?: number;
}

const LineChartComponent: React.FC<LineChartComponentProps> = ({
  data,
  strokeColor,
  height = 700,
}) => {
  return (
    <ResponsiveContainer width="100%" height={height}>
      <LineChart data={data}>
        <XAxis dataKey="name" strokeWidth={2} />
        <YAxis strokeWidth={2} />
        <Tooltip />
        <Line
          type="monotone"
          dataKey="value"
          stroke={strokeColor}
          strokeWidth={3}
          dot={false}
        />
      </LineChart>
    </ResponsiveContainer>
  );
};

export default LineChartComponent;
