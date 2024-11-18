import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Rectangle,
  ResponsiveContainer,
} from "recharts";

const data = [
  {
    day: "Oct 11",
    accesses: 4000,
  },
  {
    day: "Oct 12",
    accesses: 2000,
  },
  {
    day: "Oct 13",
    accesses: 2345,
  },
  {
    day: "Oct 14",
    accesses: 680,
  },
  {
    day: "Oct 15",
    accesses: 720,
  }
];

export default function TestPage() {
  return (
    <ResponsiveContainer width={"100%"} height={300}>
      <BarChart
        data={data}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Bar
          dataKey="accesses"
          fill="#B3CDAD"
          activeBar={<Rectangle fill="pink" stroke="blue" />}
        />
      </BarChart>
    </ResponsiveContainer>
  );
}