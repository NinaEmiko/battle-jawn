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

const Inventory = ({props}:{props:any}) => {
    const apiUrl = import.meta.env.VITE_REACT_APP_URL;
    const [inventoryList, setInventoryList] = useState([])

    const fetchInventory = async () => {
        try {
            const response = await
            axios.get(apiUrl + '/api/inventory/' + props.heroId)
            console.log("props: " + props.heroId)
            setInventoryList(response.data);
            } catch (error) {
            console.error('Error fetching inventory data: ', error)
            }
        }
      
    useEffect(() => {
        fetchInventory();
    }, [])

    const determineIcon = (item: string) => {
        switch (item) {
            case "Potion":
                return <img className="potion"
                src={healthPotion}/>;
            case "Sword":
                return <img className="potion"
                src={sword}/>;
            case "Wolf pelt":
                return <img className="potion"
                src={pelt}/>;
            case "Vest":
                return <img className="potion"
                src={vest}/>;
            case "Wolf scraps":
                return <img className="potion"
                src={scraps}/>;
            case "Wolf paw":
                return <img className="potion"
                src={paw}/>;
            case "Spirit trinket":
                return <img className="potion"
                src={trinket}/>;
            case "Orc necklace":
                return <img className="potion"
                src={necklace}/>;
            case "Helm":
                return <img className="potion"
                src={helm}/>;
            case "Mask":
                return <img className="potion"
                src={mask}/>;
            case "Jewels":
                return <img className="potion"
                src={jewels}/>;
            case "Boots":
                return <img className="potion"
                src={boots}/>;
            case "Dagger":
                return <img className="potion"
                src={dagger}/>;
            case "Pants":
                return <img className="potion"
                src={pants}/>;
        }
    }
    function handleBackButtonClick() {
        props.setIsVisible("inventory")
    }

    return (
        <div className="inventory-background-jawn">
            <div className="container-jawn-inventory">
                <h1 className="inventory-title-jawn">Inventory</h1>
                <div className="inventory-grid-container">
                    {[...Array(12).keys()].map(index => (
                        <div key={index} className="inventory-grid-item">
                            <div className="inventory-icon">{determineIcon(inventoryList[index])}</div>
                            <div className="inventory-item-name">{inventoryList[index]}</div>
                        </div>
                    ))}
                </div>
                <button className={classNames('nav-link', 'btn')} id="inventory-close-btn" onClick={handleBackButtonClick}>Close</button>
            </div>
        </div>
    );
  };
  
  export default Inventory;
  