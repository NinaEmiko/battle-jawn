import { useState } from "react";
import HomePage from "./HomePage";

function MyHeroes( {props}:{props:any} ) {
    const [chooseHero, setChooseHero] = useState(false);

  return (
    <>
    {chooseHero ?

    <HomePage />
    
    :
    
    <div className="container-jawn">
    <h1 className="title-jawn">{props.userName} Heroes</h1>
    <div className="btn-cntr">
    <button className="btn" id="button5" onClick={() => setChooseHero(true)}>Choose Hero</button>
    </div>
    </div>
    }
    </>
  );
};

export default MyHeroes;
