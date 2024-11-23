import { useState } from 'react';

interface NavbarProps {
    options: string[];
    color: string; // Tailwind color class prefix (e.g., 'purple', 'blue', 'red')
    size: string; // Tailwind width class (e.g., 'w-1/4', 'w-1/2', 'w-full')
}

export default function Navbar({ options, color, size }: NavbarProps) {
    const [activeOption, setActiveOption] = useState(options[0]);

    const handleOptionClick = (option: string) => {
        setActiveOption(option);
    };

    return (
        <div className={`flex justify-around bg-${color}-50 py-2 border-b-2 border-gray-200 ${size} mx-auto mt-4 rounded-lg shadow-md`}>
            {options.map((option) => (
                <div
                    key={option}
                    onClick={() => handleOptionClick(option)}
                    className={`text-lg font-semibold cursor-pointer ${
                        activeOption === option ? `text-${color}-700` : 'text-gray-600'
                    }`}
                    style={{ position: 'relative' }}
                >
                    {option}
                    {activeOption === option && (
                        <div
                            className={`absolute bottom-0 left-0 right-0 h-1 bg-${color}-700`}
                            style={{ width: '100%' }}
                        />
                    )}
                </div>
            ))}
        </div>
    );
}
