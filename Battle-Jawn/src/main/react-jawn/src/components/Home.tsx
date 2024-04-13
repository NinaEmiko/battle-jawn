// import axios from "axios";
// import { useEffect, useState } from "react";
// import { useNavigate } from "react-router-dom";
// import Battle from "./Battle";
// import Store from "./Store";
// import MyHeroes from "./MyHeroes";

// function Home( {props}:{props:any} ) {
//   const [heroId, setHeroId] = useState(0);
//   const [battleActive, setBattleActive] = useState(false);
//   const [storeActive, setStoreActive] = useState(false);
//   const [myHeroesActive, setMyHeroesActive] = useState(false);
//   const [inventoryActive, setInventoryActive] = useState(false);

//   const navigate = useNavigate();

//   const handleNavigation = (path: string) => {
//     navigate(path);
//   };

//   return (
//     <>
//         {battleActive &&  
//             <Battle  props={heroId} />
//         }
//         {myHeroesActive &&
//             <MyHeroes props={props.id} />
//         }
//         {storeActive &&
//             <Store props={props.id} />
//         }
//     </>
//   );
// };

// export default Home;
