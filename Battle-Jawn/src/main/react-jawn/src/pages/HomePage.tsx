import { useState } from "react";
import BattleContainer from "./BattleContainer";
import PlayerSelection from "./PlayerSelection";
import axios from "axios";

function HomePage() {
    const [beginBattle, setBeginBattle] = useState(false);

    const [ids, setIds] = useState({
        heroId: 0,
        enemyId: 0,
        battleSessionId: 0,
    })

    const handlePlayerSelection = (id: number) => {

        setIds((prevData) => ({...prevData, heroId: id}));

        axios.post('http://localhost:8080/api/battle-session', {
            heroId: id
            })
            .then((response) => {

            setIds((prevData) => ({...prevData, battleSessionId: response.data.id}));
            setIds((prevData) => ({...prevData, enemyId: response.data.enemyId}));

            })
            .catch((error) => {
            console.error('Error fetching Battle Session data:', error);
            });

        setBeginBattle(true);
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