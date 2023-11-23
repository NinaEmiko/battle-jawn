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

  const url = 'http://localhost:8080/api/hero-move';
  const [battleSessionId, setBattleSessionId] = useState(localStorage.getItem('battleSessionId'));
  
  function handleClickBattle(move: string) {

    axios.post(url, {
      move: move,
      battleSessionId: battleSessionId
        })
    .then((response) => {
    console.log(move + " successful!");
    })
    .catch((error) => {
    console.error('Error occurred while trying to use: ' + move + " ", error);
    });

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