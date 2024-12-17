import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import dotenv from 'dotenv';

// Load the environment variables from `.env`
dotenv.config();

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: parseInt(process.env.VITE_FRONTEND_PORT || '3000'), 
    host: process.env.VITE_FRONTEND_HOST,
    strictPort: true // Ensure the port is not changed if it's already in use
  },
})


