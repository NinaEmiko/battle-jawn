import "../styling/PlayerName.css";

const PlayerName = (props: any) => {

  return (
    <div className="name" id="playerName">{props.name}</div>
  );
};

export default PlayerName;
