import EnemyName from "../components/EnemyName";
import EnemyHealthBar from "../components/EnemyHealthBar";
import PotionDisplay from "../components/PotionDisplay";
import PlayerHealthBar from "../components/PlayerHealthBar";
import LogBoxDisplay from "../components/LogBoxDisplay";
import UserPromptText from "../components/UserPromptText";
import PlayerMoves from "../components/PlayerMoves";
import "../styling/Container.css";
import { useEffect, useState } from "react";
import axios from "axios";
import PlayerName from "../components/PlayerName";

function BattleContainer() {
  const [role, setRole] = useState('');
  const [id, setId] = useState(localStorage.getItem('toonId'));

  useEffect(() => {
    axios.get('http://localhost:8080/api/toon/' + id)
      .then((response) => {
        setRole(response.data.role);
        console.log(response.data);
      })
      .catch((error) => {
        console.error('Error fetching toon data:', error);
      });
  }, []);

  return (
    <div className="battle-container">
      <EnemyName/>
      <EnemyHealthBar />
      <PlayerName role={role} />
      <PotionDisplay />
      <PlayerHealthBar />
      <div className="logbox-and-user-input">
        <LogBoxDisplay />
        <div>
          <div className="user-prompt-wrapper">
            <UserPromptText text={"What would you like to do?"} />

            {role == "Tank" &&
              <PlayerMoves
                buttonOneText="Strike"
                buttonTwoText="Potion"
                buttonThreeText="Impale"
                buttonFourText="Run"
              />
            }
            {role == "Healer" &&
              <PlayerMoves
                buttonOneText="Wand"
                buttonTwoText="Heal"
                buttonThreeText="Holy"
                buttonFourText="Run"
              />
            } 
            {role == "Caster" &&
              <PlayerMoves
                buttonOneText="Wand"
                buttonTwoText="Potion"
                buttonThreeText="Blast"
                buttonFourText="Run"
              />
            }   
            {role == "DPS" &&
              <PlayerMoves
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
