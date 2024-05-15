import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { Route, Routes } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import { ThemeProvider } from "next-themes";

function App() {

  return (
    <ThemeProvider attribute="class">
      <Header/>
      <Routes>
        <Route path="/" element={<LandingPage />} />
      </Routes>
      <Footer />
    </ThemeProvider>
  );
}

export default App;
