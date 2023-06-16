import React, { KeyboardEvent } from "react";
import "../styling/Button.css";

function Button() {
  // const log = (e: KeyboardEvent): void => {
  //   alert(e.key);
  // };

  return (
    <>
      <div className="btn-grid" id="option-buttons">
        <button className="btn" id="button1">
          Button 1
        </button>
        <button className="btn" id="button2">
          Button 2
        </button>
        <button className="btn" id="button3">
          Button 3
        </button>
        <button className="btn" id="button4">
          Button 4
        </button>
        {/* <input type="text" onKeyUp={log} defaultValue="Hey!" /> */}
      </div>
    </>
  );
}

export default Button;
