import type { CustomFlowbiteTheme } from "flowbite-react";

const customHeaderTheme: CustomFlowbiteTheme["navbar"] = {
  root: {
    base: "bg-green-t3 px-2 py-2.5 dark:border-gray-700 dark:bg-gray-800 sm:px-4",
  },
  link: {
    base: "block py-2 pl-3 pr-4 md:p-0",
    active: {
      on: "bg-cyan-700 text-white dark:text-white md:bg-transparent md:text-cyan-700",
      off: "border-b border-gray-100 text-gray-700 hover:bg-gray-50 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:border-0 md:hover:bg-transparent md:hover:text-gray-900 md:dark:hover:bg-transparent md:dark:hover:text-white",
    },
    disabled: {
      on: "text-gray-400 hover:cursor-not-allowed dark:text-gray-600",
      off: "",
    },
  },
};

export default customHeaderTheme;