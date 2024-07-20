import { useState } from "react";
import Battle from "./Battle";
import Inventory from "./Inventory";
import Map from "./Map";
import Shop from "./Shop";
import Heroes from "./Heroes";

function Home( {props}:{props:any} ) {
  const [heroId, setHeroId] = useState(0);
  const [activeTab, setActiveTab] = useState("Heroes");
  
  const handleRedirect = (component: string, id: number) => {
    setHeroId(id);
    setActiveTab(component);
  }

  return (
    <>
        {activeTab === "Battle" &&  
          <Battle props={{heroId:heroId, setIsVisible: handleRedirect}} />
        }

        {activeTab === "Shop" &&
          <Shop props={{heroId:heroId, setIsVisible: handleRedirect}} />
        }

        {activeTab === "Inventory" &&
          <Inventory props={{heroId:heroId, setIsVisible: handleRedirect}} />
        }

        {activeTab === "Map" &&
          <Map props={{heroId:heroId, setIsVisible: handleRedirect}} />
        }

        {activeTab === "Heroes" &&
          <Heroes props={{accountId:props.id, setIsVisible: handleRedirect}} />
        }

        {activeTab === "Talents" &&
          <Heroes props={{accountId:props.id, setIsVisible: handleRedirect}} />
        }
    </>
  );
};

export default Home;
