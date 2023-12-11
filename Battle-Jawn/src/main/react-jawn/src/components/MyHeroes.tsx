import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import classNames from 'classnames';
import Battle from "./Battle";

function MyHeroes( {props}:{props:any} ) {
    const [heroId, setHeroId] = useState(0);
    const [beginBattle, setBeginBattle] = useState(false);
    const [heroList, setHeroList] = useState([]);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
      navigate(path);
    };

    const fetchHeroes = async () => {
      try {
          const response = await
          axios.get('http://localhost:8080/api/hero/list/' + props.id)
          setHeroList(response.data);
          } catch (error) {
          console.error('Error fetching Hero data: ', error)
          }
      }
    
    useEffect(() => {
      fetchHeroes();
    }, [])

function handleRest(id: any): void {
    axios.post('http://localhost:8080/api/hero/rest/' + id)
      .then(response => {
        console.log("Hero successfully rested. Response: " + response.data)
      })
      .catch(error => {
        console.error('Error fetching rest data:', error);
      })
      fetchHeroes();
  };

  function handleFight(id: any): void {
    setHeroId(id);
    setBeginBattle(true);
  }

  return (
    <>

{beginBattle ?  
      <Battle  props={heroId} />
      :
  
    <div className="container-jawn-hero">
    <h1 className="title-jawn">{props.userName} Heroes</h1>
    {heroList.length < 5 &&
    <div className="btn-cntr">
    <button className="btn" id="new-hero-btn" onClick={() => handleNavigation('/player-selection')}>Create New Hero</button>
    </div>
    }
    <div className="">
    {heroList.map((hero) => (
  <div className="container-jawn-hero-card" key={hero.id}>
    <p className="title-jawn">Name Goes Here</p>
    <table>
      <tbody>
        <tr>
          <td className="row-jawn">Class:</td>
          <td className="data-jawn">{hero.role}</td>
        </tr>
        <tr>
          <td className="row-jawn">Health:</td>
          <td className="data-jawn">{hero.health} / {hero.maxHealth}</td>
        </tr>
        <tr>
          <td className="row-jawn">Potions:</td>
          <td className="data-jawn">{hero.potions}</td>
        </tr>
        <tr>
          <td className="row-jawn">Wins:</td>
          <td className="data-jawn">{hero.winCount}</td>
        </tr>
        <tr>
          <td className="row-jawn">Losses:</td>
          <td className="data-jawn">{hero.lossCount}</td>
        </tr>
        <tr>
          <td className="row-jawn">Run Count:</td>
          <td className="data-jawn">{hero.runCount}</td>
        </tr>
      </tbody>
    </table>
    <div className="row justify-content-center">
          <button onClick={() => handleRest(hero.id)} className={classNames('nav-link', 'btn', 'custom-button')} id="rest-btn">Rest</button>
          <button onClick={() => handleFight(hero.id)} className={classNames('nav-link', 'btn', 'custom-button')} id="fight-btn">Fight</button>
        </div>
      

    </div>
))}
</div>
    </div>
}
    </>
  );
};

export default MyHeroes;
