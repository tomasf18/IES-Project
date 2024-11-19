import React from "react";
import MetricCard from "../MetricCard/MetricCard";
import LineChartComponent from "../LineChart/LineChartComponent";

interface ChartSectionProps {
  bgClass: string;
  icon: React.ReactNode;
  title: string;
  value: number | string;
  unit: string;
  data: { name: number; value: number }[];
  strokeColor: string;
}

const ChartSection: React.FC<ChartSectionProps> = ({
  bgClass,
  icon,
  title,
  value,
  unit,
  data,
  strokeColor,
}) => {
  return (
    <div className={`flex items-center p-4 ${bgClass}`}>
      <MetricCard icon={icon} title={title} value={value} unit={unit} />
      <div className="flex-grow p-4 ml-20">
        {data && data.length > 0 ? (
          <LineChartComponent data={data} strokeColor={strokeColor} />
        ) : (
          <p>No data available</p>
        )}
      </div>
    </div>
  );
};

export default ChartSection;