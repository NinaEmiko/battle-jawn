import React, { KeyboardEvent } from "react";
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
  // const log = (e: KeyboardEvent): void => {
  //   alert(e.key);
  // };

  return (
    <>
      <div className="btn-grid" id="option-buttons">
        <button className="btn" id="button1">
          {buttonOneText}
        </button>
        <button className="btn" id="button2">
          {buttonTwoText}
        </button>
        <button className="btn" id="button3">
          {buttonThreeText}
        </button>
        <button className="btn" id="button4">
          {buttonFourText}
        </button>
        {/* <input type="text" onKeyUp={log} defaultValue="Hey!" /> */}
      </div>
    </>
  );
};

export default Button;
