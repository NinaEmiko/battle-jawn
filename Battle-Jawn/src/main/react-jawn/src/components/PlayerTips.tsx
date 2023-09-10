import { useState, useEffect } from 'react';
import axios from 'axios';

const PlayerTips = () => {
  const [randomTip, setRandomTip] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/player-tip/random')
      .then((response) => {
        const randomTipBody = response.data;
        setRandomTip(randomTipBody);
      })
      .catch((error) => {
        console.error('Error fetching random player tip:', error);
      });
  }, []);

  return (
    <div>
      <h3>{randomTip}</h3>
    </div>
  );
};

export default PlayerTips;
