import { useState, useEffect } from "react";
import BattleContainer from "./BattleContainer";
import PlayerSelection from "./PlayerSelection";
import axios from "axios";

function HomePage() {
    const [beginBattle, setBeginBattle] = useState(false);
    const [roleHasBeenChosen, setRoleHasBeenChosen] = useState(false);

    const [ids, setIds] = useState({
        heroId: 0,
        enemyId: 0,
        battleSessionId: 0,
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
                enemyId: response.data.enemyId
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
        setIds((prevData) => ({...prevData, heroId: id}));
        setRoleHasBeenChosen(true);
    }

    return (
        <div>
            {beginBattle ? (
                <BattleContainer  props={ids} />
            ):
                <PlayerSelection roleChosen={handlePlayerSelection} />
            }
        </div>

    )
}

export default HomePage;