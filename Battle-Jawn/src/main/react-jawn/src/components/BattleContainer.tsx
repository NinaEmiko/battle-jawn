import React from "react";
import EnemyName from "./EnemyName";
import EnemyHealthBar from "./EnemyHealthBar";
import PlayerName from "./PlayerName";
import PotionDisplay from "./PotionDisplay";
import PlayerHealthBar from "./PlayerHealthBar";
import LogBoxDisplay from "./LogBoxDisplay";
import UserPromptText from "./UserPromptText";
import Button from "./Button";
import "../styling/Container.css";

function BattleContainer() {
  return (
    <div className="container">
      <EnemyName />
      <EnemyHealthBar />
      <PlayerName />
      <PotionDisplay />
      <PlayerHealthBar />
      <div className="logbox-and-user-input">
        <LogBoxDisplay />
        <div>
          <div className="user-prompt-wrapper">
            <UserPromptText text={"What would you like to do?"} />
            <Button
              buttonOneText="Attack"
              buttonTwoText="Heal"
              buttonThreeText="Strong Attack"
              buttonFourText="Run"
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default BattleContainer;
