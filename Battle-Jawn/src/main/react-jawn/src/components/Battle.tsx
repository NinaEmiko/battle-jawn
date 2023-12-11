import "../styling/Container.css";
import { useEffect, useState } from "react";
import axios from "axios";
import "../styling/BattleContainer.css";
import "../styling/UserPromptText.css";
import healthPotion from "../assets/healthPotion.png";
import wizard from "../assets/wizard.png";
import ninja from "../assets/ninja.png";
import athena from "../assets/athena.png";
import antibiotics from "../assets/antibiotics.png";
import wolf from "../assets/wolf.png";
import orc from "../assets/orc.png";
import ghost from "../assets/ghost.png";
import { useNavigate } from "react-router-dom";

function Battle({props}:{props:any}) {
  const [battleIdSet, setBattleIdSet] = useState(false);
  const [beginBattle, setBeginBattle] = useState(false);
  const [battleSessionId, setBattleSessionId] = useState(0);
  const [enemyId, setEnemyId] = useState(0);
  const [battleHistoryMessageId, setBattleHistoryMessageId] = useState(0);
  const [role, setRole] = useState('');
  const [health, setHealth] = useState(1);
  const [maxHealth, setMaxHealth] = useState(0);
  const [potionCount, setPotionCount] = useState(0);
  const [enemyName, setEnemyName] = useState('');
  const [enemyHealth, setEnemyHealth] = useState(1);
  const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
  const [battleHistory, setBattleHistory] = useState<string[]>([]);
  const [gameOver, setGameOver] = useState(false);
  const [buttonDisabled, setButtonDisabled] = useState(false);

  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  useEffect(() => {
    if (!battleIdSet){
    const createNewBattleSession = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/battle-session', {
          heroId: props
          
        });
        setBattleSessionId(response.data.id);
        setEnemyId(response.data.enemyId);
        setBattleHistoryMessageId(response.data.battleHistoryMessageId)

      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    createNewBattleSession();
    setBattleIdSet(true);
  }
  },[props, battleSessionId, enemyId, battleHistoryMessageId, battleIdSet])

  useEffect(() => {

  if (battleIdSet) {
    const fetchHero = async () => {
      try {
        const response = await
        axios.get('http://localhost:8080/api/hero/' + props)
        setRole(response.data.role);
        setHealth(response.data.health);
        setMaxHealth(response.data.maxHealth);
        setPotionCount(response.data.potions)
        console.log("Inside Battle. Response: " + response.data);
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
        axios.get('http://localhost:8080/api/battle-history-message/' + battleSessionId)
        setBattleHistory(response.data);
      } catch (error) {
        console.error('Error fetching Battle History data: ', error)
      }
    }
          
    fetchHero();
    fetchEnemy();
    fetchBattleHistory();
    setBeginBattle(true);
  }
  }, [props, role, health, maxHealth, potionCount, enemyName, enemyHealth, enemyMaxHealth, battleHistory, beginBattle, battleIdSet])

  function handleEnemyMove() {
    console.log("Inside handleEnemyMove: " + battleSessionId)

    let timeoutId: number | undefined;
    timeoutId = setTimeout(() => {
      axios.post('http://localhost:8080/api/enemy-move', {
        battleSessionId: battleSessionId
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
      battleSessionId: battleSessionId
        })
    .then((response) => {
      setHealth(response.data.heroHealth);
      setPotionCount(response.data.potionCount);
      setEnemyHealth(response.data.enemyHealth);
      setBattleHistory(response.data.battleHistory);
      setGameOver(response.data.gameOver);
    })
    .catch((error) => {
    console.error('Error occurred while trying to use: ' + move + " ", error);
    });

    handleEnemyMove();
  }

  if (battleHistory.includes('You have defeated the enemy!')||
  battleHistory.includes('You have been defeated by the enemy!')||
  battleHistory.includes('You successfully ran away!')) {
    setButtonDisabled(true);
    handleNavigation('/account-settings');
  }

  return (
<>
{beginBattle ?
    <div className="battle-container">
      <div className="name" id="enemyName">
      {enemyName == "Wolf" && 
        <img className="role-icon" src={wolf}></img>
        }
        {enemyName == "Orc" && 
        <img className="role-icon" src={orc}></img>
        }
        {enemyName == "Spirit" && 
        <img className="role-icon" src={ghost}></img>
        }
        {enemyName == "Thief" && 
        <img className="role-icon" src={ninja}></img>
        }
        
        
        {enemyName}</div>
      <progress className="healthBar" id="enemyHealthBar" value={enemyHealth} max={enemyMaxHealth} />
      <div className="name" id="playerName">
        {role == "Tank" && 
        <img className="role-icon" src={athena}></img>
        }
        {role == "Healer" && 
        <img className="role-icon" src={antibiotics}></img>
        }
        {role == "Caster" && 
        <img className="role-icon" src={wizard}></img>
        }
        {role == "DPS" && 
        <img className="role-icon" src={ninja}></img>
        }
        
        
        {role}</div>
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
    :
    <h1 className="title-jawn">Loading...</h1>
    }
    </>
  );
}

export default Battle;
