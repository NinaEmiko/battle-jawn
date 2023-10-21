import "../styling/PlayerName.css";

    interface PlayerNameProp {
        role: string;
      }
      
      const PlayerName: React.FC<PlayerNameProp> = ({
        role
      }) => {
        function handleClickBattle(move: string) {
        }
        

  return (
    <div className="name" id="playerName">{role}</div>
  )
}

export default PlayerName