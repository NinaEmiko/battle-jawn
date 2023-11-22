import { useState } from "react";
import BattleContainer from "./BattleContainer";
import PlayerSelection from "./PlayerSelection";
import axios from "axios";

function HomePage() {
    const [beginBattle, setBeginBattle] = useState(false);
    const [heroId, setHeroId] = useState(0);

    const handlePlayerSelection = (id: number) => {
        setHeroId(id);

        axios.post('http://localhost:8080/api/battle-session', {
            heroId: id
            })
            .then((response) => {

            localStorage.setItem('battleSessionId', response.data.id);
            console.log("BattleSession created successfully: " + response.data.id);

            const enemyId = response.data.enemyId;
            localStorage.setItem('enemyId', enemyId);
            console.log("Enemy created successfully: " + response.data.enemyId);

            })
            .catch((error) => {
            console.error('Error fetching battleSession data:', error);
            });

        setBeginBattle(true);
    }

    return (
        <div>
            {beginBattle ? (
                <BattleContainer  props={heroId} />
            ):
                <PlayerSelection roleChosen={handlePlayerSelection} />
            }
        </div>

    )
}

export default HomePage;