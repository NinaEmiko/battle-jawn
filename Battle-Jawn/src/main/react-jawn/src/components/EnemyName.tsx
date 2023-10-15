import "../styling/EnemyName.css";

function EnemyName(){
  const enemyList = ["Wolf", "Orc", "Spirit", "Thief"];

  const handleGenerateRandomEnemy = () => {
    return enemyList[Math.floor(Math.random() * (enemyList.length - 1))];
  }

  return (
    <div className="name" id="enemyName">{handleGenerateRandomEnemy()}</div>
  )
}

export default EnemyName