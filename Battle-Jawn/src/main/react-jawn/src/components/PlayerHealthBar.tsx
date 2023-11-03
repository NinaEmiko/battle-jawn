import "../styling/PlayerHealthBar.css";

const PlayerHealthBar = (props: any) => {

  return (
    <progress className='healthBar' id="playerHealthBar" value={props.health} max={props.maxHealth}></progress>
  )
}

export default PlayerHealthBar