import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styling/PostBattle.css";
import { fetchLoot, fetchEmptySlots, selectLootCall } from "../api/api";

const PostBattle = ({props}:{props:any}) => {
    const [loot, setLoot] = useState<string[]>([]);
    const [lootActive, setLootActive] = useState(false);
    const [emptySlots, setEmptySlots] = useState(0);
    const [selectedOptions, setSelectedOptions] = useState<string[]>([]);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
      navigate(path);
    };
    
    const getLoot = async () => {
        if (props.enemyId !== 0) {
            const data = await fetchLoot(props.enemyId)
            setLoot(data);
            setLootActive(true);
        }
    }

    const getEmptySlots = async () => {
        const data = await fetchEmptySlots(props.heroId)
        setEmptySlots(data);
    }

    const selectLoot = async () => {
        const selectedItems = JSON.stringify(selectedOptions);

        if (selectedOptions.length === 0) {
            handleNavigation("/leader-board")
        } else if (emptySlots === 0){
            alert("You have no more room in your inventory to pick up loot.")
            handleNavigation("/leader-board")
        } else if (emptySlots < selectedOptions.length){
            alert("You do not have room in your inventory for all the loot you've selected. Please unselect and try again.")
        } else {
            selectLootCall(props.heroId, selectedItems)
            handleNavigation("/leader-board")
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
        <div className="container-jawn-login-form">

            {props.ran ? (
                <h1 className="post-battle-text">You ran away.</h1>
            ) : (
                <div>
                    <h1 className="title-jawn">{props.message}</h1>
                    <p className="select-text">Select loot you wish to pick up:</p>
                    {loot.map ((item, index) =>
                        <div key={index}>
                            <input 
                                type="checkbox" 
                                onChange={() => handleSelect(item)}/>
                            <label className="label-jawn">{item}</label>
                        </div>
                    )}
                </div>
            )}

            <button onClick={handleClickEndOfBattle} className="btn">OK</button>
        </div>
    );
  };
  
  export default PostBattle;
  