import { useState, useEffect } from 'react';
import "../styling/BattleContainer.css";
import axios from 'axios';

const PlayerTips = () => {
  const [randomTip, setRandomTip] = useState('');

  useEffect(() => {
          const fetchPlayerTip = async () => {
              try {
                  const response = await
                  axios.get('http://localhost:8080/api/player-tip/random')
                  const randomTipBody = response.data;
                          setRandomTip(randomTipBody);
                  } catch (error) {
                  console.error('Error fetching Player Tip data: ', error)
                  }
              }
          fetchPlayerTip();
      }, [])

  return (
    <div>
      <p className="player-tips-container">{randomTip}</p>
    </div>
  );
};

export default PlayerTips;
