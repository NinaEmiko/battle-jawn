import "./App.css";
import Button from "./components/Button";
import EnemyHealthBar from "./components/EnemyHealthBar";
import EnemyName from "./components/EnemyName";
import LogBoxDisplay from "./components/LogBoxDisplay";
import PlayerHealthBar from "./components/PlayerHealthBar";
import PlayerName from "./components/PlayerName";
import PotionDisplay from "./components/PotionDisplay";

function App() {
  return (
    <>
      <EnemyName />
      <EnemyHealthBar />
      <PlayerName />
      <PotionDisplay />
      <PlayerHealthBar />
      <LogBoxDisplay />
      <Button />
    </>
  );
}

export default App;
