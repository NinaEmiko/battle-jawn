import React, { KeyboardEvent, useState } from "react";
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
  const [role, setRole] = useState("");

  function handleClick(role: string) {
    setRole(role);
    window.localStorage.setItem("Role", role);
    navigate("/battle-screen");
  }
  

  return (
    <>
      <div className="btn-grid" id="option-buttons">
        <button onClick={(e) => handleClick(buttonOneText)} className="btn" id="button1">
          {buttonOneText}
        </button>
        <button onClick={(e) => handleClick(buttonTwoText)} className="btn" id="button2">
          {buttonTwoText}
        </button>
        <button onClick={(e) => handleClick(buttonThreeText)} className="btn" id="button3">
          {buttonThreeText}
        </button>
        <button onClick={(e) => handleClick(buttonFourText)} className="btn" id="button4">
          {buttonFourText}
        </button>
      </div>
    </>
  );
};

export default Button;