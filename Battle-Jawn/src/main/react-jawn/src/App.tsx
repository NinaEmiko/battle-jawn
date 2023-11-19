import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";

function App() {
  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
      </Routes>
    </BrowserRouter>
  )
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
