import "../styling/PlayerName.css";

const PlayerName = (props: any) => {
  return (
    <div className="name" id="playerName">{props.props}</div>
  );
};

export default PlayerName;
