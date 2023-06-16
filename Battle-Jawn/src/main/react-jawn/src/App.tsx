import "./App.css";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png"
import Container from "./components/Container";

function App() {
  return (
    <div style={{backgroundImage: `url("${BackgroundImage}")`}}>
      <Container />
    </div>
  );
}

export default App;
