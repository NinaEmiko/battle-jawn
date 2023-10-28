import "../styling/EnemyName.css";

const EnemyName = (props: any) => {

  return (
    <div className="name" id="enemyName">{props.name}</div>
  );
};

export default EnemyName;
