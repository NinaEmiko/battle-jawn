import { useState, useEffect } from 'react';
import "../styling/PlayerTips.css";
import axios from 'axios';

const PlayerTips = () => {
  const [randomTip, setRandomTip] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/player-tip/random')
      .then((response) => {
        console.log("Inside getRandomPlayerTip GET request. Response data: " + response.data);
        const randomTipBody = response.data;
        setRandomTip(randomTipBody);
      })
      .catch((error) => {
        console.error('Error fetching random player tip:', error);
      });
  }, []);

  return (
    <div>
      <p className="player-tips-container">{randomTip}</p>
    </div>
  );
};

export default PlayerTips;
