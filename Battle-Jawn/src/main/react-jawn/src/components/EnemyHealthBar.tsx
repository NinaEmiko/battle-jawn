import "../styling/EnemyHealthBar.css";

const EnemyHealthBar = (props: any) => {

  return (
    <progress className="healthBar" id="enemyHealthBar" value={props.health} max={props.enemyMaxHealth} />
  );
};

export default EnemyHealthBar;
