import EnemyName from "../components/EnemyName";
import EnemyHealthBar from "../components/EnemyHealthBar";
import PotionDisplay from "../components/PotionDisplay";
import PlayerHealthBar from "../components/PlayerHealthBar";
import LogBoxDisplay from "../components/LogBoxDisplay";
import UserPromptText from "../components/UserPromptText";
import Button from "../components/Button";
import "../styling/Container.css";

function BattleContainer() {
  const role = window.localStorage.getItem("Role");

  return (
    <div className="battle-container">
      <EnemyName/>
      <EnemyHealthBar />
      <div className="name" id="playerName">{role}</div>
      <PotionDisplay />
      <PlayerHealthBar />
      <div className="logbox-and-user-input">
        <LogBoxDisplay />
        <div>
          <div className="user-prompt-wrapper">
            <UserPromptText text={"What would you like to do?"} />

            {role == "Tank" &&
              <Button
                buttonOneText="Strike"
                buttonTwoText="Potion"
                buttonThreeText="Impale"
                buttonFourText="Run"
              />
            }

            {role == "Healer" &&
              <Button
                buttonOneText="Wand"
                buttonTwoText="Heal"
                buttonThreeText="Holy"
                buttonFourText="Run"
              />
            }   

            {role == "Caster" &&
              <Button
                buttonOneText="Wand"
                buttonTwoText="Potion"
                buttonThreeText="Blast"
                buttonFourText="Run"
              />
            }   
            {role == "DPS" &&
              <Button
                buttonOneText="Stab"
                buttonTwoText="Potion"
                buttonThreeText="Steal"
                buttonFourText="Run"
              />
            }   

            
          </div>
        </div>
      </div>
    </div>
  );
}

export default BattleContainer;
