import { useState } from "react";
import BattleContainer from "./BattleContainer";
import PlayerSelection from "./PlayerSelection";

function HomePage() {
    const [roleChosen, setRoleChosen] = useState(false);
    const [heroId, setHeroId] = useState(0);

    const handlePlayerSelection = (id) => {
        setRoleChosen(true);
        setHeroId(id);

    }

    return (
        <div>
            {roleChosen ? (
                <BattleContainer id={heroId} />
            ):
                <PlayerSelection roleChosen={handlePlayerSelection} />
            }
        </div>

    )
}

export default HomePage;