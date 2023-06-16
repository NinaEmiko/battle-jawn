import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png"
import Container from "./components/Container";

function App() {
  return (
      <Container />
  );
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default App;
