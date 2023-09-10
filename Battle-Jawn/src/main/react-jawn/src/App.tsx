import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import BattleContainer from "./pages/BattleContainer";
import PlayerSelectionContainer from "./pages/PlayerSelectionContainer";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Account from "./pages/Account";
import Home from "./pages/Home";

function App() {
  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="account" element={<Account />} />
        <Route path="hero-creation" element={<PlayerSelectionContainer />} />
        <Route path="battle-screen" element={<BattleContainer />} />
      </Routes>
    </BrowserRouter>
  )
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
