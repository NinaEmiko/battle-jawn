import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import classNames from 'classnames';
import Battle from "./Battle";
import Inventory from "./Inventory";
import Store from "./Store";
import "../styling/MyHeroes.css";
import PopUp from "./PopUp";

function MyHeroes( {props}:{props:any} ) {
  const apiUrl = import.meta.env.VITE_REACT_APP_URL;
  const [heroId, setHeroId] = useState(0);
  const [deleteHeroId, setDeleteHeroId] = useState(0);
  const [battleActive, setBattleActive] = useState(false);
  const [inventoryActive, setInventoryActive] = useState(false);
  const [storeActive, setStoreActive] = useState(false);
  const [heroList, setHeroList] = useState([]);
  const [rested, setRested] = useState(1);
  const [popUpType, setPopUpType] = useState("");
  const [popUpContent, setPopUpContent] = useState("");
  const [showPopUp, setShowPopUp] = useState(false);

  let maxExperience = 50;

  function determineMaxExperience(level: number) {
    switch (level) {
      case 1:
        return 50;
      case 2:
        return 125;
      case 3:
        return 300;
      case 4:
        return 500;
      case 5:
        return 750;
      case 6:
        return 1250;
      case 7:
        return 2000;
      case 8:
        return 3000;
      case 9:
      case 10:
        return 5000;
      default:
        return 50;
    }
  }

  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  const fetchHeroes = async () => {
    try {
        const response = await
        axios.get(apiUrl + '/api/hero/list/' + props.id)
        setHeroList(response.data);
        } catch (error) {
        console.error('Error fetching Hero data: ', error)
        }
    }
    
  useEffect(() => {
    fetchHeroes();
  }, [rested])

  useEffect(() => {
    fetchHeroes();
  }, [inventoryActive])

function handleRest(id: any): void {
    axios.post(apiUrl + '/api/hero/rest/' + id)
      .then(response => {
        console.log("Hero successfully rested. Response: " + response.data)
      })
      .catch(error => {
        console.error('Error fetching rest data:', error);
      })
      fetchHeroes();
      setRested(rested + 1);
  };

  function handleFight(id: any, health: number): void {
    if (health > 0) {
        setHeroId(id);
        setBattleActive(true);
    } else {
      setPopUpType("jawn");
      setPopUpContent("You cannot fight! You have no health!");
      setShowPopUp(true);
    }
  }

  function handleInventory(id: any): void {
    setHeroId(id);
    setInventoryActive(true);
  }

  function handleStore(id: any) {
    setHeroId(id);
    setStoreActive(true);
  }

  function handleDelete(id: any): void {
    axios.delete(apiUrl + '/api/hero/delete/' + id)
      .then(response => {
        console.log("Hero successfully deleted. Response: " + response.data);
        fetchHeroes();
      }).catch(error => {
        console.error('Error deleting hero:', error);
      })
  }

  function handleDeleteConfirmation(id: any): void {
      setPopUpType("confirmation");
      setPopUpContent("delete hero");
      setShowPopUp(true);
      setDeleteHeroId(id);
  }
  
  function handleSubComponentButtonClick(component: string) {
      if (component === "store"){
        setStoreActive(false);
      } else if (component === "inventory") {
        setInventoryActive(false);
      }
  }

  function handleOkButtonClick() {
    setShowPopUp(false);
}
  function handleConfirmButtonClick() {
    console.log("confirm button clicked")
    setShowPopUp(false);
    handleDelete(deleteHeroId);
  }
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
            <div className="hero-name">
                {hero.name}
            </div>
            <table className="my-heroes-table">
              <tbody>
                <tr>
                  <td className="row-jawn">Class:</td>
                  <td className="data-jawn">{hero.role}</td>
                </tr>
                <tr>
                  <td className="row-jawn">Level:</td>
                  <td className="data-jawn">{hero.level}</td>
                </tr>
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
              <span className="experience-fraction">{hero.experience}/{determineMaxExperience(hero.level)}</span>
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
