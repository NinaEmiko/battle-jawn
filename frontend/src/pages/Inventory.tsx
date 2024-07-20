import { useEffect, useState } from "react";
import { healthPotion, vest, sword, pelt, scraps, 
    boots, dagger, helm, jewels, mask, necklace,
    paw, trinket, pants, water } from "../helpers/image_helper";
import "../styling/Inventory.css";
import PopUp from "../components/PopUp";
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
        props.setIsVisible("Map", props.heroId)
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
                <PageName props={"Inventory"} />
                <Display>
                    <>
                        {!showPopUp ? 
                            <>
                                <div className="parent-jawn">
                                    <div className="child-jawn">
                                        <div className="inventory-grid-container">
                                                
                                            {[...Array(12).keys()].map(index => (
                                                <div key={index} className="inventory-grid-item">
                                                    <div className="inventory-icon">{determineIcon(inventoryList[index], index + 1)}</div>
                                                </div>
                                            ))}
                                        </div>
                                        <div className="coins-container-jawn">
                                            <p className="coins-jawn">{heroCoins} coins</p>
                                        </div>
                                    </div>
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
                <Controls
                    handleClickLeftBtnBottom={() => handleBackButtonClick()}
                    leftBtnBottomText="Close"
                />
            </Container>
        </>
    );
};
  
  export default Inventory;
  