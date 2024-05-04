import axios from "axios";
import { useEffect, useState } from "react";
import healthPotion from "../assets/healthPotion.png";
import vest from "../assets/vest.png";
import sword from "../assets/sword.png";
import pelt from "../assets/pelt.png";
import scraps from "../assets/scraps.png";
import boots from "../assets/boots.png";
import dagger from "../assets/dagger.png";
import helm from "../assets/helm.png";
import jewels from "../assets/jewelery.png";
import mask from "../assets/mask.png";
import necklace from "../assets/necklace.png";
import paw from "../assets/paw.png";
import trinket from "../assets/spirit-trinket.png";
import pants from "../assets/pants.png";
import classNames from "classnames";
import "../styling/Inventory.css";
import PopUp from "./PopUp";
import { fetchInventory, usePotion } from "../api/api";

const Inventory = ({props}:{props:any}) => {
    const [inventoryList, setInventoryList] = useState([])
    const [popUpType, setPopUpType] = useState("");
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const fetchInventoryCall = async () => {
        const data = await fetchInventory(props.heroId)
        setInventoryList(data);
    }

    const handleClickPotion = async (index: number) => {
        const data = await usePotion(props.heroId, index)
        setPopUpType("jawn");
        setPopUpContent(data);
        setShowPopUp(true);
    }

    const handleClickNotPotion = () => {
        console.log("Not a potion")
    }

    const handleBackButtonClick = () => {
        props.setIsVisible("inventory")
    }

    const handleOkButtonClick = () => {
        setShowPopUp(false);
    }
      
    useEffect(() => {
        fetchInventoryCall();
    }, [])

    useEffect(() => {
        fetchInventoryCall();
    }, [showPopUp])

    const determineIcon = (item: string, index: number) => {
        switch (item) {
            case "Potion":
                return <img className="potion-icon" onClick={() => handleClickPotion(index)}
                src={healthPotion}/>;
            case "Sword":
                return <img className="potion" onClick={handleClickNotPotion}
                src={sword}/>;
            case "Wolf pelt":
                return <img className="potion" onClick={handleClickNotPotion}
                src={pelt}/>;
            case "Vest":
                return <img className="potion" onClick={handleClickNotPotion}
                src={vest}/>;
            case "Wolf scraps":
                return <img className="potion" onClick={handleClickNotPotion}
                src={scraps}/>;
            case "Wolf paw":
                return <img className="potion" onClick={handleClickNotPotion}
                src={paw}/>;
            case "Spirit trinket":
                return <img className="potion" onClick={handleClickNotPotion}
                src={trinket}/>;
            case "Orc necklace":
                return <img className="potion" onClick={handleClickNotPotion}
                src={necklace}/>;
            case "Helm":
                return <img className="potion" onClick={handleClickNotPotion}
                src={helm}/>;
            case "Mask":
                return <img className="potion" onClick={handleClickNotPotion}
                src={mask}/>;
            case "Jewels":
                return <img className="potion" onClick={handleClickNotPotion}
                src={jewels}/>;
            case "Boots":
                return <img className="potion" onClick={handleClickNotPotion}
                src={boots}/>;
            case "Dagger":
                return <img className="potion" onClick={handleClickNotPotion}
                src={dagger}/>;
            case "Pants":
                return <img className="potion" onClick={handleClickNotPotion}
                src={pants}/>;
        }
    }

    return (
        <div className="inventory-background-jawn">

            {!showPopUp ? 
        
            <div className="container-jawn-inventory">
                <h1 className="inventory-title-jawn">Inventory</h1>
                <div className="inventory-grid-container">
                    {[...Array(12).keys()].map(index => (
                        <div key={index} className="inventory-grid-item">
                            <div className="inventory-icon">{determineIcon(inventoryList[index], index + 1)}</div>
                        </div>
                    ))}
                </div>
                <button className={classNames('nav-link', 'btn')} id="inventory-close-btn" onClick={handleBackButtonClick}>Close</button>
            </div>
            :
            <PopUp 
                props={{
                    type: popUpType,
                    content: popUpContent,
                    onClickOk: handleOkButtonClick
                }} 
            />   
}
        </div>
    );
  };
  
  export default Inventory;
  