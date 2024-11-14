import { FaSignOutAlt } from "react-icons/fa";
import { Link } from "react-router-dom";

interface SmallSideBarProps {
  avatarUrl?: string;
  navLinks?: { icon: JSX.Element; to: string; label?: string }[];
  width?: string;
  isLargeBar?: boolean;
  activePath?: string;
}

export default function SideBar({
  avatarUrl = "https://via.placeholder.com/80",
  navLinks = [],
  width = "w-24",
  isLargeBar = false,
  activePath = "",
}: SmallSideBarProps) {
  return (
    <aside
      className={`sticky left-0 ${width} bg-green-t4 p-4 flex flex-col items-center shadow-lg rounded-r-xl overflow-hidden`}
    >
      {/* Avatar and line if !isLargeBar */}
      {!isLargeBar && (
        <>
          <div className="mb-6">
            <img
              src={avatarUrl} // URL
              alt="Profile"
              className="w-16 h-16 rounded-full"
            />
          </div>
          <div className="w-12 border-b-2 border-gray-400 mb-14"></div>
        </>
      )}

      {/* Icons */}
      <nav className="flex flex-col gap-4 flex-grow">
        {navLinks.map((link, index) => (
          <Link
            to={link.to}
            key={index}
            className={`flex items-center text-2xl cursor-pointer rounded-full px-4 py-3 transition-colors duration-300 ${
              link.to === activePath
                ? "text-gray-900 bg-green-t3"
                : "text-gray-600 hover:bg-green-t3 hover:text-gray-900"
            }`}
          >
            <div className="flex items-center">
              {link.icon}
              {isLargeBar && link.label && (
                <span className="ml-2 text-lg">{link.label}</span>
              )}
            </div>
          </Link>
        ))}
      </nav>

      {/* Logout if !isLargeBar */}
      {!isLargeBar && (
        <Link
          className="mt-auto mb-4 text-2xl text-gray-600 hover:text-gray-900"
          to="/"
        >
          <FaSignOutAlt />
        </Link>
      )}
    </aside>
  );
}
