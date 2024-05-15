import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      "/api": {
        target: "http://localhost:5202",
        changeOrigin: true,
        secure: false,
      },
      "/auth": {
        target: "http://localhost:5202",
        changeOrigin: true,
      },
    },
  },
  build: {
    outDir: "../wwwroot",
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
});
