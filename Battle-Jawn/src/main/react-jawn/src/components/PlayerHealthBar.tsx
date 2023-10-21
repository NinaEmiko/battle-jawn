import { useEffect, useState } from "react";
import "../styling/PlayerHealthBar.css";
import axios from "axios";

const PlayerHealthBar = () => {
  const [health, setHealth] = useState('');

  return (
    <progress className='healthBar' id="playerHealthBar" value="100" max="100"></progress>
  )
}

export default PlayerHealthBar