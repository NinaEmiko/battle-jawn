import "./App.css";
import Button from "./components/Button";
import EnemyHealthBar from "./components/EnemyHealthBar";
import PlayerHealthBar from "./components/PlayerHealthBar";

function App() {
  return (
    <>
      <EnemyHealthBar />
      <PlayerHealthBar />
      <Button />
    </>
  );
}

export default App;
