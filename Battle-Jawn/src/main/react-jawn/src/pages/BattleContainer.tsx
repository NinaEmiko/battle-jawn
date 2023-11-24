import "../styling/Container.css";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import "../styling/BattleContainer.css";
import "../styling/UserPromptText.css";
import "../styling/Button.css";
import healthPotion from "../assets/healthPotion.png";

function BattleContainer({props}:{props:any}) {
    const [role, setRole] = useState('');
    const [health, setHealth] = useState(0);
    const [maxHealth, setMaxHealth] = useState(0);
    const [potionCount, setPotionCount] = useState(0);
    const [enemyName, setEnemyName] = useState('');
    const [enemyHealth, setEnemyHealth] = useState(0);
    const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
    const [battleHistory, setBattleHistory] = useState([]);
    const [gameOver, setGameOver] = useState(false);
    const [heroTurn, setHeroTurn] = useState(true);
    const [buttonDisabled, setButtonDisabled] = useState(false);

    useEffect(() => {

        const fetchHero = async () => {
            try {
                const response = await
                axios.get('http://localhost:8080/api/hero/' + props.heroId)
                setRole(response.data.role);
                setHealth(response.data.health);
                setMaxHealth(response.data.maxHealth);
                setPotionCount(response.data.potions)
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
                axios.get('http://localhost:8080/api/battle-history-message/' + props.battleSessionId)
                setBattleHistory(response.data);
                } catch (error) {
                console.error('Error fetching Battle History data: ', error)
                }
            }
        if (!heroTurn) {
          handleEnemyMove();
          setHeroTurn(true);
        }
            
        fetchHero();
        fetchEnemy();
        fetchBattleHistory();
    }, [props])

    function handleEnemyMove() {
      let timeoutId: number | undefined;
      timeoutId = setTimeout(() => {
        axios.post('http://localhost:8080/api/enemy-move', {
          battleSessionId: props.battleSessionId
            })
          .then(response => {
            setHealth(response.data.heroHealth);
            setGameOver(response.data.gameOver);
            setPotionCount(response.data.potionCount);
            setEnemyHealth(response.data.enemyHealth);
            setBattleHistory(response.data.battleHistory);
            setButtonDisabled(false);
          })
          .catch(error => {
            console.error('Error fetching enemy move data:', error);
          })
      }, 1500);
    }

    function handleClickBattle(move: string) {
      setButtonDisabled(true);

        axios.post('http://localhost:8080/api/hero-move', {
          move: move,
          battleSessionId: props.battleSessionId
            })
        .then((response) => {
          setHealth(response.data.heroHealth);
          setPotionCount(response.data.potionCount);
          setEnemyHealth(response.data.enemyHealth);
          setBattleHistory(response.data.battleHistory);
          setGameOver(response.data.gameOver);
          handleEnemyMove();
        })
        .catch((error) => {
        console.error('Error occurred while trying to use: ' + move + " ", error);
        });
        setHeroTurn(false);
    }
    console.log("Hero turn: " +heroTurn); 

    return (
        <div className="battle-container">
          <div className="name" id="enemyName">{enemyName}</div>
          <progress className="healthBar" id="enemyHealthBar" value={enemyHealth} max={enemyMaxHealth} />
          <div className="name" id="playerName">{role}</div>
            <div className="potionDisplay">
              <a href="#" onClick={(e) => handleClickBattle('Potion')}>
              {Array.from({ length: potionCount }).map((_, index) => (
                <img className="potion"
                key={index}
                src={healthPotion}
                alt={`Potion ${index + 1}`}
              />
            ))}
        </a>
      </div>
          <progress className='healthBar' id="playerHealthBar" value={health} max={maxHealth}></progress>
          <div className="logbox-and-user-input">
            <div className="logBox" id="logBox">
              {battleHistory.slice().reverse().map((item, index) => (
            <div key={index}>
          {item}
          <br />
        </div>
      ))}
          </div>
          <div>
              <div className="user-prompt-wrapper">
                <div className="userPrompt">{"What would you like to do?"}</div>

                {role == "Tank" &&
                  <div className="btn-grid" id="option-buttons">
                      <button onClick={(e) => handleClickBattle('Strike')} disabled={buttonDisabled} className="btn" id="button1">
                        Strike
                      </button>
                      <button onClick={(e) => handleClickBattle('Potion')} disabled={buttonDisabled} className="btn" id="button2">
                        Potion
                      </button>
                      <button onClick={(e) => handleClickBattle('Impale')} disabled={buttonDisabled} className="btn" id="button3">
                        Impale
                      </button>
                      <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn" id="button4">
                        Run
                      </button>
                    </div>
                }
                {role == "Healer" &&
                  <div className="btn-grid" id="option-buttons">
                      <button onClick={(e) => handleClickBattle('Wand')} disabled={buttonDisabled} className="btn" id="button1">
                        Wand
                      </button>
                      <button onClick={(e) => handleClickBattle('Heal')} disabled={buttonDisabled} className="btn" id="button2">
                        Heal
                      </button>
                      <button onClick={(e) => handleClickBattle('Holy')} disabled={buttonDisabled} className="btn" id="button3">
                        Holy
                      </button>
                      <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn" id="button4">
                        Run
                      </button>
                    </div>
                }
                {role == "Caster" &&
                    <div className="btn-grid" id="option-buttons">
                        <button onClick={(e) => handleClickBattle('Wand')} disabled={buttonDisabled} className="btn" id="button1">
                          Wand
                        </button>
                        <button onClick={(e) => handleClickBattle('Potion')} disabled={buttonDisabled} className="btn" id="button2">
                          Potion
                        </button>
                        <button onClick={(e) => handleClickBattle('Blast')} disabled={buttonDisabled} className="btn" id="button3">
                          Blast
                        </button>
                        <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn" id="button4">
                          Run
                        </button>
                      </div>
                }
                {role == "DPS" &&
                    <div className="btn-grid" id="option-buttons">
                        <button onClick={(e) => handleClickBattle('Stab')} disabled={buttonDisabled} className="btn" id="button1">
                          Stab
                        </button>
                        <button onClick={(e) => handleClickBattle('Potion')} disabled={buttonDisabled} className="btn" id="button2">
                          Potion
                        </button>
                        <button onClick={(e) => handleClickBattle('Steal')} disabled={buttonDisabled} className="btn" id="button3">
                          Steal
                        </button>
                        <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn" id="button4">
                          Run
                        </button>
                      </div>
                }
              </div>
            </div>
          </div>
        </div>
    );
}

export default BattleContainer;
