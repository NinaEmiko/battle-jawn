import { useEffect, useState } from "react";
import classNames from 'classnames';
// import "../styling/Store.css";
// import "../styling/Inventory.css";
import PopUp from "./PopUp";
import { buyItems, sellItems, fetchHero, fetchInventory } from "../api/api";
// import { determineIcon } from "../helpers/icon_helper"
import { determinePrice } from "../helpers/price_helper";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
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
import cash from '../assets/cash.png'

const Shop = ({props}:{props:any}) => {
    const [purchases, setPurchases] = useState(1);
    const [inventoryList, setInventoryList] = useState([]);
    const [activeButton, setActiveButton] = useState("Buy");
    const [heroName, setHeroName] = useState("");
    const [heroCoins, setHeroCoins] = useState(0);
    const [filteredInventoryList, setFilteredInventoryList] = useState([]);
    const [buttonActive, setButtonActive] = useState(true);
    const [popUpType, setPopUpType] = useState("");
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const purchaseList = ["Potion", "Water"]

    const handleTabClick = (button: string) => {
        setActiveButton(button);
      };

    const handleFetchHero = async () => {
        const data = await fetchHero(props.heroId)
        setHeroName(data.name);
        setHeroCoins(data.coins);
    }

    const handleFetchInventory = async () => {
        const data = await fetchInventory(props.heroId)
        setInventoryList(data);
    }

    const handleClickBuy = async (id: number, item: string) => {
        const data = await buyItems(id, item);
        setPopUpType('jawn');
        setPopUpContent(data);
        setPurchases(prevPurchases => prevPurchases + 1);
        setShowPopUp(true);
    }

    const handleClickSell = async (id: number, item: string) => {
        const data = await sellItems(id, item);
        setPopUpType('jawn');
        setPopUpContent(data);
        setPurchases(prevPurchases => prevPurchases + 1);
        setShowPopUp(true);
    }

    const handleBackButtonClick = () => {
        props.setIsVisible("exit-store", props.heroId)
    }
    
    const handleOkButtonClick = () => {
        setShowPopUp(false);
    }
          
    useEffect(() => {
        handleFetchHero();
        handleFetchInventory();
    }, [])

    useEffect(() => {
        handleFetchHero();
        handleFetchInventory();
    }, [purchases])

    useEffect(() => {
       setFilteredInventoryList(inventoryList.filter(item => item !== ""));
    }, [inventoryList])

    const determineIcon = (item: string, index: number) => {
        switch (item) {
            case "Potion":
                if (activeButton === "Buy") {
                    return <img className="potion-icon" onClick={() => handleClickBuy(props.heroId, "Potion")}
                    src={healthPotion}/>;
                } else {
                    return <img className="potion-icon" onClick={() => handleClickSell(props.heroId, "Potion")}
                    src={healthPotion}/>;
                }
            case "Water":
                if (activeButton === "Buy"){
                    return <img className="potion-icon" onClick={() => handleClickBuy(props.heroId, "Water")}
                src={water}/>;
                } else {
                    return <img className="potion-icon" onClick={() => handleClickSell(props.heroId, "Water")}
                src={water}/>;
                }
            case "Sword":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Sword")}
                src={sword}/>;
            case "Wolf pelt":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Wolf pelt")}
                src={pelt}/>;
            case "Vest":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Vest")}
                src={vest}/>;
            case "Wolf scraps":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Wolf scraps")}
                src={scraps}/>;
            case "Wolf paw":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Wolf paw")}
                src={paw}/>;
            case "Spirit trinket":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Spirit trinket")}
                src={trinket}/>;
            case "Orc necklace":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Orc necklace")}
                src={necklace}/>;
            case "Helm":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Helm")}
                src={helm}/>;
            case "Mask":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Mask")}
                src={mask}/>;
            case "Jewels":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Jewels")}
                src={jewels}/>;
            case "Boots":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Boots")}
                src={boots}/>;
            case "Dagger":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Dagger")}
                src={dagger}/>;
            case "Pants":
                return <img className="potion" onClick={() => handleClickSell(props.heroId, "Pants")}
                src={pants}/>;
        }
    }

    return (        
        <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Leave</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">Shop</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
                <>
                    {!showPopUp ?
                        <>

                            {activeButton === "Buy" &&
                                <>
                                <div className="parent-jawn">
                                    <div className="child-jawn">
                                    <div className="inventory-grid-container">
                                        {[...Array(12).keys()].map(index => (
                                            <div key={index} className="inventory-grid-item">
                                                <div className="inventory-icon-price">{determinePrice(purchaseList[index])}</div>
                                                <div className="inventory-icon">{determineIcon(purchaseList[index], index + 1)}</div>
                                            </div>
                                        ))}
                                    </div>
                                    <div className="coins-container-jawn">
                                        <p className="coins-jawn">{heroCoins} coins</p>
                                        {/* <img className="cash" src={cash}/> */}
                                    </div>
                                    </div>

                                    </div>
                                </>
                            }

                            {activeButton === "Sell" &&
                                <>
                                <div className="parent-jawn">
                                    <div className="child-jawn">
                                    <div className="inventory-grid-container">
                                        {[...Array(12).keys()].map(index => (
                                                <div key={index} className="inventory-grid-item">
                                                <div className="inventory-icon-price">{determinePrice(inventoryList[index])}</div>
                                                <div className="inventory-icon">{determineIcon(inventoryList[index], index + 1)}</div>
                                            </div>
                                        ))}
                                    </div>
                                    <div className="coins-container-jawn">
                                        <p className="coins-jawn">{heroCoins} coins</p>
                                        {/* <img className="cash" src={cash}/> */}
                                    </div>
                                    </div>
                                    </div>
                                </>
                            }

                            <div className="display-jawn-tabs">
                                <button className={activeButton === 'Buy' ? 'active' : ''} onClick={()=> handleTabClick("Buy")}>Buy</button>
                                <button className={activeButton === 'Sell' ? 'active' : ''} onClick={()=> handleTabClick("Sell")}>Sell</button>
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
                        <button className="controls-btn" onClick={() => handleBackButtonClick()}>Leave</button>                   
                    </div>
                    <div className="controls-right">
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick("Buy")}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick("Sell")}>Right</button>
                        <button className="controls-btn"></button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}
  
  export default Shop;
  