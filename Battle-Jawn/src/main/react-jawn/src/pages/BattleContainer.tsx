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

function BattleContainer({props}:{props:any}) {
  const [role, setRole] = useState('');
  const [health, setHealth] = useState(0);
  const [maxHealth, setMaxHealth] = useState(0);
  const [enemyId, setEnemyId] = useState(localStorage.getItem('enemyId'));
  const [enemyName, setEnemyName] = useState('');
  const [enemyHealth, setEnemyHealth] = useState(0);
  const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
  const [messages, setMessages] = useState('');
  const [battleSessionId, setBattleSessionId] = useState(localStorage.getItem('battleSessionId'));
  const [battleHistory, setBattleHistory] = useState([]);

    useEffect(() => {
        const fetchHero = async () => {
            try {
                const response = await
                axios.get('http://localhost:8080/api/hero/' + props)
                setRole(response.data.role);
                setHealth(response.data.health);
                setMaxHealth(response.data.maxHealth);
                } catch (error) {
                console.error('Error fetching Hero data: ', error)
                }
            }
        const fetchEnemy = async () => {
            try {
                const response = await
                axios.get('http://localhost:8080/api/enemy/' + enemyId)
                setEnemyName(response.data.name);
                setEnemyHealth(response.data.health);
                setEnemyMaxHealth(response.data.maxHealth);
                } catch (error) {
                console.error('Error fetching Enemy data: ', error)
                }
            }
        const fetchBattleHistory = async () => {
            try {
                const response = await
                axios.get('http://localhost:8080/api/battle-session/' + battleSessionId)
                setBattleHistory(response.data.battleHistory);
                } catch (error) {
                console.error('Error fetching Battle History data: ', error)
                }
            }
        fetchHero();
        fetchEnemy();
        fetchBattleHistory();
    }, [props, enemyId, battleSessionId])


  return (
    <div className="battle-container">
      <EnemyName props={enemyName}/>
      <EnemyHealthBar props={{enemyHealth, enemyMaxHealth}}/>
      <PlayerName props={role} />
      <PotionDisplay />
      <PlayerHealthBar  props={maxHealth}/>
      <div className="logbox-and-user-input">
        <LogBoxDisplay props={battleHistory} />
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
