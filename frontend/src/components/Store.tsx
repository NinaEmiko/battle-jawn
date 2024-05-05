import { useEffect, useState } from "react";
import classNames from 'classnames';
import "../styling/Store.css";
import PopUp from "./PopUp";
import { buyItems, sellItems, fetchHero, fetchInventory } from "../api/api";
import { determineIcon } from "../helpers/icon_helper"
import { determinePrice } from "../helpers/price_helper";

const Store = ({props}:{props:any}) => {
    const [purchases, setPurchases] = useState(1);
    const [inventoryList, setInventoryList] = useState([]);
    const [heroName, setHeroName] = useState("");
    const [heroCoins, setHeroCoins] = useState(0);
    const [buyActive, setBuyActive] = useState(true);
    const [sellActive, setSellActive] = useState(false);
    const [filteredInventoryList, setFilteredInventoryList] = useState([]);
    const [buttonActive, setButtonActive] = useState(true);
    const [popUpType, setPopUpType] = useState("");
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

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

    const handleClickBuyTab = () => {
        setBuyActive(true);
        setSellActive(false);
        setButtonActive(true);
    }

    const handleClickSellTab = () => {
        setSellActive(true);
        setBuyActive(false);
        setButtonActive(false);
    }

    const handleBackButtonClick = () => {
        props.setIsVisible("store")
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

    return (
        <div className="store-background-jawn">
            {!showPopUp ?
            <div className="container-jawn-store">
                <h1 className="store-title-jawn">Store</h1>
                <div className="row justify-content-center">
                <button
                  className={classNames('nav-link', 'btn', 'custom-button', buttonActive === true ? 'active' : '')}
                  id="tab-login"
                  onClick={() => handleClickBuyTab()}
                >
                  Buy
                </button>
                <button
                  className={classNames('nav-link', 'btn', 'custom-button', buttonActive === false ? 'active' : '')}
                  id="tab-register"
                  onClick={() => handleClickSellTab()}
                >
                  Sell
                </button>
                </div>


                <div className="container-jawn-store-card">
                    <div className="store-hero-name">
                        {heroName}
                    </div>
                    <div className="store-hero-coins">
                        {heroCoins} coins
                    </div>
                    <div>
                        {buyActive &&
                            <table className="store-buy-table">
                                <tbody>
                                    <tr>
                                        <td className="store-buy-item">
                                            <div>
                                                <div>
                                                    {determineIcon("Potion")}
                                                </div>
                                                <span className="store-buy-item-price">1 coin</span>
                                            </div>
                                            <button onClick={() => handleClickBuy(props.heroId, "Potion")} className="buy-btn">Buy</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className="store-buy-item">
                                            <div>
                                                <div>
                                                    {determineIcon("Water")}
                                                </div>
                                                <span className="store-buy-item-price">3 coins</span>
                                            </div>
                                            <button onClick={() => handleClickBuy(props.heroId, "Water")} className="buy-btn">Buy</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className="buy-btn-separator" colSpan={2}>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        }
                        {sellActive && filteredInventoryList &&
                            <div>
                                {filteredInventoryList.map((item, index) => (
                                    <div key={index}>
                                        <table className="store-sell-table">
                                            <tbody>
                                                <tr>
                                                    <td className="store-sell-item">
                                                        <div>
                                                        {determineIcon(item)}
                                                        </div>
                                                        <span className="store-sell-item-price">{determinePrice(item)}</span>
                                                        <button onClick={() => handleClickSell(props.heroId, item)} className="sell-btn">Sell {item}</button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td className="sell-btn-separator" colSpan={2}></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                ))}
                            </div>
                        }   
                    </div>             
                </div>
                <button className={classNames('nav-link', 'btn')} id="store-leave-btn" onClick={handleBackButtonClick}>Leave</button>
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
  
  export default Store;
  