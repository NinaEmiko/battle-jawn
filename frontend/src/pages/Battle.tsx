import "../styling/Container.css";
import { useEffect, useState } from "react";
import "../styling/BattleContainer.css";
import "../styling/PostBattle.css";
import { fetchBattleSession, heroMove, enemyMove, createNewBattleSession, fetchHero, fetchEnemy, fetchBattleHistoryMessage, endBattleSession, fetchLoot, fetchEmptySlots, selectLootCall } from "../api/api";
import EnemyIcon from "../components/Battle/EnemyIcon";
import HeroIcon from "../components/Battle/HeroIcon";
import LogBox from "../components/Battle/LogBox";
import { determineResourceIcon } from "../helpers/icon_helper";
import Container from "../components/Container";
import PageName from "../components/PageName";
import Display from "../components/Display";
import Controls from "../components/Controls";

function Battle({props}:{props:any}) {
  const [battleSessionCreated, setBattleSessionCreated] = useState(false);
  const [sessionInitialized, setSessionInitialized] = useState(false);
  const [beginBattle, setBeginBattle] = useState(false);
  const [buttonDisabled, setButtonDisabled] = useState(false);
  const [battleSessionId, setBattleSessionId] = useState(0);
  const [enemyId, setEnemyId] = useState(0);
  const [role, setRole] = useState('');
  const [heroName, setHeroName] = useState('')
  const [health, setHealth] = useState(1);
  const [maxHealth, setMaxHealth] = useState(0);
  const [resource, setResource] = useState(0);
  const [enemyName, setEnemyName] = useState('');
  const [enemyHealth, setEnemyHealth] = useState(0);
  const [enemyMaxHealth, setEnemyMaxHealth] = useState(0);
  const [enemyLevel, setEnemyLevel] = useState(0);
  const [battleHistory, setBattleHistory] = useState<string[]>(["Retrieving battle history. Please wait."]);
  const [battleResult, setBattleResult] = useState("");
  const [leftButtonTopText, setLeftButtonTopText] = useState("Potion");
  const [leftButtonCenterText, setLeftButtonCenterText] = useState("Water");
  const [leftButtonBottomText, setLeftButtonBottomText] = useState("Run");
  const [rightButtonLeftText, setRightButtonLeftText] = useState("");
  const [rightButtonCenterText, setRightButtonCenterText] = useState("");
  const [rightButtonRightText, setRightButtonRightText] = useState("");
  const [moveOne, setMoveOne] = useState("");
  const [moveTwo, setMoveTwo] = useState("");
  const [moveThree, setMoveThree] = useState("");
  const [loot, setLoot] = useState<string[]>([]);
  const [emptySlots, setEmptySlots] = useState(0);
  const [selectedOptions, setSelectedOptions] = useState<string[]>([]);
  const [postBattleActive, setPostBattleActive] = useState(false);
  const [postBattleMessage, setPostBattleMessage] = useState(false);

  //HANDLER FUNCTIONS

  const handleExitPostBattleComponent = () => {
    props.setIsVisible("Map",props.heroId);
  }

  const handleEnemyMove = () => {
    let timeoutId: number | undefined | any;
    timeoutId = setTimeout(async () => {
      const data = await enemyMove(battleSessionId);
      setHealth(data.heroHealth);
      setResource(data.heroResource);
      setEnemyHealth(data.enemyHealth);
      setBattleHistory(data.battleHistory);
      setButtonDisabled(false);
    }, 1500);
  }

  const handleClickBattle = async (move: string) => {
    if (!buttonDisabled){
      setButtonDisabled(true);
      const data = await heroMove(move, battleSessionId)
      setHealth(data.heroHealth);
      setResource(data.heroResource);
      setEnemyHealth(data.enemyHealth);
      setBattleHistory(data.battleHistory);
      handleEnemyMove();
    }
  }

  const handleClickCenterDirectionButton = () => {
    if (rightButtonCenterText === "OK") {
      handleClickEndOfBattle()
    } else {
      handleClickBattle(moveTwo);
    }
  }

  const handleClickEndOfBattle = () => {
    if (selectedOptions) {
        selectLoot();
    }
  }

  const handleSelect = (option: string) => {
    setSelectedOptions(prevOptions => {
        if (prevOptions.includes(option)) {
            return prevOptions.filter(item => item !== option);
        } else {
            return [...prevOptions, option];
        }
    });
  }

  //API CALLS

  const getLoot = async () => {
    if (enemyId !== 0) {
        const data = await fetchLoot(enemyId)
        setLoot(data);
    }
  }

  const getEmptySlots = async () => {
    const data = await fetchEmptySlots(props.heroId)
    setEmptySlots(data);
  }

  const selectLoot = async () => {
    const selectedItems = JSON.stringify(selectedOptions);

    if (selectedOptions.length === 0) {
        handleExitPostBattleComponent()
    } else if (emptySlots === 0){
        alert("You have no more room in your inventory to pick up loot.")
        handleExitPostBattleComponent()
    } else if (emptySlots < selectedOptions.length){
        alert("You do not have room in your inventory for all the loot you've selected. Please unselect and try again.")
    } else {
        selectLootCall(props.heroId, selectedItems)
        handleExitPostBattleComponent()
    }
  }

  const fetchInitialHeroData = async () => {
    const data = await fetchHero(props.heroId);
    setHeroName(data.name);
    setRole(data.role);
    setHealth(data.health);
    setMaxHealth(data.maxHealth);
    setResource(data.resource);
    if(data.activeBattleSession != null) {
      fetchBattleSessionCall(data.activeBattleSession);
    } else {
      createNewBattleSessionCall();
    }
  }

  const createNewBattleSessionCall = async () => {
    const data = await createNewBattleSession(props.heroId)
    setBattleSessionId(data.id);
    setEnemyId(data.enemyId);
    setBattleSessionCreated(true);
  }

  const fetchBattleSessionCall = async (id: number) => {
    const data = await fetchBattleSession(id)
    setBattleSessionId(data.id);
    setEnemyId(data.enemyId);
    setBattleSessionCreated(true);
  }

  const fetchInitialData = async () => {

    const enemyData = await fetchEnemy(enemyId);
    setEnemyName(enemyData.name);
    setEnemyHealth(enemyData.health);
    setEnemyMaxHealth(enemyData.maxHealth);
    setEnemyLevel(enemyData.level);

    const battleHistoryMessageData = await fetchBattleHistoryMessage(battleSessionId);
    setBattleHistory(battleHistoryMessageData);

    setSessionInitialized(true);
  }
  const setPostBattleButtons = () => {
    setLeftButtonTopText("")
    setLeftButtonCenterText("")
    setLeftButtonBottomText("")
    setRightButtonLeftText("")
    setRightButtonCenterText("OK")
    setRightButtonRightText("")
  }

  const processEndOfBattle = async (result: string) => {  
    setBattleResult(result);
    setButtonDisabled(true);
    setPostBattleActive(true);
    setPostBattleButtons();
    getEmptySlots();
    getLoot();
    const endBattleSessionData = await endBattleSession(battleSessionId, result);      
    setPostBattleMessage(endBattleSessionData);
  }

  //BATTLE INITIALIZATION

  //Fetch hero data on mount
  useEffect(() => {
    fetchInitialHeroData();
  },[])

  //Once battle session has been established, fetch enemy and battle session data
  useEffect(() => {
    if (battleSessionCreated) {
      fetchInitialData();
    }
  }, [battleSessionCreated])

  //Once required data has been set, initialize battle
  useEffect(() => {
    if (sessionInitialized) {
      setBeginBattle(true);
      setButtonDisabled(false);
    }
  }, [sessionInitialized])

  //BATTLE COMPLETION

  //Set buttons

  //Set battle result to end battle
  //
  useEffect(() => {
    if(battleHistory && !postBattleActive) {
      if(battleHistory.includes('You have defeated the enemy!')) {
        processEndOfBattle("Hero wins");
      } else if (battleHistory.includes('You have been defeated by the enemy!')) {
        processEndOfBattle("Hero loses");
      } else if (battleHistory.includes('You successfully ran away!')) {
        processEndOfBattle("Hero runs");
      }
    }
  })

  //USE EFFECT

  useEffect(()=> {
    if (role === "Tank") {
      setMoveOne("Strike")
      setRightButtonLeftText("Strike")
      setMoveTwo("Impale")
      setRightButtonCenterText("Impale")
      setMoveThree("Block")
      setRightButtonRightText("Block")
    } else if (role === "Healer") {
      setMoveOne("Wand")
      setRightButtonLeftText("Wand")
      setMoveTwo("Holy")
      setRightButtonCenterText("Holy")
      setMoveThree("Heal")
      setRightButtonRightText("Heal")
    } else if (role === "DPS") {
      setMoveOne("Stab")
      setRightButtonLeftText("Stab")
      setMoveTwo("BackStab")
      setRightButtonCenterText("BackStab")
      setMoveThree("Steal")
      setRightButtonRightText("Steal")
    } else if (role === "Caster") {
      setMoveOne("Wand")
      setRightButtonLeftText("Wand")
      setMoveTwo("FireBlast")
      setRightButtonCenterText("FireBlast")
      setMoveThree("IceBlast")
      setRightButtonRightText("IceBlast")
    }
  }, [role])

  return (
    <Container>
      <PageName props={"Battle"} />
      <Display>
        {beginBattle && !postBattleActive &&
          <div className="battle-container-jawn">
            <div className="enemy-display">
              <div className="enemy-display-left">
                <EnemyIcon enemyNameProp={enemyName} />
              </div>
              <div className="enemy-display-right">
                <div className="enemy-display-left-top-half">
                  {enemyName}
                  <div className="enemy-level"> Lvl. {enemyLevel} </div>
                </div>
                <div className="enemy-display-left-bottom-half">
                  <progress className="healthBar" id="enemyHealthBar" value={enemyHealth} max={enemyMaxHealth} />
                </div>
              </div>
            </div>

            <div className="enemy-display">
              <div className="enemy-display-left">
                <HeroIcon heroNameProp={role} />
              </div>
              <div className="enemy-display-right">
                <div className="enemy-display-left-top-half">
                  <span className="enemy-display-right-top-half-name">{heroName}</span>
                  {determineResourceIcon(role, resource)}
                </div>
                <div className="enemy-display-left-bottom-half">
                  <progress className='healthBar' id="playerHealthBar" value={health} max={maxHealth}></progress>
                </div>
              </div>
            </div>

            <div className="logBox-container">
              <LogBox battleHistoryProp={battleHistory} />
            </div>
          </div>
        }

        {postBattleActive && 
                <div>

                {battleResult === "Hero runs" &&
                    <h1 className="post-battle-text">You ran away.</h1>
                }
    
                {battleResult === "Hero loses" &&
                    <h1 className="post-battle-text">You have been defeated.</h1>
                }
    
                {battleResult === "Hero wins" &&
                    <div>
                        <h1 className="title-jawn">{postBattleMessage}</h1>
                        <p className="select-text">Select loot you wish to pick up:</p>
                        {loot.map ((item, index) =>
                            <div className="loot-jawn" key={index}>
                                <input 
                                    type="checkbox" 
                                    onChange={() => handleSelect(item)}/>
                                <label className="label-jawn">{item}</label>
                            </div>
                        )}
                    </div>
                }
              </div>
        }
      </Display>
      <Controls
        handleClickLeftBtnTop={() => handleClickBattle("Potion")}
        leftBtnTopText={leftButtonTopText}
        handleClickLeftBtnMiddle={() => handleClickBattle("Water")}
        leftBtnMiddleText={leftButtonCenterText}
        handleClickLeftBtnBottom={() => handleClickBattle("Run")}
        leftBtnBottomText={leftButtonBottomText}
        handleClickRightBtnLeft={() => handleClickBattle(moveOne)}
        rightBtnLeftText={rightButtonLeftText}
        handleClickRightBtnCenter={() => handleClickCenterDirectionButton()}
        rightBtnCenterText={rightButtonCenterText}
        handleClickRightBtnRight={() => handleClickBattle(moveThree)}
        rightBtnRightText={rightButtonRightText}
      />
    </Container>
  );
}

export default Battle;
