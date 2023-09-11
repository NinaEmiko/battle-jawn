import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import BattleContainer from "./pages/BattleContainer";
import PlayerSelectionContainer from "./pages/HeroCreation";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Account from "./pages/Account";
import Home from "./pages/Home";
import HeroCreation from "./pages/HeroCreation";
import PlayerSelection from "./pages/PlayerSelection";

function App() {
  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="player-selection" element={<PlayerSelection />} />
        <Route path="account-creation" element={<Account />} />
        <Route path="hero-creation" element={<HeroCreation />} />
        <Route path="battle-screen" element={<BattleContainer />} />
      </Routes>
    </BrowserRouter>
  )
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
