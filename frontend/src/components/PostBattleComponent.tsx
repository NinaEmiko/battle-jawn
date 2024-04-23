import axios from "axios";
import { useEffect, useState } from "react";

const PostBattleComponent = ({props}:{props:any}) => {
    const [heroList, setHeroList] = useState([]);
    console.log(props)

    return (
        <div className="container-jawn-login-form">
            <h1 className="post-battle-text">{props}</h1>
            <button className="btn">OK</button>
        </div>
    );
  };
  
  export default PostBattleComponent;
  