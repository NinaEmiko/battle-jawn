import { useEffect, useState } from "react";
import "../styling/PlayerHealthBar.css";
import axios from "axios";

const PlayerHealthBar = (maxHealth: any) => {
  const [id, setId] = useState(localStorage.getItem('heroId'));
  const [health, setHealth] = useState(0);

  useEffect(() => {
    axios.get('http://localhost:8080/api/hero/health/' + id)
      .then((response) => {
        setHealth(response.data);
        console.log("Inside getPlayerHealthById. Response data: " + response.data);
      })
      .catch((error) => {
        console.error('Error fetching hero health:', error);
      });
  }, []);

  return (
    <progress className='healthBar' id="playerHealthBar" value={health} max={maxHealth}></progress>
  )
}

export default PlayerHealthBar