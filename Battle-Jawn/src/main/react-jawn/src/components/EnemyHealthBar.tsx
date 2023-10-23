import { useEffect, useState } from "react";
import "../styling/EnemyHealthBar.css";
import axios from "axios";

const EnemyHealthBar = (props: any) => {
  const { enemyMaxHealth } = props;
  const [health, setHealth] = useState(0);
  console.log("Enemy Max Health inside BattleContainer: " + { enemyMaxHealth })
  console.log("Enemy Health inside BattleContainer: " + health)

  useEffect(() => {
    //Change 1 to enemyId
    axios.get('http://localhost:8080/api/enemy/health/' + 1)
      .then((response) => {
        setHealth(response.data);
        console.log("Inside getEnemyHealthById. Response data: " + response.data);
      })
      .catch((error) => {
        console.error('Error fetching enemy health:', error);
      });
  }, []);
  console.log("Enemy health: " + health);
  console.log("Enemy max health: " + enemyMaxHealth);

  return (
    <progress className="healthBar" id="enemyHealthBar" value={health} max={enemyMaxHealth} />
  );
};

export default EnemyHealthBar;
