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
  const [heroId, setHeroId] = useState(localStorage.getItem('heroId'));
  const [role, setRole] = useState('');
  const [health, setHealth] = useState(0);
  const [maxHealth, setMaxHealth] = useState(0);
  const [enemyId, setEnemyId] = useState(localStorage.getItem('enemyId'));
  const [enemyName, setEnemyName] = useState('');
  const [enemyHealth, setEnemyHealth] = useState(0);
  const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
  const [messages, setMessages] = useState('');
  const [battleId, setBattleId] = useState(0);

  useEffect(() => {
    getHero();
    getEnemy();

  }, []);

  const getHero = () => {
      axios.get('http://localhost:8080/api/hero/' + heroId)
            .then((response) => {
              setRole(response.data.role);
              setMaxHealth(response.data.maxHealth);
              console.log("Inside Hero getById. Response data: " + response.data);
            })
            .catch((error) => {
              console.error('Error fetching hero data:', error);
        });
  }

  const getEnemy = () => {
      axios.get('http://localhost:8080/api/enemy/' + enemyId)
        .then((response) => {
          setEnemyName(response.data.name);
          setEnemyHealth(response.data.health);
          setEnemyMaxHealth(response.data.maxHealth);
          console.log("Inside Enemy getById. response.data.name: " + response.data.name);
        })
        .catch((error) => {
          console.error('Error fetching enemy data:', error);
        });
    }

  return (
    <div className="battle-container">
      <EnemyName props={enemyName}/>
      <EnemyHealthBar props={{enemyHealth, enemyMaxHealth}}/>
      <PlayerName props={role} />
      <PotionDisplay />
      <PlayerHealthBar  props={maxHealth}/>
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
