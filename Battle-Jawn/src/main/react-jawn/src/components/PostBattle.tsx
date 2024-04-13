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
      }

      function getEmptySlots() {
        axios.get('http://localhost:8080/api/slots/' + props.heroId)
        .then((lootResponse) => {
          setEmptySlots(lootResponse.data);
        })
        .catch((error) => {
          console.error('Error fetching slot data: ', error)
        })
      }

      function selectLoot() {
        const selectedItems = JSON.stringify(selectedOptions);
        console.log("Empty slots: " + emptySlots)
        console.log("selectedOptions.length: " + selectedOptions.length)
            if (selectedOptions.length == 0) {
                // handleNavigation("/leader-board")
            } else if (emptySlots >= selectedOptions.length) {
            axios.post('http://localhost:8080/api/inventory/add/' + props.heroId, selectedOptions, {
                headers: {
                'Content-Type': 'application/json'
                }})
            .then((lootResponse) => {
            console.log(lootResponse.data);
            })
            .catch((error) => {
            console.error('Error selecting loot data: ', error)
            })
            handleNavigation("/leader-board")
        } else {
            alert("You do not have enough room in your inventory.")
        }
      }

      function handleClickEndOfBattle() {
        if (selectedOptions) {
            selectLoot();
        }


        handleNavigation('/leader-board');
      }

      const handleSelect = (option: string) => {
        setSelectedOptions(prevOptions => {
            if (prevOptions.includes(option)) {
              return prevOptions.filter(item => item !== option); // Deselect the option
            } else {
              return [...prevOptions, option]; // Select the option
            }
          });
      }

      useEffect(() => {
        console.log("selected items: " + selectedOptions)
      },[selectedOptions])

      useEffect(() => {
        if (!lootActive) {
            getLoot();
            getEmptySlots();
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
  