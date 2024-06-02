import { useState } from "react";
import Battle from "./Battle";
import Inventory from "./Inventory";
import Map from "./Map";
import Shop from "./Shop";
import Heroes from "./Heroes";

function Home( {props}:{props:any} ) {
  const [heroId, setHeroId] = useState(0);
  const [battleActive, setBattleActive] = useState(false);
  const [shopActive, setShopActive] = useState(false);
  const [inventoryActive, setInventoryActive] = useState(false);
  const [mapActive, setMapActive] = useState(false);
  const [prevScreen, setPrevScreen] = useState('Heroes');
  
  const handleSubComponentButtonClick = (component: string, id: number) => {
    if (component === "exit-map"){
      setHeroId(id);
      setMapActive(false);
    } else if (component === "exit-store"){
      setHeroId(id);
      setPrevScreen('Shop')
      setShopActive(false);
      setMapActive(true);
    } else if (component === "exit-inventory"){
      setHeroId(id);
      setPrevScreen('Inventory')
      setInventoryActive(false);
      setMapActive(true);
    } else if (component === "open-store") {
      setHeroId(id);
      setMapActive(false);
      setShopActive(true);
    } else if (component === "open-inventory") {
      setHeroId(id);
      setMapActive(false);
      setInventoryActive(true);
    } else if (component === "open-battle") {
      setHeroId(id);
      setMapActive(false);
      setBattleActive(true);
    } else if (component === "open-map") {
      setHeroId(id);
      setMapActive(true);
    } else if (component === "open-map-post-battle") {
      setHeroId(id);
      setBattleActive(false);
      setPrevScreen('Battle');
      setMapActive(true);
    }
  }

  return (
    <>
        {battleActive &&  
          <Battle  props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {shopActive &&
          <Shop props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {inventoryActive &&
          <Inventory props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {mapActive &&
          <Map props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick, prevScreen: prevScreen}} />
        }

        {!battleActive && !inventoryActive && !shopActive && !mapActive &&
          <Heroes props={{accountId:props.id, setIsVisible: handleSubComponentButtonClick}} />
        }
    </>
  );
};

export default Home;
