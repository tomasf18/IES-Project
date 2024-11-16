import React, { useEffect, useState } from 'react';
import PlayersCard from "../components/PlayersCard/PlayersCard";
import { Google } from "../assets";

export default function TestPage() {
  const [activeOption, setActiveOption] = useState('GET');

    const handleOptionClick = (option: string) => {
        setActiveOption(option);
    };

    return (
        <div className="flex justify-around bg-purple-50 py-2 border-b-2 border-gray-200 w-1/4 mx-auto mt-4 rounded-lg shadow-md">
            {['GET', 'POST', 'PUT', 'DELETE'].map((option) => (
                <div
                    key={option}
                    onClick={() => handleOptionClick(option)}
                    className={`text-lg font-semibold cursor-pointer ${
                        activeOption === option ? 'text-purple-700' : 'text-gray-600'
                    }`}
                    style={{ position: 'relative' }}
                >
                    {option}
                    {activeOption === option && (
                        <div
                            className="absolute bottom-0 left-0 right-0 h-1 bg-purple-700"
                            style={{ width: '100%' }}
                        />
                    )}
                </div>
            ))}
        </div>
    );
}
