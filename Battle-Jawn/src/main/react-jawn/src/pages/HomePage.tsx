import { useState } from "react";
import BattleContainer from "./BattleContainer";
import PlayerSelection from "./PlayerSelection";
import axios from "axios";


function HomePage() {
    const [beginBattle, setBeginBattle] = useState(false);
    const [heroId, setHeroId] = useState(0);

    const handlePlayerSelection = (heroId: number) => {
        setHeroId(heroId);
        setBeginBattle(true);

        axios.post('http://localhost:8080/api/enemy')
            .then((response) => {
            const enemyId = response.data.id;
            localStorage.setItem('enemyId', enemyId);
            console.log("Enemy created successfully: " + response.data.id);
            })
            .catch((error) => {
            console.error('Error fetching enemy data:', error);
            });

        axios.post('http://localhost:8080/api/battle-history')
            .then((response) => {
            const battleHistoryId = response.data.id;
            localStorage.setItem('battleHistoryId', battleHistoryId);
            console.log("BattleHistory created successfully: " + response.data.id);
            })
            .catch((error) => {
            console.error('Error fetching battleHistory data:', error);
            });

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