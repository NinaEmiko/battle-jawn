import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function MyHeroes( {props}:{props:any} ) {
    const [chooseHero, setChooseHero] = useState(false);
    const [heroList, setHeroList] = useState([]);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
      navigate(path);
    };
    
    useEffect(() => {
      const fetchHeroes = async () => {
          try {
              const response = await
              axios.get('http://localhost:8080/api/hero/all')
              setHeroList(response.data);
              } catch (error) {
              console.error('Error fetching Hero data: ', error)
              }
          }
      // fetchHeroes();
  }, [])

  return (
    <>
  
    <div className="container-jawn-login-form">
    <h1 className="title-jawn">{props.userName} Heroes</h1>
    {/* {heroList.map((hero) => (
  <li key={hero.id}>
    <strong>{hero.role}</strong> by {hero.winCount}
  </li>
))} */}
    <div className="btn-cntr">
    <button className="btn" id="button5" onClick={() => handleNavigation('/battle')}>Create New Hero</button>
    </div>
    </div>

    </>
  );
};

export default MyHeroes;
