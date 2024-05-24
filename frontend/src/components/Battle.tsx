import "../styling/Container.css";
import { useEffect, useState } from "react";
import "../styling/BattleContainer.css";
import PostBattle from "./PostBattle";
import { fetchBattleSession, heroMove, enemyMove, createNewBattleSession, fetchHero, fetchEnemy, fetchBattleHistoryMessage, endBattleSession } from "../api/api";
import HeroMove from "./HeroMove";
import EnemyIcon from "./EnemyIcon";
import HeroIcon from "./HeroIcon";
import LogBox from "./LogBox";
import { determineResourceIcon } from "../helpers/icon_helper";

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
  const [postBattleActive, setPostBattleActive] = useState(false);
  const [postBattleObject, setPostBattleObject] = useState({
    message: "",
    enemyId: enemyId,
    heroId: props,
    ran: false,
    lost: false,
    won: false

  })

  //HANDLER FUNCTIONS

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
    setButtonDisabled(true);
    const data = await heroMove(move, battleSessionId)
    setHealth(data.heroHealth);
    setResource(data.heroResource);
    setEnemyHealth(data.enemyHealth);
    setBattleHistory(data.battleHistory);
    handleEnemyMove();
  }

  //API CALLS

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

  const processEndOfBattle = async () => {
    let heroRan = false;
    let heroLost = false;
    let heroWon = false;

    if (battleHistory.includes('You successfully ran away!')) {
      heroRan = true;
    } else if (battleHistory.includes('You have been defeated by the enemy!')) {
      heroLost = true;
    } else {
      heroWon = true;
    }
    
    const endBattleSessionData = await endBattleSession(battleSessionId, battleResult);      
      setPostBattleObject({
        message: endBattleSessionData,
        enemyId: enemyId,
        heroId: props.heroId,
        ran: heroRan,
        lost: heroLost,
        won: heroWon
      })
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
    }
  }, [sessionInitialized])

  //BATTLE COMPLETION

  //Set battle result to end battle
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

  //Once battle result has been set, end battle
  useEffect(() => {
    if (battleResult != "") {
      processEndOfBattle();
      setButtonDisabled(true);
      setPostBattleActive(true);
  }
  }, [battleResult])

  return (
    <>
      <div className="background-jawn">
        {beginBattle && !postBattleActive &&
          <div className="container-jawn-hero-battle">


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
              <HeroMove roleProp={role} buttonDisabledProp={buttonDisabled} handleClickBattleProp={handleClickBattle} />
            </div>
          </div>
        }

        {postBattleActive && 
          <PostBattle props={postBattleObject} />
        }
      </div>
    </>
  );
}

export default Battle;
