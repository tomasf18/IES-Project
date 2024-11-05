import { Button } from "../../components";
import { SecuritySectionImage } from "../../assets";

export default function SecuritySection() {
  return (
    <section className="flex flex-col items-center p-16 pb-8 relative">
      <div className="container mx-auto flex flex-col lg:flex-row-reverse items-center gap-8">
        <div className="flex-1 flex flex-col gap-10 text-center lg:text-left">
          <h1 className="text-2xl md:text-3xl xl:text-4xl font-bold text-gray-800">
            <span>How we ensure the security of</span>
            <br />
            <span>your performance data?</span>
          </h1>
          <p className="text-gray-600">
            At our core, we prioritize the protection of your team's performance
            data. We use advanced encryption protocols, secure storage
            solutions, and regular system audits to safeguard your information.
            Our platform complies with industry standards to ensure data privacy
            and integrity, allowing you to focus on improving performance
            without concerns about data security. Your trust is our top
            priority.
          </p>
          <div className="w-1/2">
            <Button color="primary">Learn More</Button>
          </div>
        </div>
        <div className="flex-1 flex justify-center items-center">
          <img
            src={SecuritySectionImage}
            alt="Data Graph Illustration"
            className="max-w-sm md:max-w-md lg:max-w-lg object-contain"
          />
        </div>
      </div>
    </section>
  );
}
