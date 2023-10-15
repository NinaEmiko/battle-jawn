import "../styling/PlayerHealthBar.css";

const PlayerHealthBar = () => {
  return (
    <progress className='healthBar' id="playerHealthBar" value="100" max="100"></progress>
  )
}

export default PlayerHealthBar