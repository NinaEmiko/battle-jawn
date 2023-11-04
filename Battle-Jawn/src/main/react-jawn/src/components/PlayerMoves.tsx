import axios from "axios";
import "../styling/Button.css";
import { useState } from "react";

interface ButtonProp {
  buttonOneText: string;
  buttonTwoText: string;
  buttonThreeText: string;
  buttonFourText: string;
}

const PlayerMoves: React.FC<ButtonProp> = ({
  buttonOneText,
  buttonTwoText,
  buttonThreeText,
  buttonFourText,
}) => {
  const config = { headers: {
    'Content-Type': 'application/json'
  }}

  const data = {
    moveName: String,
    heroId: Number,
    enemyId: Number,
    battleId: Number
  }
  const url = 'http://localhost:8080/api/battle';
  const [enemyId, setEnemyId] = useState(localStorage.getItem('enemyId'));
  const [battleId, setBattleId] = useState(localStorage.getItem('battleId'));
  const [heroId, setHeroId] = useState(1);
  
  function handleClickBattle(move: string) {

    // axios.put(url, data)
    // .then((response) => {
    // console.log(move + " successful: " + response.data);
    // })
    // .catch((error) => {
    // console.error('Error occured while trying to use: ' + move + " ", error);
    // });
    console.log("Player used " + move);

  }

  return (
    <>
      <div className="btn-grid" id="option-buttons">
        <button onClick={(e) => handleClickBattle(buttonOneText)} className="btn" id="button1">
          {buttonOneText}
        </button>
        <button onClick={(e) => handleClickBattle(buttonTwoText)} className="btn" id="button2">
          {buttonTwoText}
        </button>
        <button onClick={(e) => handleClickBattle(buttonThreeText)} className="btn" id="button3">
          {buttonThreeText}
        </button>
        <button onClick={(e) => handleClickBattle(buttonFourText)} className="btn" id="button4">
          {buttonFourText}
        </button>
      </div>
    </>
  );
};

export default PlayerMoves;