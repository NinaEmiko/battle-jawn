import { useEffect, useState } from "react";
import "../styling/PostBattle.css";
import { fetchLoot, fetchEmptySlots, selectLootCall } from "../api/api";

const PostBattle = ({props}:{props:any}) => {
    const [loot, setLoot] = useState<string[]>([]);
    const [lootActive, setLootActive] = useState(false);
    const [emptySlots, setEmptySlots] = useState(0);
    const [selectedOptions, setSelectedOptions] = useState<string[]>([]);
    
    const getLoot = async () => {
        if (props.postBattleObject.enemyId !== 0) {
            const data = await fetchLoot(props.postBattleObject.enemyId)
            setLoot(data);
            setLootActive(true);
        }
    }

    const getEmptySlots = async () => {
        const data = await fetchEmptySlots(props.postBattleObject.heroId)
        setEmptySlots(data);
    }

    const selectLoot = async () => {
        const selectedItems = JSON.stringify(selectedOptions);

        if (selectedOptions.length === 0) {
            props.handleExitPostBattleComponent()
        } else if (emptySlots === 0){
            alert("You have no more room in your inventory to pick up loot.")
            props.handleExitPostBattleComponent()
        } else if (emptySlots < selectedOptions.length){
            alert("You do not have room in your inventory for all the loot you've selected. Please unselect and try again.")
        } else {
            selectLootCall(props.postBattleObject.heroId, selectedItems)
            props.handleExitPostBattleComponent()
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

    useEffect(() => {
        if (!lootActive) {
            getEmptySlots();
            getLoot();
        }
    })

    return (
        <div>

            {props.postBattleObject.ran &&
                <h1 className="post-battle-text">You ran away.</h1>
            }

            {props.postBattleObject.lost &&
                <h1 className="post-battle-text">You have been defeated.</h1>
            }

            {props.postBattleObject.won &&
                <div>
                    <h1 className="title-jawn">{props.postBattleObject.message}</h1>
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

            <button onClick={handleClickEndOfBattle} className="btn">OK</button>
        </div>
    );
  };
  
  export default PostBattle;
  