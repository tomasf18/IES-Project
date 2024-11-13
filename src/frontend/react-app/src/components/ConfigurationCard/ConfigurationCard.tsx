import React from 'react';

interface ConfigurationCardProps {
  widthClass?: string;
  heightClass?: string;
  name: string;
  rightContent: React.ReactNode;
}

export default function ConfigurationCard({ widthClass, heightClass, name, rightContent }: ConfigurationCardProps) {
  return (
    <div className={`flex rounded-xl overflow-hidden shadow-lg border ${widthClass} ${heightClass}`}>
      {/* Left sidebar with gray background */}
      <div className="w-98 bg-gray-300 p-6 flex flex-col justify-center">
        <p className="mt-2 font-normal text-gray-700">
          Configuration
        </p>
        <h4 className="text-xl font-bold text-gray-900">
          {name}
        </h4>
        {/* in the end of the sidebar */}
				<div className="mt-auto justify-end">
					<p className='text-center font-thin'>Configuration</p>
				</div>
      </div>
      
      {/* Main content area with white background */}
      <div className="w-full bg-white m-6">
        <p className="font-normal text-gray-700">
          {rightContent}
        </p>
      </div>
    </div>
  );
}