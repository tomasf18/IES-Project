interface CardProps {
  title: string;
  description: string;
  icon: React.ReactNode;
}

export default function Card({ title, description, icon }: CardProps) {
  return (
    <div className="flex flex-col items-center bg-white shadow-xl p-12 rounded-lg flex-1 w-full md:w-1/3 lg:w-1/4 xl:w-1/5">
      <div className="mb-4">{icon}</div>
      <h3 className="text-xl font-semibold text-gray-700">{title}</h3>
      <p className="text-gray-600 text-center">{description}</p>
    </div>
  );
}
