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
          <Button
            buttonOneText="Tank"
            buttonTwoText="Healer"
            buttonThreeText="Caster"
            buttonFourText="DPS"
          />
        </div>
      </div>
    </div>
  );
}

export default PlayerSelectionContainer;
