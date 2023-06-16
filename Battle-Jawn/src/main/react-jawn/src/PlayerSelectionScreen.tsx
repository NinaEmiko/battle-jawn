import React from "react";
import BackgroundImage from "../../resources/images/BattleJawnBackground.png";
import PlayerSelectionContainer from "./components/PlayerSelectionContainer";

function PlayerSelectionScreen() {
  return <PlayerSelectionContainer />;
}

document.body.style.backgroundImage = `url("${BackgroundImage}")`;

export default PlayerSelectionScreen;
