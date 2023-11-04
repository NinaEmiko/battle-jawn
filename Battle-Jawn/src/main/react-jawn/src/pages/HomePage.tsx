import { useState } from "react";
import BattleContainer from "./BattleContainer";
import PlayerSelection from "./PlayerSelection";

function HomePage() {
    const [heroId, setHeroId] = useState(0);

    const handlePlayerSelection = (id: any) => {
        setHeroId(id);
    }

    return (
        <div>
            {heroId ? (
                <BattleContainer props={heroId} />
            ):
                <PlayerSelection roleChosen={handlePlayerSelection} />
            }
        </div>

    )
}

export default HomePage;