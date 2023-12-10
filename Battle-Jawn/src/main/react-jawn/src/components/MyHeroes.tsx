import { useState } from "react";
import HomePage from "./HomePage";
import { useNavigate } from "react-router-dom";

function MyHeroes( {props}:{props:any} ) {
    const [chooseHero, setChooseHero] = useState(false);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
      navigate(path);
    };
    console.log()
  return (
    <>
  
    <div className="container-jawn-login-form">
    <h1 className="title-jawn">{props.userName} Heroes</h1>
    <div className="btn-cntr">
    <button className="btn" id="button5" onClick={() => handleNavigation('/battle')}>Choose Hero</button>
    </div>
    </div>

    </>
  );
};

export default MyHeroes;
