import { useEffect, useState } from "react";
import { healthPotion, vest, sword, pelt, scraps, 
    boots, dagger, helm, jewels, mask, necklace,
    paw, trinket, pants, water } from "../helpers/image_helper";
import "../styling/Inventory.css";
import { fetchHero, fetchInventory, usePotion, useWater } from "../api/api";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";

const Inventory = ({props}:{props:any}) => {
    const [inventoryList, setInventoryList] = useState([]);
    const [heroCoins, setHeroCoins] = useState(0);
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);
    const [rightCenterButtonText, setRightCenterButtonText] = useState("-")
    const [leftBottomButtonText, setLeftBottomButtonText] = useState("Close")

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
        setPopUpContent(data);
        setLeftBottomButtonText("-");
        setRightCenterButtonText("OK")
        setShowPopUp(true);
    }

    const handleClickWater = async (index: number) => {
        const data = await useWater(props.heroId, index)
        setPopUpContent(data);
        setLeftBottomButtonText("-");
        setRightCenterButtonText("OK")
        setShowPopUp(true);
    }

    const handleClickNotPotion = () => {
    }

    const handleBackButtonClick = () => {
        if (!showPopUp){
            props.setIsVisible("Map", props.heroId)
        }
    }

    const handleOkButtonClick = () => {
        if (showPopUp) {
            setLeftBottomButtonText("Close");
            setRightCenterButtonText("-")
            setShowPopUp(false);
        }
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
                            <div className="parent-jawn">
                                <div className="child-jawn">
                                    <div className="text-jawn">
                                        {popUpContent}
                                    </div>
                                </div>
                            </div>
                        }
                    </>
                </Display>
                <Controls
                    handleClickLeftBtnBottom={() => handleBackButtonClick()}
                    leftBtnBottomText={leftBottomButtonText}
                    handleClickLeftBtnTop={() => handleOkButtonClick()}
                    leftBtnTopText={rightCenterButtonText}
                    rightBtnTopText="ᐃ"
                    rightBtnLeftText="ᐊ"
                    rightBtnRightText="ᐅ"
                    rightBtnBottomText="ᐁ"
                />
            </Container>
        </>
    );
};
  
  export default Inventory;
  