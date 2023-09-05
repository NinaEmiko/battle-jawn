import React, { KeyboardEvent } from "react";
import "../styling/Button.css";
import { useNavigate } from "react-router-dom";



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
  const navigate = useNavigate();
  const handleClick = () => {
    navigate("/battle-screen");
  }
  

  return (
    <>
      <div className="btn-grid" id="option-buttons">
        <button onClick={handleClick} className="btn" id="button1">
          {buttonOneText}
        </button>
        <button onClick={handleClick} className="btn" id="button2">
          {buttonTwoText}
        </button>
        <button onClick={handleClick} className="btn" id="button3">
          {buttonThreeText}
        </button>
        <button onClick={handleClick} className="btn" id="button4">
          {buttonFourText}
        </button>
      </div>
    </>
  );
};

export default Button;
