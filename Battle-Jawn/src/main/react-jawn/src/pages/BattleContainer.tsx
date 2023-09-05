import React from "react";
import EnemyName from "../components/EnemyName";
import EnemyHealthBar from "../components/EnemyHealthBar";
import PlayerName from "../components/PlayerName";
import PotionDisplay from "../components/PotionDisplay";
import PlayerHealthBar from "../components/PlayerHealthBar";
import LogBoxDisplay from "../components/LogBoxDisplay";
import UserPromptText from "../components/UserPromptText";
import Button from "../components/Button";
import "../styling/Container.css";

function BattleContainer() {
  return (
    <div className="container">
      <EnemyName/>
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
