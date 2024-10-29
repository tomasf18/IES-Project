# ReactJS

## Create project

```bash
npm create vite@latest
```

## Install Dependencies

```bash
npm install
```

## Run Project

```bash
npm run dev
```

## Tailwind CSS into ReactJS

### Install Tailwind CSS

```bash
npm install -D tailwindcss postcss autoprefixer
```

### Setup Tailwind CSS

1. Run `npx tailwindcss init -p` to create a `tailwind.config.js` and `postcss.config.js` files.

2. Add the paths to all of your component files in your tailwind.config.js file.

```bash
# tailwind.config.js
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
```
3. Add the @tailwind directives for each of Tailwind’s layers to your `./src/index.css` file.

```bash
# ./src/styles/index.css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

## Flowbite into ReactJS

1. Install Flowbite React

```bash
npm i flowbite-react
```

2. Add the Flowbite React `content` path and `plugin` to `tailwind.config.js`

```bash
# tailwind.config.js
const flowbite = require("flowbite-react/tailwind");

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    // ...
    flowbite.content(),
  ],
  plugins: [
    // ...
    flowbite.plugin(),
  ],
};
```

## Navigation System

1. Install React Router DOM

```bash
npm i react-router-dom
```

2. Create a `index.ts` file in `src/pages/`

```bash
# src/pages/index.ts
export { default as LandingPage } from "./LandingPage";
export { default as About } from "./About";
...
```

3. Create a `router.tsx` file in the `src` folder

```bash
# src/router.tsx
import { Route, Routes } from "react-router-dom";
import { LandingPage, About, ... } from "./pages";

export default function AppRoutes() {
    return (
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/about" element={<About />} />
        </Routes>
    )
}
...
```

4. `App.tsx` configuration

```bash
# src/App.tsx
import { BrowserRouter } from "react-router-dom";
import AppRoutes from "./router";

export default function App() {
  return (
    <BrowserRouter>
      <AppRoutes />
    </BrowserRouter>
  )
}
```

5. How to navigate between pages (LandingPage -> About):

```bash
# src/pages/LandingPage.tsx
import { Link } from 'react-router-dom';
import { Button } from 'flowbite-react';

export default function LandingPage() {
    return (
      ...
        <Link to="/about">
            <Button>Go to About Page</Button>
        </Link>
      ...
    );
}
```

## Folder Structure

```bash
my-react-app/
├── node_modules/
├── public/                     # Public files
│   ├── favicon.ico             # Application icon
│   └── assets/                 # Public resources such as images
├── src/                        # Main source code
│   ├── assets/                 # Assets (images, fonts)
│   │   └── logo.png
│   ├── components/             # Reusable components
│   │   ├── Button/
│   │   │   ├── Button.tsx
│   │   │   └── CustomButtonTheme.ts
│   │   ├── Header/
│   │   │   ├── Header.tsx
│   │   │   └── Header.module.css
│   │   └── index.ts            # Exports all components
│   ├── context/                # Global contexts
│   │   ├── AuthContext.tsx     # Authentication context example
│   │   └── ThemeContext.tsx    # Theme context example
│   ├── hooks/                  # Custom hooks
│   │   ├── useAuth.ts          # Authentication hook example
│   │   └── useFetch.ts         # Fetch hook example
│   ├── pages/                  # Main pages
│   │   ├── HomePage/
│   │   │   ├── HomePage.tsx
│   │   │   └── HomePage.module.css
│   │   ├── AboutPage/
│   │   │   ├── AboutPage.tsx
│   │   │   └── AboutPage.module.css
│   │   └── index.ts            # Exports all pages
│   ├── styles/                 # Global styles
│   │   ├── globals.css         # Global CSS styles
│   │   └── variables.css       # CSS variables (e.g., colors, fonts)
│   ├── utils/                  # Utility functions
│   │   ├── formatDate.ts       # Date formatting function
│   │   └── api.ts              # API configuration or functions
│   ├── App.tsx                 # Main application component
│   ├── main.tsx                # ReactDOM entry point
│   ├── router.tsx              # Centralized routing configuration
│   └── types/                  # TypeScript type definitions
│       └── index.d.ts          # Global type definitions
├── .env                        # Environment variables
├── .gitignore                  # Git ignored files
├── eslint.config.js
├── index.html                  # Main HTML file
├── package.json                # Project dependencies and scripts
├── tailwind.config.js          # Tailwind CSS configuration (if used)
├── tsconfig.json               # TypeScript configuration
└── README.md                   # Project documentation

```

## Project Theme Colors

```bash
# tailwind.config.js
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
          light: '#A5D6A7',
          medium: '#4CAF4F',
        },
        ...
        green1: '#4CAF4F',
        green2: '#A5D6A7',
        ...
      }
    },
  },
  plugins: [
    flowbite.plugin(),
  ],
}
```

## Illustrations

https://undraw.co/illustrations
