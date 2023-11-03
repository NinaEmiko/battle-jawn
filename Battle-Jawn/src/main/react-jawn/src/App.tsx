import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import BattleContainer from "./pages/BattleContainer";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import PlayerSelection from "./pages/PlayerSelection";
import HomePage from "./pages/HomePage";

function App() {
  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="player-selection" element={<PlayerSelection />} />
        <Route path="battle-screen" element={<BattleContainer />} />
      </Routes>
    </BrowserRouter>
  )
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
