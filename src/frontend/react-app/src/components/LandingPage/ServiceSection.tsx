import { Card } from "../../components";
import { FaUser } from "react-icons/fa";
import { GiSoccerBall } from "react-icons/gi";
import { MdOutlineSports } from "react-icons/md";

export default function ServiceSection() {
  return (
    <section className="flex flex-col items-center p-16 space-y-8">
      <h2 className="text-3xl md:text-4xl font-bold text-gray-800 text-center">
        <span>Manage your entire team</span>
        <br />
        <span>performance in a single system</span>
      </h2>
      <p className="text-gray-600 text-center max-w-lg">
        Who is Smart Training System suitable for?
      </p>

      <div className="flex flex-col md:flex-row justify-center gap-8 w-full max-w-6xl">
        <Card
          icon={
            <MdOutlineSports className="text-3xl text-green-primary mb-2" />
          }
          title="Coaches"
          description="Get real-time stats and analysis to optimize strategies and boost player performance"
        />
        <Card
          icon={<GiSoccerBall className="text-3xl text-green-primary mb-2" />}
          title="Players"
          description="Track your stats and progress to set goals and improve performance"
        />
        <Card
          icon={<FaUser className="text-3xl text-green-primary mb-2" />}
          title="Personal Trainers"
          description="Monitor metrics and personalize workouts for better training results"
        />
      </div>
    </section>
  );
}
