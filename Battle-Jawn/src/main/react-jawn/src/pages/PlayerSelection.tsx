import Button from "../components/Button";
import PlayerTips from "../components/PlayerTips";
import UserPromptText from "../components/UserPromptText";

function PlayerSelection() {
        return (
            <div className="container">
              <PlayerTips />
              <div>
                <div className="user-prompt-wrapper">
                  <UserPromptText text="Choose a Class: "></UserPromptText>
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

export default PlayerSelection;