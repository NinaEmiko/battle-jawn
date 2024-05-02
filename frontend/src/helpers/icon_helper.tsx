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

export const determineIcon = (item: string) => {
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