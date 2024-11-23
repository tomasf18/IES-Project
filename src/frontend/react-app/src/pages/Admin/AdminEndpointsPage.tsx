import { SideBar, Header, StripedTable, Navbar } from "../../components";
import { FaUsers, FaCode, FaHeartPulse } from "react-icons/fa6";
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
import { AreaChart, Area } from 'recharts';
import { useAuth } from "../../hooks";
import { useState } from 'react';


export default function AdminEndpointsPage() {
  const auth = useAuth();

  const data = [
    {
      day: "Oct 11",
      accesses: 4000,
      chartData: Array.from({ length: 24 }, (_, hour) => ({ hour, accesses: Math.floor(Math.random() * 100) })),
    },
    {
      day: "Oct 12",
      accesses: 2000,
      chartData: Array.from({ length: 24 }, (_, hour) => ({ hour, accesses: Math.floor(Math.random() * 100) })),
    },
    {
      day: "Oct 13",
      accesses: 2345,
      chartData: Array.from({ length: 24 }, (_, hour) => ({ hour, accesses: Math.floor(Math.random() * 100) })),
    },
    {
      day: "Oct 14",
      accesses: 680,
      chartData: Array.from({ length: 24 }, (_, hour) => ({ hour, accesses: Math.floor(Math.random() * 100) })),
    },
    {
      day: "Oct 15",
      accesses: 720,
      chartData: Array.from({ length: 24 }, (_, hour) => ({ hour, accesses: Math.floor(Math.random() * 100) })),
    }
  ];

    const [selectedDay, setSelectedDay] = useState(data.length - 1);

    const color = "#82ca9d"; // Highlight color for the selected bar

    const handleBarClick = (index: number): void => {
      setSelectedDay(index);
    };
    

  const navLinks = [
    {
      icon: <FaCode />,
      to: "/admin/endpoints",
      label: "Endpoints",
    },
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
  }[] = [{ to: "#", label: "Sign Out", color: "primary", onClick:() => auth.logOut() }];

  let StripedTableWidthClass = "w-3/4";
  let StripedTableHeightClass = "h-full";
  let StripedTableColumnsName = ["Name", "State", "Accesses"];
  let StripedTableRows = [
    [
      "/admin/endpoints",
      "Critical",
      "10"
    ],
    [
      "/admin/endpoints",
      "Flooded",
      "7"
    ],
    [
      "/admin/endpoints",
      "Normal",
      "1"
    ]
  ];

  return (
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
      <div className="flex-grow p-8 grid grid-cols-10 gap-4">{/* Content */}

        <div className="items-center col-span-4 flex flex-col space-y-2">
          
          <div className="flex flex-col mt-3">
            
            <div className="flex space-x-4">
              <FaChartBar className="text-3xl text-black-600" />
              <h1 className="text-3xl font-bold text-black-800">Total Requests</h1>
            </div>
            
              <p className="text-xl text-gray-600">
              Balance of accesses of the last 5 days
              </p>
              <p className="text-xl text-gray-600">
              in the company
              </p>

                  <>
              {/* BarChart */}
              <ResponsiveContainer width={"100%"} height={350} className="mt-10">
                <BarChart
                  data={data}
                  margin={{
                    top: 5,
                    right: 30,
                    left: 20,
                    bottom: 5,
                  }}
                >
                  <XAxis dataKey="day" />
                  <Tooltip cursor={{fill: 'transparent'}}/>
                  <Bar dataKey="accesses" >
                    {data.map((_, index) => (
                      <Cell
                        key={`cell-${index}`}
                        fill={index === selectedDay ? color : "#d3d3d3"}
                        onClick={() => handleBarClick(index)}
                      />
                    ))}
                  </Bar>


                </BarChart>
              </ResponsiveContainer>

              {/* AreaChart for the Selected Day */}

              {selectedDay !== null && (
                <div className={`mt-10 flex flex-col overflow-hidden rounded-lg shadow-lg p-6 w-full bg-green-50 justify-center`}>

                  {/* Title */}
                  <h3 className="text-xl font-semibold text-center mb-4">
                    Daily Comparison - Number of accesses per hour
                  </h3>
                  
                  <div className=" w-full h-80">
                    <ResponsiveContainer width="100%" height="100%">
                        <AreaChart
                          data={data[selectedDay].chartData}
                          margin={{ top: 10, right: 0, left: 0, bottom: 0 }}
                        >
                          <defs>
                            {/* Gradient for diminishing color fill under the curve */}
                            <linearGradient id={`color${selectedDay}`} x1="0" y1="0" x2="0" y2="1">
                              <stop offset="5%" stopColor={color} stopOpacity={0.6} />
                              <stop offset="95%" stopColor={color} stopOpacity={0} />
                            </linearGradient>
                          </defs>
                          <XAxis dataKey="hour" tick={false} label={{ value: "Hour of the day", position: "insideBottomRight" }} />
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

        <div className="col-span-6">
          <Navbar options={['GET', 'POST', 'PUT', 'DELETE']} color="purple" size='w-1/3'/>
          <div className="flex p-8 justify-center items-center mt-4">
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
  </div>
);

}

