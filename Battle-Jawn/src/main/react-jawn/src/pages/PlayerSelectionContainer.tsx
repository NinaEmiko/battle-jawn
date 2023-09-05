import UserPromptText from "../components/UserPromptText";
import Button from "../components/Button";
import "../styling/Container.css";
import PlayerTips from "../components/PlayerTips";

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
