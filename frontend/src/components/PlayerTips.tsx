import { useState, useEffect } from 'react';
import { fetchPlayerTips } from "../api/api";

const PlayerTips = () => {
  const [randomTip, setRandomTip] = useState('');

  const fetchPlayerTip = async () => {
    const data = await fetchPlayerTips();
    setRandomTip(data);
  }

  useEffect(() => {
    fetchPlayerTip();
  }, [])

  return (
    <div>
      <p className="player-tips-container">{randomTip}</p>
    </div>
  );
};

export default PlayerTips;
