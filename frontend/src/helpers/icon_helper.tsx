import vest from "../assets/items/vest.png";
import sword from "../assets/items/sword.png";
import pelt from "../assets/items/pelt.png";
import scraps from "../assets/items/scraps.png";
import boots from "../assets/items/boots.png";
import dagger from "../assets/items/dagger.png";
import helm from "../assets/items/helm.png";
import jewels from "../assets/items/jewelery.png";
import mask from "../assets/items/mask.png";
import necklace from "../assets/items/necklace.png";
import paw from "../assets/items/paw.png";
import trinket from "../assets/items/spirit-trinket.png";
import pants from "../assets/items/pants.png";
import healthPotion from "../assets/items/healthPotion.png";
import power from "../assets/resources/power.png";
import spirit from "../assets/resources/spirit.png";
import magic from "../assets/resources/magic.png";
import energy from "../assets/resources/energy.png";
import water from "../assets/items/water.png";

export const determineIcon = (item: string) => {
    switch (item) {
        case "Potion":
            return <img className="top-left"
            src={healthPotion}/>;
        case "Water":
            return <img className="top-left"
            src={water}/>;
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

export const determineResourceIcon = (role: string, resource: number) => {
    switch (role) {
        case "Tank":
            return (
                <div>
                    {[...Array(resource)].map((_, index) => (
                        <img key={index} className="resource-icon" src={power} />
                        
                    ))}
                </div>
            );
        case "Healer":
            return (
                <div>
                    {[...Array(resource)].map((_, index) => (
                        <img key={index} className="resource-icon" src={spirit} />
                    ))}
                </div>
            );
        case "Caster":
            return (
                <div>
                    {[...Array(resource)].map((_, index) => (
                        <img key={index} className="resource-icon" src={magic} />
                    ))}
                </div>
            );
        case "DPS":
            return (
                <div>
                    {[...Array(resource)].map((_, index) => (
                        <img key={index} className="resource-icon" src={energy} />
                    ))}
                </div>
            );
    }
}