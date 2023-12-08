import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppContent from "./components/AppContent";
import Header from "./components/Header";

function App() {
  return (
    <BrowserRouter>
    <div>
    <Header pageTitle="Battle Jawn"/>
      <Routes>
        <Route path="/" element={<AppContent />} />
      </Routes>
      </div>
    </BrowserRouter>
  )
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
