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
import water from "../assets/water.png";
import classNames from "classnames";
import "../styling/Inventory.css";
import PopUp from "./PopUp";
import { fetchHero, fetchInventory, usePotion, useWater } from "../api/api";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";

const Inventory = ({props}:{props:any}) => {
    const [inventoryList, setInventoryList] = useState([]);
    const [heroCoins, setHeroCoins] = useState(0);
    const [popUpType, setPopUpType] = useState("");
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const fetchInventoryCall = async () => {
        const data = await fetchInventory(props.heroId)
        setInventoryList(data);
    }

    const handleFetchHero = async () => {
        const data = await fetchHero(props.heroId)
        setHeroCoins(data.coins);
    }

    const handleClickPotion = async (index: number) => {
        const data = await usePotion(props.heroId, index)
        setPopUpType("jawn");
        setPopUpContent(data);
        setShowPopUp(true);
    }

    const handleClickWater = async (index: number) => {
        const data = await useWater(props.heroId, index)
        setPopUpType("jawn");
        setPopUpContent(data);
        setShowPopUp(true);
    }

    const handleClickNotPotion = () => {
        console.log("Not a potion")
    }

    const handleBackButtonClick = () => {
        props.setIsVisible("exit-inventory", props.heroId)
    }

    const handleOkButtonClick = () => {
        setShowPopUp(false);
    }
      
    useEffect(() => {
        fetchInventoryCall();
        handleFetchHero();
    }, [])

    useEffect(() => {
        fetchInventoryCall();
        handleFetchHero();
    }, [showPopUp])

    const determineIcon = (item: string, index: number) => {
        switch (item) {
            case "Potion":
                return <img className="potion-icon" onClick={() => handleClickPotion(index)}
                src={healthPotion}/>;
            case "Water":
                return <img className="potion-icon" onClick={() => handleClickWater(index)}
                src={water}/>;
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
        <>
            <Container>
                <PageName>
                    <div className="page-name-column-1">
                        {/* <button className="page-name-btn">Close</button> */}
                    </div>
                    <div className="page-name-column-2">
                        <div className="page-name-txt">Inventory</div>
                    </div>
                    <div className="page-name-column-3">
                        {/* <button className="page-name-btn">New Hero</button> */}
                    </div>
                </PageName>
                <Display>
                    <>
                        {!showPopUp ? 
                            <>
                                <div className="coins-container-jawn">
                                    <p className="coins-jawn">{heroCoins} coins</p>
                                    {/* <img className="cash" src={cash}/> */}
                                </div>

                                <div className="inventory-grid-container">
                                        
                                    {[...Array(12).keys()].map(index => (
                                        <div key={index} className="inventory-grid-item">
                                            <div className="inventory-icon">{determineIcon(inventoryList[index], index + 1)}</div>
                                        </div>
                                    ))}
                                </div>
                            </>
                            :
                            <PopUp 
                                props={{
                                    type: popUpType,
                                    content: popUpContent,
                                    onClickOk: handleOkButtonClick
                                }} 
                            />   
                        }
                    </>
                </Display>
                <Controls>
                    <>
                        <div className="controls-left">
                            <button className="controls-btn"></button>
                            <button className="controls-btn"></button>
                            <button className="controls-btn" onClick={() => handleBackButtonClick()}>Close</button>                    
                        </div>
                        <div className="controls-right">
                            <button className="controls-btn">Up</button>
                            <button className="controls-btn">Left</button>
                            <button className="controls-btn">OK</button>
                            <button className="controls-btn">Right</button>
                            <button className="controls-btn">Down</button>
                        </div>
                    </>
                </Controls>
            </Container>
        </>
    );
};
  
  export default Inventory;
  