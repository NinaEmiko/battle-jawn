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
    setHeroId(id);
    setInventoryActive(true);
  }

  const handleStore = (id: any) => {
    setHeroId(id);
    setStoreActive(true);
  }

  const handleDeleteConfirmation = (id: any) => {
    setPopUpType("confirmation");
    setPopUpContent("delete hero");
    setShowPopUp(true);
    setDeleteHeroId(id);
  }
  
  const handleSubComponentButtonClick = (component: string) => {
    if (component === "store"){
      setStoreActive(false);
    } else if (component === "inventory") {
      setInventoryActive(false);
    }
  }
    
  useEffect(() => {
    fetchHeroesCall();
  }, [inventoryActive, showPopUp])

  return (
    <>
      <div className="home-background-jawn">
        {battleActive &&  
              <Battle  props={heroId} />
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
            <h1 className="title-jawn-hero">{props.userName} Heroes</h1>
            {heroList.length < 5 &&
            <div className="btn-cntr">
            <button className="btn" id="new-hero-btn" onClick={() => handleNavigation('/create-hero')}>Create New Hero</button>
            </div>
            }
            <div className="">
            {heroList.map((hero) => (
          <div className="container-jawn-hero-card" key={hero.id}>
            <div className="hero-name-level">
              <div className="hero-name"> {hero.name} </div>
              <div className="hero-level"> Lvl {hero.level} {hero.role} </div>
            </div>
            
            <table className="my-heroes-table">
              <tbody>
                <tr>
                  <td className="row-jawn">Health:</td>
                  <td className="data-jawn" id="health-jawn">{hero.health} / {hero.maxHealth}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Coins:</td>
                  <td className="data-jawn" id="health-jawn">{hero.coins}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Wins:</td>
                  <td className="data-jawn">{hero.winCount}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Losses:</td>
                  <td className="data-jawn">{hero.lossCount}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Run Count:</td>
                  <td className="data-jawn">{hero.runCount}</td>
                </tr>
              </tbody>
            </table>
            <div className="experience-bar-container">
              <progress className='experience-bar' value={hero.experience} max={determineMaxExperience(hero.level)}></progress>
              <span className="experience-fraction">{determineNumerator(hero.level, hero.experience)}/{determineMaxExperience(hero.level)}</span>
            </div>
            <div className="row justify-content-center">
                  <button onClick={() => handleStore(hero.id)} className={classNames('nav-link', 'btn', 'custom-button')} id="store-btn">Store</button>
                  <button onClick={() => handleInventory(hero.id)} className={classNames('nav-link', 'btn', 'custom-button')} id="inventory-btn">Inventory</button>
                  <button onClick={() => handleFight(hero.id, hero.health)} className={classNames('nav-link', 'btn', 'custom-button')} id="fight-btn">Fight</button>
                  {import.meta.env.VITE_REACT_APP_URL == "http://localhost:8080" &&
                  <button onClick={() => handleRest(hero.id)} className={classNames('nav-link', 'btn', 'custom-button')} id="rest-btn">Rest</button>
                   }
                  <button onClick={() => handleDeleteConfirmation(hero.id)} className={classNames('nav-link', 'btn', 'custom-button')} id="delete-btn">Delete</button>
            </div>
              

            </div>
        ))}
        </div>
            </div>
        }
      </div>
    </>
  );
};

export default MyHeroes;
