import "./App.css";
import Button from "./components/Button";
import EnemyHealthBar from "./components/EnemyHealthBar";
import EnemyName from "./components/EnemyName";
import PlayerHealthBar from "./components/PlayerHealthBar";
import PlayerName from "./components/PlayerName";

function App() {
  return (
    <>
      <EnemyName />
      <EnemyHealthBar />
      <PlayerName />
      <PlayerHealthBar />
      <Button />
    </>
  );
}

export default App;
