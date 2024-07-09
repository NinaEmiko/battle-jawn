// import { defineConfig } from 'vite'
// import react from '@vitejs/plugin-react'

// // https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [react()],
//   server: {
//     host: '0.0.0.0',
//     port: Number(process.env.PORT) || 3000,
//   },
// })

import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import dotenv from 'dotenv';

// Load environment variables based on NODE_ENV
const loadEnv = (mode: string) => {
  const env = dotenv.config({
    path: `.env.${mode}`,
  }).parsed;

  return Object.keys(env || {}).reduce((prev, key) => {
    prev[`process.env.${key}`] = JSON.stringify(env[key]);
    return prev;
  }, {});
};

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  return {
    plugins: [react()],
    server: {
      host: '0.0.0.0',
      port: Number(process.env.PORT) || 3000,
    },
    define: {
      ...loadEnv(mode),
    },
  };
});
