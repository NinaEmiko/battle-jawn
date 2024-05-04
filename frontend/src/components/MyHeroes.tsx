import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import classNames from 'classnames';
import Battle from "./Battle";
import Inventory from "./Inventory";
import Store from "./Store";
import "../styling/MyHeroes.css";
import PopUp from "./PopUp";
import { determineMaxExperience, determineNumerator } from "../helpers/experience_helper";
import { fetchHeroes, restHero, deleteHero } from "../api/api";

function MyHeroes( {props}:{props:any} ) {
  const [heroId, setHeroId] = useState(0);
  const [deleteHeroId, setDeleteHeroId] = useState(0);
  const [battleActive, setBattleActive] = useState(false);
  const [inventoryActive, setInventoryActive] = useState(false);
  const [storeActive, setStoreActive] = useState(false);
  const [heroList, setHeroList] = useState([]);
  const [popUpType, setPopUpType] = useState("");
  const [popUpContent, setPopUpContent] = useState("");
  const [showPopUp, setShowPopUp] = useState(false);
  const [currentHeroIndex, setCurrentHeroIndex] = useState(0);
  const [activeSession, setActiveSession] = useState(false);

  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  const fetchHeroesCall = async () => {
    const data = await fetchHeroes(props.id);
    setHeroList(data);
  }

  const handleRest = async (id: any) => {
    await restHero(id);
    fetchHeroesCall()
  }

  const handleOkButtonClick = () => {
    setShowPopUp(false);
  }

  const handleConfirmButtonClick = async () => {
    setShowPopUp(false);
    await deleteHero(deleteHeroId);
    fetchHeroesCall();
  }

  const handleFight = (id: any, health: number) => {
    if (heroList[currentHeroIndex].activeBattleSession){
      console.log("heroList[currentHeroIndex].activeBattleSession: " + heroList[currentHeroIndex].activeBattleSession)
      setActiveSession(true);
    }
    if (health > 0) {
      setHeroId(id);
      setBattleActive(true);
    } else {
      setPopUpType("jawn");
      setPopUpContent("You cannot fight! You have no health!");
      setShowPopUp(true);
    }
  }

  const handleInventory = (id: any) => {
    if (heroList[currentHeroIndex].activeBattleSession > 0) {
      setPopUpType("jawn");
      setPopUpContent("You must complete current battle before checking your inventory.");
      setShowPopUp(true);
    } else {
      setHeroId(id);
      setInventoryActive(true);
    }
  }

  const handleStore = (id: any) => {
    if (heroList[currentHeroIndex].activeBattleSession > 0) {
      setPopUpType("jawn");
      setPopUpContent("You cannot shop while in a fight.");
      setShowPopUp(true);
    } else {
      setHeroId(id);
      setStoreActive(true);
    }
  }

  const handleDeleteConfirmation = (id: any) => {
    setPopUpType("confirmation");
    setPopUpContent("delete hero");
    setShowPopUp(true);
    setDeleteHeroId(id);
    setCurrentHeroIndex(0);
  }
  
  const handleSubComponentButtonClick = (component: string) => {
    if (component === "store"){
      setStoreActive(false);
    } else if (component === "inventory") {
      setInventoryActive(false);
    }
  }

  const previousHero = () => {
    if(currentHeroIndex == 0) {
      setCurrentHeroIndex(heroList.length - 1)
    } else {
      setCurrentHeroIndex(currentHeroIndex - 1);
    }
  };

  const nextHero = () => {
    if(currentHeroIndex == heroList.length - 1) {
      setCurrentHeroIndex(0)
    } else {
      setCurrentHeroIndex(currentHeroIndex + 1);
    }
  };
    
  useEffect(() => {
    fetchHeroesCall();
  }, [inventoryActive, showPopUp])

  return (
    <>
      <div className="home-background-jawn">
        {battleActive &&  
          <Battle  props={heroId} activeSessionProp={activeSession} battleSessionProp={heroList[currentHeroIndex].activeBattleSession} />
        }

        {inventoryActive &&
          <Inventory props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {storeActive && 
          <Store props={{heroId:heroId, setIsVisible: handleSubComponentButtonClick}} />
        }

        {showPopUp &&
          <PopUp 
            props={{
                type: popUpType,
                content: popUpContent,
                onClickOk: handleOkButtonClick,
                onClickConfirm: handleConfirmButtonClick
            }} 
          />   
        }

        {!battleActive && !inventoryActive && !storeActive && !showPopUp &&

          <div className="container-jawn-hero">
            <div className="carousel-jawn">
            {heroList.length > 1 &&
              <button className={classNames('nav-link', 'btn', 'custom-button')} id="prev-btn" onClick={() => previousHero()}>Prev</button>
            }   
            <h1 className="title-jawn-hero">{props.userName} Heroes</h1>
            {heroList.length > 1 &&
              <button className={classNames('nav-link', 'btn', 'custom-button')} id="next-btn" onClick={() => nextHero()}>Next</button>
            }
            </div>
            {heroList.length < 5 &&
            <div className="btn-cntr">
            <button className="btn" id="new-hero-btn" onClick={() => handleNavigation('/create-hero')}>Create New Hero</button>
            </div>
            }

            {heroList.length > 0 &&

            <div className="">
          <div className="container-jawn-hero-card">
            <div className="hero-name-level">
              <div className="hero-name"> {heroList[currentHeroIndex].name} </div>
              <div className="hero-level"> Lvl {heroList[currentHeroIndex].level} {heroList[currentHeroIndex].role} </div>
            </div>
            
            <table className="my-heroes-table">
              <tbody>
                <tr>
                  <td className="row-jawn">Health:</td>
                  <td className="data-jawn" id="health-jawn">{heroList[currentHeroIndex].health} / {heroList[currentHeroIndex].maxHealth}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Coins:</td>
                  <td className="data-jawn" id="health-jawn">{heroList[currentHeroIndex].coins}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Current Streak:</td>
                  <td className="data-jawn">{heroList[currentHeroIndex].winStreak}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Wins:</td>
                  <td className="data-jawn">{heroList[currentHeroIndex].winCount}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Losses:</td>
                  <td className="data-jawn">{heroList[currentHeroIndex].lossCount}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Run Count:</td>
                  <td className="data-jawn">{heroList[currentHeroIndex].runCount}</td>
                </tr>
              </tbody>
            </table>
            <div className="experience-bar-container">
              <progress className='experience-bar' value={heroList[currentHeroIndex].experience} max={determineMaxExperience(heroList[currentHeroIndex].level)}></progress>
              <span className="experience-fraction">{determineNumerator(heroList[currentHeroIndex].level, heroList[currentHeroIndex].experience)}/{determineMaxExperience(heroList[currentHeroIndex].level)}</span>
            </div>
            <div className="row justify-content-center">
                  <button onClick={() => handleStore(heroList[currentHeroIndex].id)} className={classNames('nav-link', 'btn', 'custom-button')} id="store-btn">Store</button>
                  <button onClick={() => handleInventory(heroList[currentHeroIndex].id)} className={classNames('nav-link', 'btn', 'custom-button')} id="inventory-btn">Inventory</button>
                  <button onClick={() => handleFight(heroList[currentHeroIndex].id, heroList[currentHeroIndex].health)} className={classNames('nav-link', 'btn', 'custom-button')} id="fight-btn">Fight</button>
                  {import.meta.env.VITE_REACT_APP_URL == "http://localhost:8080" &&
                  <button onClick={() => handleRest(heroList[currentHeroIndex].id)} className={classNames('nav-link', 'btn', 'custom-button')} id="rest-btn">Rest</button>
                   }
                  <button onClick={() => handleDeleteConfirmation(heroList[currentHeroIndex].id)} className={classNames('nav-link', 'btn', 'custom-button')} id="delete-btn">Delete</button>
            </div>
            

            </div>
            
        </div>
}
            </div>
        }
      </div>
    </>
  );
};

export default MyHeroes;
