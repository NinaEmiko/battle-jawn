import { useEffect, useState } from "react";
import "../styling/PlayerHealthBar.css";
import axios from "axios";

const PlayerHealthBar = () => {
  const [health, setHealth] = useState('');

  // useEffect(() => {
  //   axios.get('http://localhost:8080/api/toon/health')
  //     .then((response) => {
  //       console.log(response.data);
  //       setHealth(response.data);
  //     })
  //     .catch((error) => {
  //       console.error('Error fetching toon health:', error);
  //     });
  // }, []);

  return (
    <progress className='healthBar' id="playerHealthBar" value="100" max="100"></progress>
  )
}

export default PlayerHealthBar