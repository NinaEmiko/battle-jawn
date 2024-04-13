import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const PostBattle = ({props}:{props:any}) => {
    const [loot, setLoot] = useState<string[]>([]);
    const [lootActive, setLootActive] = useState(false);
    const [emptySlots, setEmptySlots] = useState(0);
    const [selectedOptions, setSelectedOptions] = useState<string[]>([]);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
      navigate(path);
    };
    
    function getLoot() {
        axios.get('http://localhost:8080/api/loot/' + props.enemyId)
        .then((lootResponse) => {
          setLoot(lootResponse.data);
          setLootActive(true);
        })
        .catch((error) => {
          console.error('Error fetching loot data: ', error)
        })
        console.log("Enemy ID: " + props.enemyId)
      }

      function getEmptySlots() {
        axios.get('http://localhost:8080/api/inventory/slots/' + props.heroId)
        .then((lootResponse) => {
          setEmptySlots(lootResponse.data);
        })
        .catch((error) => {
          console.error('Error fetching slot data: ', error)
        })
      }

      function selectLoot() {
        const selectedItems = JSON.stringify(selectedOptions);

        if (selectedOptions.length === 0) {
            handleNavigation("/leader-board")
        } else if (emptySlots === 0){
            alert("You have no more room in your inventory to pick up loot.")
            handleNavigation("/leader-board")
        } else if (emptySlots < selectedOptions.length){
            alert("You do not have room in your inventory for all the loot you've selected. Please unselect and try again.")
        } else {
            axios.post('http://localhost:8080/api/inventory/add/' + props.heroId, selectedItems, {
                headers: {
                'Content-Type': 'application/json'
                }})
            .then((lootResponse) => {
            })
            .catch((error) => {
            console.error('Error selecting loot data: ', error)
            })
            handleNavigation("/leader-board")
        }
      }

      function handleClickEndOfBattle() {
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
            <h1 className="title-jawn">{props.message}</h1>
            Select loot you wish to pick up:
            {loot.map ((item, index) =>
            <div key={index}>
                <input 
                    type="checkbox" 
                    onChange={() => handleSelect(item)}/>
                <label>{item}</label>
            </div>
            )}
            <button onClick={handleClickEndOfBattle} className="btn">OK</button>
        </div>
    );
  };
  
  export default PostBattle;
  