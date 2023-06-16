import React from "react";
import EnemyName from "./EnemyName";
import EnemyHealthBar from "./EnemyHealthBar";
import PlayerName from "./PlayerName";
import PotionDisplay from "./PotionDisplay";
import PlayerHealthBar from "./PlayerHealthBar";
import LogBoxDisplay from "./LogBoxDisplay";
import UserPromptText from "./UserPromptText";
import Button from "./Button";
import BackgroundImage from "./BackgroundImage";

const Container = () => {
  return (
    <div className="container">
      <EnemyName />
      <EnemyHealthBar />
      <PlayerName />
      <PotionDisplay />
      <PlayerHealthBar />
      <LogBoxDisplay />
      <UserPromptText />
      <Button />
    </div>
  );
};

export default Container;
