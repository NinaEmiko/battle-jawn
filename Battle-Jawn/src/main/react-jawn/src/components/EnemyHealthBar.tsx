import "../styling/EnemyHealthBar.css";

const EnemyHealthBar = () => {
  return (
    <progress className="healthBar" id="enemyHealthBar" value="100" max="100" />
  );
};

export default EnemyHealthBar;
