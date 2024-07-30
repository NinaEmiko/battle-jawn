import { useState } from "react";
import Battle from "./Battle";
import Inventory from "./Inventory";
import Map from "./Map";
import Shop from "./Shop";
import Heroes from "./Heroes";
import TalentTree from "./TalentTree";
import CreateNewHero from "./CreateNewHero";

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
          <Battle props={{heroId:heroId, setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
        }

        {activeTab === "Shop" &&
          <Shop props={{heroId:heroId, setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
        }

        {activeTab === "Inventory" &&
          <Inventory props={{heroId:heroId, setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
        }

        {activeTab === "Map" &&
          <Map props={{heroId:heroId, setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
        }

        {activeTab === "Heroes" &&
          <Heroes props={{setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav }} />
        }

        {activeTab === "Talents" &&
          <TalentTree props={{id:heroId, setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
        }

        {activeTab === "New Hero" &&
          <CreateNewHero props={{setIsVisible: handleRedirect, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
        }
    </>
  );
};

export default Home;
