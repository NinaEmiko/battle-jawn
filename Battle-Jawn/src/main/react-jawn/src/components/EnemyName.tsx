import "../styling/EnemyName.css";

    interface EnemyNameProp {
        name: string;
      }
      
      const EnemyName: React.FC<EnemyNameProp> = ({
        name
      }) => {
        function handleClickBattle(move: string) {
        }
        

  return (
    <div className="name" id="enemyName">{name}</div>
  )
}

export default EnemyName