import { useState } from "react";
import Battle from "./Battle";
import Inventory from "./Inventory";
import Map from "./Map";
import "../styling/MyHeroes.css";
import Store from "./Store";
import Heroes from "./Heroes";

function MyHeroes( {props}:{props:any} ) {
  const [heroId, setHeroId] = useState(0);
  const [battleActive, setBattleActive] = useState(false);
  const [storeActive, setStoreActive] = useState(false);
  const [inventoryActive, setInventoryActive] = useState(false);
  const [mapActive, setMapActive] = useState(false);
  
  const handleSubComponentButtonClick = (component: string, id: number) => {
    if (component === "exit-btn-map"){
      setHeroId(id);
      setMapActive(false);
    } else if (component === "exit-store"){
      setHeroId(id);
      setStoreActive(false);
      setMapActive(true);
    } else if (component === "exit-inventory"){
      setHeroId(id);
      setInventoryActive(false);
      setMapActive(true);
    } else if (component === "open-store-map") {
      setHeroId(id);
      setMapActive(false);
      setStoreActive(true);
    } else if (component === "open-inventory-map") {
      setHeroId(id);
      setMapActive(false);
      setInventoryActive(true);
    } else if (component === "battle") {
      setHeroId(id);
      setMapActive(false);
      setBattleActive(true);
    } else if (component === "open-map") {
      setHeroId(id);
      setMapActive(true);
    }
  }

  return (
    <>
      <div className="home-background-jawn">
        {battleActive &&  
          <Battle  props={{heroId:heroId}} />
        }

        {storeActive &&
          <Store props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {inventoryActive &&
          <Inventory props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {mapActive &&
          <Map props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {!battleActive && !inventoryActive && !storeActive && !mapActive &&
          <Heroes props={{accountId:props.id, setIsVisible: handleSubComponentButtonClick}} />
        }

      </div>
    </>
  );
};

export default MyHeroes;
