import { Button } from "../../components";
import { HomeSectionImage } from "../../assets";

export default function HomeSection() {
  return (
    <section className="flex flex-col items-center bg-gray-100 p-16 pb-8 relative">
      <div className="container mx-auto flex flex-col lg:flex-row items-center gap-8">
        <div className="flex-1 flex flex-col gap-10 text-center lg:text-left">
          <h1 className="text-4xl md:text-5xl xl:text-6xl font-bold text-gray-800">
            <span>Reach your full</span>
            <br />
            <span>potential</span>{" "}
            <span className="text-green-primary">analyzing</span>
            <br />
            <span className="text-green-primary">real-time data</span>
          </h1>
          <p className="text-gray-600">
            Where to grow your players' performance: history and real-time?
          </p>
          <div className="w-1/2">
            <Button color="primary">Register a Team</Button>
          </div>
        </div>
        <div className="flex-1 flex justify-center items-center">
          <img
            src={HomeSectionImage}
            alt="Data Graph Illustration"
            className="max-w-sm md:max-w-md lg:max-w-lg object-contain"
          />
        </div>
      </div>
      <div className="w-1/12 h-1 bg-green-primary mt-16" />
    </section>
  );
}
