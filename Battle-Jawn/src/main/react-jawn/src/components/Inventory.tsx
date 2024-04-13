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
import jewelery from "../assets/jewelery.png";
import mask from "../assets/mask.png";
import necklace from "../assets/necklace.png";
import paw from "../assets/paw.png";
import trinket from "../assets/spirit-trinket.png";
import pants from "../assets/pants.png";

const Inventory = ({props}:{props:any}) => {

    const [inventoryList, setInventoryList] = useState([])

    const fetchInventory = async () => {
        try {
            const response = await
            axios.get('http://localhost:8080/api/inventory/' + props)
            console.log("props: " + props)
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
            case "potion":
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
            case "Orc Necklace":
                return <img className="potion"
                src={necklace}/>;
            case "Helm":
                return <img className="potion"
                src={helm}/>;
            case "Mask":
                return <img className="potion"
                src={mask}/>;
            case "Jewelry":
                return <img className="potion"
                src={jewelery}/>;
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

    return (
        <div className="container-jawn-login-form">
            <h1 className="title-jawn">Inventory</h1>
            <div className="">
                <div className="grid-container">
                    {[...Array(12).keys()].map(index => (
                        <div key={index} className="grid-item">
                            <div className="icon">{determineIcon(inventoryList[index])}</div>
                            <div className="item-name">{inventoryList[index]}</div>
                        </div>
                    ))}
                </div>
            </div>
            {/* <div className="toss-btn">
                <button className="btn">Toss</button>
            </div> */}
        </div>
    );
  };
  
  export default Inventory;
  