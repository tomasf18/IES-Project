import React from "react";

interface InfoCardProps {
  icon: React.ReactNode;
  title: string;
  value: number | string;
  unit: string;
}

const MetricCard: React.FC<InfoCardProps> = ({ icon, title, value, unit }) => {
  return (
    <div className="w-1/6 flex flex-col p-4 bg-white rounded-lg shadow-xl">
      <div className="flex items-center">
        {icon}
        <h2 className="text-2xl font-bold text-gray-800 ml-4">{title}</h2>
      </div>
      <h2 className="mt-4 text-3xl font-bold text-gray-800">
        {value} {unit}
      </h2>
    </div>
  );
};

export default MetricCard;