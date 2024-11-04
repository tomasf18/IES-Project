import { Button } from "../../components";
import { FeatureSectionImage } from "../../assets";

export default function FeatureSection() {
  return (
    <section className="flex flex-col items-center p-16 pb-8 relative">
      <div className="container mx-auto flex flex-col lg:flex-row items-center gap-8">
        <div className="flex-1 flex flex-col gap-10 text-center lg:text-left">
          <h1 className="text-2xl md:text-3xl xl:text-4xl font-bold text-gray-800">
            <span>What</span>{" "}
            <span className="text-green-primary">performance data</span>{" "}
            <span>do we</span>
            <br />
            <span>track in real-time?</span>
          </h1>
          <p className="text-gray-600">
            Our platform gathers vital performance metrics using wearable
            sensors, tracking heart rate, fatigue, and player workload
            throughout training and matches. Additionally, we provide real-time
            sports data, such as ball possession, shots on target, and player
            positioning, offering a complete view of your team's performance on
            the field. These insights, available instantly, allow coaches to
            make informed decisions, adjust strategies, and enhance both
            individual and team performance.
          </p>
          <div className="w-1/2">
            <Button color="primary">Learn More</Button>
          </div>
        </div>
        <div className="flex-1 flex justify-center items-center">
          <img
            src={FeatureSectionImage}
            alt="Data Graph Illustration"
            className="max-w-sm md:max-w-md lg:max-w-lg object-contain"
          />
        </div>
      </div>
    </section>
  );
}
