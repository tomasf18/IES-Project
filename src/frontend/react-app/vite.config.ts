import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import dotenv from 'dotenv';

// Load the environment variables from `.env`
dotenv.config();

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: parseInt(process.env.FRONTEND_CONTAINER_PORT || '3000'), // Usa a porta do .env ou 3000 como padrão
    host: true, // permite que o Vite escute em todas as interfaces de rede
  },
})


