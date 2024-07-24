import { useEffect, useState } from "react";
import { buyItems, sellItems, fetchHero, fetchInventory } from "../api/api";
import { determinePrice } from "../helpers/price_helper";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import { healthPotion, vest, sword, pelt, scraps, 
    boots, dagger, helm, jewels, mask, necklace,
    paw, trinket, pants, water } from "../helpers/image_helper";


const Shop = ({props}:{props:any}) => {
    const [purchases, setPurchases] = useState(1);
    const [inventoryList, setInventoryList] = useState([]);
    const [activeButton, setActiveButton] = useState("Buy");
    const [heroName, setHeroName] = useState("");
    const [heroCoins, setHeroCoins] = useState(0);
    const [filteredInventoryList, setFilteredInventoryList] = useState([]);
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);
    const [leftBottomButtonText, setLeftBottomButtonText] = useState("Leave");
    const [rightCenterButtonText, setRightCenterButtonText] = useState("")
    const [leftDirectionButtonText, setLeftDirectionButtonText] = useState("Left");
    const [rightDirectionButtonText, setRightDirectionButtonText] = useState("Right")

    const purchaseList = ["Potion", "Water"]

    const handleTabClick = (button: string) => {
        if (!showPopUp) {
            setActiveButton(button);
        }
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
        setPopUpContent(data);
        setLeftBottomButtonText("")
        setLeftDirectionButtonText("")
        setRightDirectionButtonText("")
        setRightCenterButtonText("OK")
        setPurchases(prevPurchases => prevPurchases + 1);
        setShowPopUp(true);
    }

    const handleClickSell = async (id: number, item: string) => {
        const data = await sellItems(id, item);
        setPopUpContent(data);
        setLeftBottomButtonText("")
        setLeftDirectionButtonText("")
        setRightDirectionButtonText("")
        setRightCenterButtonText("OK")
        setPurchases(prevPurchases => prevPurchases + 1);
        setShowPopUp(true);
    }

    const handleBackButtonClick = () => {
        if (!showPopUp){
            props.setIsVisible("Map", props.heroId)
        }
    }
    
    const handleOkButtonClick = () => {
        setLeftBottomButtonText("Leave")
        setLeftDirectionButtonText("Left")
        setRightDirectionButtonText("Right")
        setRightCenterButtonText("")
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
            <PageName props={"Shop"} />
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
                            <div className="account-settings-container-jawn">
                                <div className="other-txt">
                                    {popUpContent}
                                </div>
                            </div>
                    }
                </>
            </Display>
            <Controls
                handleClickLeftBtnBottom={handleBackButtonClick}
                leftBtnBottomText={leftBottomButtonText}
                handleClickRightBtnLeft={() => handleTabClick("Buy")}
                rightBtnLeftText={leftDirectionButtonText}
                handleClickRightBtnRight={() => handleTabClick("Sell")}
                rightBtnRightText={rightDirectionButtonText}
                handleClickRightBtnCenter={() => handleOkButtonClick()}
                rightBtnCenterText={rightCenterButtonText}
            />
        </Container>
    )
}
  
  export default Shop;
  