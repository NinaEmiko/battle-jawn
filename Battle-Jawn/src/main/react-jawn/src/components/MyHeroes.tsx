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
              axios.get('http://localhost:8080/api/hero/list/' + props.id)
              setHeroList(response.data);
              } catch (error) {
              console.error('Error fetching Hero data: ', error)
              }
          }
      fetchHeroes();
  }, [])

  return (
    <>
  
    <div className="container-jawn-hero">
    <h1 className="title-jawn">{props.userName} Heroes</h1>
    {heroList.length < 5 &&
    <div className="btn-cntr">
    <button className="btn" id="new-hero-btn" onClick={() => handleNavigation('/battle')}>Create New Hero</button>
    </div>
    }
    <div className="">
    {heroList.map((hero) => (
  <div className="container-jawn-hero-card" key={hero.id}>
    <p>Hero: {hero.role}</p>
    <p>Hero Win Count: {hero.winCount}</p>
    <p>Hero Loss Count: {hero.lossCount}</p>
    <p>Hero Run Count: {hero.runCount}</p>
    </div>
))}
</div>
    </div>

    </>
  );
};

export default MyHeroes;
