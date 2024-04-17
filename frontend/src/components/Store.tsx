import axios from "axios";
import { useEffect, useState } from "react";
import classNames from 'classnames';
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
import healthPotion from "../assets/healthPotion.png";
import "../styling/Store.css";

const Store = ({props}:{props:any}) => {
    const [purchases, setPurchases] = useState(1);
    const [inventoryList, setInventoryList] = useState([]);
    const [heroName, setHeroName] = useState("");
    const [heroCoins, setHeroCoins] = useState(0);
    const [buyActive, setBuyActive] = useState(true);
    const [sellActive, setSellActive] = useState(false);
    const [filteredInventoryList, setFilteredInventoryList] = useState([]);

    const buyItems = async (id: number, item: string) => {
        const response = await
        axios.post('${REACT_APP_API_URL}/api/store/buy', {
            heroId: id,
            item: item,
            quantity: 1
        })
        .then((response) => {
            alert("" + response.data);
            setPurchases(purchases + 1);
            })
        .catch((error) => {
            console.error('Error buying items:', error);
        }
        )
    }

    const sellItems = async (id: number, item: string) => {
        const response = await
        axios.post('${REACT_APP_API_URL}/api/store/sell', {
            heroId: id,
            item: item,
            quantity: 1
        })
        .then((response) => {
            alert("" + response.data);
            setPurchases(purchases + 1);
            })
        .catch((error) => {
            console.error('Error selling items:', error);
        }
        )
    }

    const fetchHero = async () => {
        axios.get('${REACT_APP_API_URL}/api/hero/' + props.heroId)
        .then((heroResponse) => {
          setHeroName(heroResponse.data.name);
          setHeroCoins(heroResponse.data.coins);
        })
        .catch((error) => {
          console.error('Error fetching hero data: ', error)
        })
    }

    const fetchInventory = async () => {
        try {
            const response = await
            axios.get('${REACT_APP_API_URL}/api/inventory/' + props.heroId)
            console.log("props: " + props)
            setInventoryList(response.data);
            } catch (error) {
            console.error('Error fetching inventory data: ', error)
            }
        }

    function handleClickBuy(id: number, item: string) {
        buyItems(id, item);
    }

    function handleClickSell(id: number, item: string) {
        sellItems(id, item);
    }
    function handleClickBuyTab() {
        setBuyActive(true);
        setSellActive(false);
    }

    function handleClickSellTab() {
        setSellActive(true);
        setBuyActive(false);
    }
          
    useEffect(() => {
        fetchHero();
        fetchInventory();
    }, [])

    useEffect(() => {
        fetchHero();
        fetchInventory();
    }, [purchases])

    useEffect(() => {
       setFilteredInventoryList(inventoryList.filter(item => item !== ""));
    }, [inventoryList])

    const determineIcon = (item: string) => {
        switch (item) {
            case "Potion":
                return <img className="top-left"
                src={healthPotion}/>;
            case "Sword":
                return <img className="top-left"
                src={sword}/>;
            case "Wolf pelt":
                return <img className="top-left"
                src={pelt}/>;
            case "Vest":
                return <img className="top-left"
                src={vest}/>;
            case "Wolf scraps":
                return <img className="top-left"
                src={scraps}/>;
            case "Wolf paw":
                return <img className="top-left"
                src={paw}/>;
            case "Spirit trinket":
                return <img className="top-left"
                src={trinket}/>;
            case "Orc necklace":
                return <img className="top-left"
                src={necklace}/>;
            case "Helm":
                return <img className="top-left"
                src={helm}/>;
            case "Mask":
                return <img className="top-left"
                src={mask}/>;
            case "Jewels":
                return <img className="top-left"
                src={jewels}/>;
            case "Boots":
                return <img className="top-left"
                src={boots}/>;
            case "Dagger":
                return <img className="top-left"
                src={dagger}/>;
            case "Pants":
                return <img className="top-left"
                src={pants}/>;
        }
    }

    function handleBackButtonClick() {
        props.setIsVisible("store")
    }

    return (
        <div className="store-background-jawn">
            <div className="container-jawn-store">
                <h1 className="store-title-jawn">Store</h1>
                <div className="row justify-content-center">
                    <button className={classNames('nav-link', 'btn', 'custom-button')} id="store-btn" onClick={() => handleClickBuyTab()}>Buy</button>
                    <button className={classNames('nav-link', 'btn', 'custom-button')} id="inventory-btn" onClick={() => handleClickSellTab()}>Sell</button>
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
                                                        <span className="store-sell-item-price">1 coin</span>
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
        </div>
    );
};
  
  export default Store;
  