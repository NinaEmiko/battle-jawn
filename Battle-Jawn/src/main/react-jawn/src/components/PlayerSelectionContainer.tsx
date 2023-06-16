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
import PlayerTips from "./PlayerTips";

function PlayerSelectionContainer() {
  return (
    <div className="container">
      <PlayerTips />
        <div>
        <div className="user-prompt-wrapper">
        <UserPromptText text="Choose a role!"></UserPromptText>
        <Button />
        </div>
      </div>
    </div>
  );
};

export default PlayerSelectionContainer;
