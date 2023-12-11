import { useState, useEffect } from "react";
import Battle from "./Battle";
import PlayerSelection from "./PlayerSelection";
import axios from "axios";

function BattleContainer({props}:{props:any}) {

  console.log("User Account Id(BattleContainer): " + props.id);
    const [beginBattle, setBeginBattle] = useState(false);
    const [roleHasBeenChosen, setRoleHasBeenChosen] = useState(false);

    const [ids, setIds] = useState({
        heroId: 0,
        enemyId: 0,
        battleSessionId: 0,
        battleHistoryMessageId: 0,
    })

    useEffect(() => {
        if (roleHasBeenChosen && !beginBattle) {
          const createNewBattleSession = async () => {
            try {
              const response = await axios.post('http://localhost:8080/api/battle-session', {
                heroId: ids.heroId
              });
      
              setIds((prevData) => ({
                ...prevData,
                battleSessionId: response.data.id,
                enemyId: response.data.enemyId,
                battleHistoryMessageId: response.data.battleHistoryMessageId
              }));
      
              setBeginBattle(true);
            } catch (error) {
              console.error('Error fetching data:', error);
            }
          };
          createNewBattleSession();
        }
      }, [roleHasBeenChosen, beginBattle]);

    const handlePlayerSelection = (id: number) => {
        console.log("Inside handlePlayerSelection(BattleContainer. Id: " + id);
        setIds((prevData) => ({...prevData, heroId: id}));
        setRoleHasBeenChosen(true);
    }

    return (
        <div>
            {beginBattle ? (
                <Battle  props={ids} />
            ):
                <PlayerSelection
                roleChosen={handlePlayerSelection} userAccountId={props.id}
                />
            }
        </div>

    )
}

export default BattleContainer;