const flowbite = require("flowbite-react/tailwind");

/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    flowbite.content(),
  ],
  theme: {
    extend: {
      colors: {
        green: {
          darker: '#237D31',
          primary: '#4CAF4F',
          t1: '#66BB69',
          t2: '#81C784',
          t3: '#A5D6A7',
          t4: '#C8E6C9',
          t5: '#E8F5E9',
        },
        red: {
          primary: '#E46C6C',
        }
      },
    },
  },
  plugins: [
    flowbite.plugin(),
  ],
}