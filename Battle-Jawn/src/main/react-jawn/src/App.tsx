import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import BattleContainer from "./components/BattleContainer";
import PlayerSelectionContainer from "./components/PlayerSelectionContainer";

function App() {
  return <BattleContainer />;
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
