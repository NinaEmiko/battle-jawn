import React from "react";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import BattleContainer from "./components/BattleContainer";

function BattleScreen() {
  return <BattleContainer />;
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default BattleScreen;
