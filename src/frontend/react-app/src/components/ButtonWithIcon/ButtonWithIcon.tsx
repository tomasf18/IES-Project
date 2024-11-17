import { useNavigate } from "react-router-dom";

interface ButtonWithIconProps {
    color: string; // Tailwind color class prefix (e.g., 'green', 'blue', 'red')
}

export default function ButtonWithIcon({ color }: ButtonWithIconProps) {
    const navigate = useNavigate();

    return (
        <button
            onClick={() => navigate("/admin/teams-managing/add-team")}
            className={`flex items-center justify-center w-20 h-14 rounded-lg bg-green-t3 hover:bg-${color}-500 transition duration-300 shadow-md absolute right-14`}
        >
            <div
                className={`flex items-center justify-center w-8 h-8 rounded-full border-2 border-white text-white bg-${color}`}
            >
                <span className="text-xl font-bold">+</span>
            </div>
        </button>
    );
}