import "../styling/Container.css";
import { useEffect, useState } from "react";
import axios from "axios";
import "../styling/BattleContainer.css";
import wizard from "../assets/wizard.png";
import ninja from "../assets/ninja.png";
import athena from "../assets/athena.png";
import antibiotics from "../assets/antibiotics.png";
import wolf from "../assets/wolf.png";
import orc from "../assets/orc.png";
import ghost from "../assets/ghost.png";
import PostBattle from "./PostBattle";

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

  function handleEnemyMove() {
    let timeoutId: number | undefined;
    timeoutId = setTimeout(() => {
      axios.post('http://localhost:8080/api/enemy-move', {
        battleSessionId: battleSessionId
          })
        .then(response => {
            setHealth(response.data.heroHealth);
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
        setEnemyHealth(response.data.enemyHealth);
        setBattleHistory(response.data.battleHistory);
    })
    .catch((error) => {
    console.error('Error occurred while trying to use: ' + move + " ", error);
    });

    handleEnemyMove();
  }

  const createNewBattleSession = () => {
      
    axios.post('http://localhost:8080/api/battle-session/create', {
        heroId: props
        
      })
      .then((response) => {
        setBattleSessionId(response.data.id);
        setEnemyId(response.data.enemyId);

        setBattleSessionCreated(true);
      })
    .catch((error) => {
      console.error('Error fetching data:', error);
    }
    )
  }

  const fetchInitialData = () => {

    axios.get('http://localhost:8080/api/hero/' + props)
    .then((heroResponse) => {
      setRole(heroResponse.data.role);
      setHealth(heroResponse.data.health);
      setMaxHealth(heroResponse.data.maxHealth);
    })
    .catch((error) => {
      console.error('Error fetching hero data: ', error)
    })

    axios.get('http://localhost:8080/api/enemy/' + enemyId)
    .then((enemyResponse) => {
      setEnemyName(enemyResponse.data.name);
      setEnemyHealth(enemyResponse.data.health);
      setEnemyMaxHealth(enemyResponse.data.maxHealth);
    })
    .catch((error) => {
      console.error('Error fetching enemy data: ', error)
    })

    axios.get('http://localhost:8080/api/battle-history-message/' + battleSessionId)
    .then((battleHistoryResponse) => {
      setBattleHistory(battleHistoryResponse.data);
    })
    .catch((error) => {
      console.error('Error fetching battle history data: ', error)
    })

    setSessionInitialized(true);
}

  useEffect(() => {
    if (!battleSessionCreated) {
      createNewBattleSession();
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

    const processEndOfBattle = () => {
      let heroRan = false;

      if (battleHistory.includes('You successfully ran away!') || battleHistory.includes('You have been defeated by the enemy!')) {
        heroRan = true;
      }
      
      axios.post('http://localhost:8080/api/battle-session/end', {
          battleSessionId: battleSessionId, 
          battleResult: battleResult
          
        })
        .then((response) => {          
          setPostBattleObject({
            message: response.data,
            enemyId: enemyId,
            heroId: props,
            ran: heroRan
          })
          
        })
      .catch((error) => {
        console.error('Error processing end of battle:', error);
      }
      )
    }

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
        <div key={index}>
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