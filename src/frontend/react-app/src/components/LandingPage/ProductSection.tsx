import { FaChartLine, FaUsers, FaBullseye, FaRunning } from "react-icons/fa";


export default function ProductSection() {
  return (
    <section className="flex flex-col bg-gray-100 border-2 border-gray-300 items-center p-16 pb-8 relative">
      <div className="container mx-auto flex flex-col lg:flex-row items-center gap-8">
        <div className="flex-1 flex flex-col gap-10 text-center lg:text-left">
          <h1 className="text-2xl md:text-3xl xl:text-4xl font-bold text-gray-800">
            <span>Empowering teams to</span>
            <br />
            <span className="text-green-primary">perform at their best</span>
          </h1>
          <p className="text-gray-600">
            We've reached this milestone by focusing on efficiency,
            collaboration, and innovation.
          </p>
        </div>

        {/* Grid de ícones e textos */}
        <div className="flex-1 grid grid-cols-2 gap-8">
          {/* Item 1 */}
          <div className="flex items-center p-4 bg-white rounded-lg shadow-lg">
            <FaRunning className="text-green-primary text-3xl mr-4" />
            <div>
              <h2 className="text-2xl font-bold text-gray-800">1,245,341</h2>
              <p className="text-gray-600">Athletes Managed</p>
            </div>
          </div>

          {/* Item 2 */}
          <div className="flex items-center p-4 bg-white rounded-lg shadow-lg">
            <FaUsers className="text-green-primary text-3xl mr-4" />
            <div>
              <h2 className="text-2xl font-bold text-gray-800">46,328</h2>
              <p className="text-gray-600">Teams Trained</p>
            </div>
          </div>

          {/* Item 3 */}
          <div className="flex items-center p-4 bg-white rounded-lg shadow-lg">
            <FaChartLine className="text-green-primary text-3xl mr-4" />
            <div>
              <h2 className="text-2xl font-bold text-gray-800">828,867</h2>
              <p className="text-gray-600">Workouts Logged</p>
            </div>
          </div>

          {/* Item 4 */}
          <div className="flex items-center p-4 bg-white rounded-lg shadow-lg">
            <FaBullseye className="text-green-primary text-3xl mr-4" />
            <div>
              <h2 className="text-2xl font-bold text-gray-800">1,926,436</h2>
              <p className="text-gray-600">Daily Metrics Tracked</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
