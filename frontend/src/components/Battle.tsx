import "../styling/Container.css";
import { useEffect, useState } from "react";
import "../styling/BattleContainer.css";
import wizard from "../assets/wizard.png";
import ninja from "../assets/ninja.png";
import athena from "../assets/athena.png";
import antibiotics from "../assets/antibiotics.png";
import wolf from "../assets/wolf.png";
import orc from "../assets/orc.png";
import ghost from "../assets/ghost.png";
import PostBattle from "./PostBattle";
import { heroMove, enemyMove, createNewBattleSession, fetchHero, fetchEnemy, fetchBattleHistoryMessage, endBattleSession } from "../api/api";

function Battle({props}:{props:any}) {
  const [battleSessionCreated, setBattleSessionCreated] = useState(false);
  const [sessionInitialized, setSessionInitialized] = useState(false);
  const [beginBattle, setBeginBattle] = useState(false);
  const [buttonDisabled, setButtonDisabled] = useState(false);
  const [battleSessionId, setBattleSessionId] = useState(0);
  const [enemyId, setEnemyId] = useState(0);
  const [role, setRole] = useState('');
  const [health, setHealth] = useState(1);
  const [maxHealth, setMaxHealth] = useState(0);
  const [enemyName, setEnemyName] = useState('');
  const [enemyHealth, setEnemyHealth] = useState(0);
  const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
  const [battleHistory, setBattleHistory] = useState<string[]>(["Retrieving battle history. Please wait."]);
  const [battleResult, setBattleResult] = useState("");
  const [postBattleActive, setPostBattleActive] = useState(false);
  const [postBattleObject, setPostBattleObject] = useState({
    message: "",
    enemyId: enemyId,
    heroId: props,
    ran: false

  })

  const handleEnemyMove = () => {
    let timeoutId: number | undefined | any;
    timeoutId = setTimeout(async () => {
      const data = await enemyMove(battleSessionId);
      setHealth(data.heroHealth);
      setEnemyHealth(data.enemyHealth);
      setBattleHistory(data.battleHistory);
      setButtonDisabled(false);
    }, 1500);
  }

  const handleClickBattle = async (move: string) => {
    setButtonDisabled(true);
    const data = await heroMove(move, battleSessionId)
    setHealth(data.heroHealth);
    setEnemyHealth(data.enemyHealth);
    setBattleHistory(data.battleHistory);
    handleEnemyMove();
  }

  const createNewBattleSessionCall = async () => {
    const data = await createNewBattleSession(props)
    setBattleSessionId(data.id);
    setEnemyId(data.enemyId);
    setBattleSessionCreated(true);
  }

  const fetchInitialData = async () => {

    const data = await fetchHero(props);
    setRole(data.role);
    setHealth(data.health);
    setMaxHealth(data.maxHealth);

    const enemyData = await fetchEnemy(enemyId);
    setEnemyName(enemyData.name);
    setEnemyHealth(enemyData.health);
    setEnemyMaxHealth(enemyData.maxHealth);

    const battleHistoryMessageData = await fetchBattleHistoryMessage(battleSessionId);
    setBattleHistory(battleHistoryMessageData);

    setSessionInitialized(true);
  }

  const processEndOfBattle = async () => {
    let heroRan = false;

    if (battleHistory.includes('You successfully ran away!') || battleHistory.includes('You have been defeated by the enemy!')) {
      heroRan = true;
    }
    
    const endBattleSessionData = await endBattleSession(battleSessionId, battleResult);      
      setPostBattleObject({
        message: endBattleSessionData,
        enemyId: enemyId,
        heroId: props,
        ran: heroRan
      })
  }

  useEffect(() => {
    if (!battleSessionCreated) {
      createNewBattleSessionCall();
    }
  },[])

  useEffect(() => {
    if (battleSessionCreated) {
      fetchInitialData();
    }
  }, [battleSessionCreated])

  useEffect(() => {
    if (sessionInitialized) {
      setBeginBattle(true);
    }
  }, [sessionInitialized])

  useEffect(() => {
    if (battleResult != "") {
      processEndOfBattle();
      setButtonDisabled(true);
      setPostBattleActive(true);
  }
    
  }, [battleResult])

  useEffect(() => {
    if(battleHistory) {
      if(battleHistory.includes('You have defeated the enemy!')) {
        setBattleResult("Hero wins");
      } else if (battleHistory.includes('You have been defeated by the enemy!')) {
        setBattleResult("Hero loses");
      } else if (battleHistory.includes('You successfully ran away!')) {
        setBattleResult("Hero runs");
      }
    }
  })

  return (
<>
<div className="background-jawn">
{beginBattle && !postBattleActive &&
    <div className="container-jawn-hero-battle">
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

      <progress className='healthBar' id="playerHealthBar" value={health} max={maxHealth}></progress>
      <div className="logBox-container">
        <div className="logBox" id="logBox">
          {battleHistory.slice().reverse().map((item, index) => (
        <div className="dialog" key={index}>
      {item}
      <br />
    </div>
  ))}
      </div>
      <div className="battle-btn-display">
          <div className="user-prompt-wrapper-battle">
            <div className="userPrompt-battle">{"What would you like to do?"}</div>

            {role == "Tank" &&
              <div className="btn-grid-roles" id="option-buttons">
                  <button onClick={(e) => handleClickBattle('Strike')} disabled={buttonDisabled} className="btn-role-battle" id="button1">
                    Strike
                  </button>
                  <button onClick={(e) => handleClickBattle('Potion')} disabled={buttonDisabled} className="btn-role-battle" id="button2">
                    Potion
                  </button>
                  <button onClick={(e) => handleClickBattle('Impale')} disabled={buttonDisabled} className="btn-role-battle" id="button3">
                    Impale
                  </button>
                  <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn-role-battle" id="button4">
                    Run
                  </button>
                </div>
            }
            {role == "Healer" &&
              <div className="btn-grid-roles" id="option-buttons">
                  <button onClick={(e) => handleClickBattle('Wand')} disabled={buttonDisabled} className="btn-role-battle" id="button1">
                    Wand
                  </button>
                  <button onClick={(e) => handleClickBattle('Heal')} disabled={buttonDisabled} className="btn-role-battle" id="button2">
                    Heal
                  </button>
                  <button onClick={(e) => handleClickBattle('Holy')} disabled={buttonDisabled} className="btn-role-battle" id="button3">
                    Holy
                  </button>
                  <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn-role-battle" id="button4">
                    Run
                  </button>
                </div>
            }
            {role == "Caster" &&
                <div className="btn-grid-roles" id="option-buttons">
                    <button onClick={(e) => handleClickBattle('Wand')} disabled={buttonDisabled} className="btn-role-battle" id="button1">
                      Wand
                    </button>
                    <button onClick={(e) => handleClickBattle('Potion')} disabled={buttonDisabled} className="btn-role-battle" id="button2">
                      Potion
                    </button>
                    <button onClick={(e) => handleClickBattle('Blast')} disabled={buttonDisabled} className="btn-role-battle" id="button3">
                      Blast
                    </button>
                    <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn-role-battle" id="button4">
                      Run
                    </button>
                  </div>
            }
            {role == "DPS" &&
                <div className="btn-grid-roles" id="option-buttons">
                    <button onClick={(e) => handleClickBattle('Stab')} disabled={buttonDisabled} className="btn-role-battle" id="button1">
                      Stab
                    </button>
                    <button onClick={(e) => handleClickBattle('Potion')} disabled={buttonDisabled} className="btn-role-battle" id="button2">
                      Potion
                    </button>
                    <button onClick={(e) => handleClickBattle('Steal')} disabled={buttonDisabled} className="btn-role-battle" id="button3">
                      Steal
                    </button>
                    <button onClick={(e) => handleClickBattle('Run')} disabled={buttonDisabled} className="btn-role-battle" id="button4">
                      Run
                    </button>
                  </div>
            }
          </div>
        </div>
      </div>
    </div>
    // :
    // <h1 className="title-jawn">Loading...</h1>
    }

    {postBattleActive && 
      <PostBattle props={postBattleObject} />
    }
</div>
    </>
  );
}

export default Battle;
