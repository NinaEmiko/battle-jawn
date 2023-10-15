import "../styling/Button.css";

interface ButtonProp {
  buttonOneText: string;
  buttonTwoText: string;
  buttonThreeText: string;
  buttonFourText: string;
}

const Button: React.FC<ButtonProp> = ({
  buttonOneText,
  buttonTwoText,
  buttonThreeText,
  buttonFourText,
}) => {
  function handleClickBattle(move: string) {
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

export default Button;