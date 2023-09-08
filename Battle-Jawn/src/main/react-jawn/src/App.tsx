import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import BattleContainer from "./pages/BattleContainer";
import PlayerSelectionContainer from "./pages/PlayerSelectionContainer";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Account from "./components/Account";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="accounts" element={<Account />} />
        <Route path="/" element={<PlayerSelectionContainer />} />
        <Route path="battle-screen" element={<BattleContainer />} />
      </Routes>
    </BrowserRouter>
  )
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
