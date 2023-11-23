import PotionDisplay from "../components/PotionDisplay";
import PlayerMoves from "../components/PlayerMoves";
import "../styling/Container.css";
import { useEffect, useState } from "react";
import axios from "axios";
import "../styling/BattleContainer.css";
import "../styling/UserPromptText.css";

function BattleContainer({props}:{props:any}) {
    const [role, setRole] = useState('');
    const [health, setHealth] = useState(0);
    const [maxHealth, setMaxHealth] = useState(0);
    const [enemyName, setEnemyName] = useState('');
    const [enemyHealth, setEnemyHealth] = useState(0);
    const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
    const [battleHistory, setBattleHistory] = useState([]);

    useEffect(() => {
        const fetchHero = async () => {
            try {
                const response = await
                axios.get('http://localhost:8080/api/hero/' + props.heroId)
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
                axios.get('http://localhost:8080/api/enemy/' + props.enemyId)
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
                axios.get('http://localhost:8080/api/battle-session/' + props.battleSessionId)
                setBattleHistory(response.data.battleHistory);
                } catch (error) {
                console.error('Error fetching Battle History data: ', error)
                }
            }
        fetchHero();
        fetchEnemy();
        fetchBattleHistory();
    }, [props])


    return (
        <div className="battle-container">
          <div className="name" id="enemyName">{enemyName}</div>
          <progress className="healthBar" id="enemyHealthBar" value={enemyHealth} max={enemyMaxHealth} />
          <div className="name" id="playerName">{role}</div>
          <PotionDisplay />
          <progress className='healthBar' id="playerHealthBar" value={health} max={maxHealth}></progress>
          <div className="logbox-and-user-input">
            <div className="logBox" id="logBox">{battleHistory}
          </div>
          <div>
              <div className="user-prompt-wrapper">
                <div className="userPrompt">{"What would you like to do?"}</div>

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
