import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Battle from "./Battle";
import Store from "./Store";
import MyHeroes from "./MyHeroes";

function Home( {props}:{props:any} ) {
  const [heroId, setHeroId] = useState(0);
  const [battleActive, setBattleActive] = useState(false);
  const [storeActive, setStoreActive] = useState(false);
  const [myHeroesActive, setMyHeroesActive] = useState(false);
  const [inventoryActive, setInventoryActive] = useState(false);

  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  return (
    <>
        <div className="container-hero-jawn">

        </div>
    </>
  );
};

export default Home;
