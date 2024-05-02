import { useEffect, useState } from "react";
import "../styling/LeaderBoard.css";
import classNames from 'classnames';
import { useNavigate } from "react-router-dom";
import { fetchLeaderboard } from "../api/api";

const LeaderBoard = ({props}:{props:any}) => {
    const [heroList, setHeroList] = useState([]);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
      };

    const fetchHeroes = async () => {
        const data = await fetchLeaderboard()
        setHeroList(data);
    }
      
    useEffect(() => {
        fetchHeroes();
    }, [])

    return (
        <div className="leader-board-background-jawn">
            <div className="container-jawn-leader-board">
                <h1 className="leader-board-title-jawn">Leader Board</h1>
                <div className="">
                    {heroList.map((hero) => (
                        <div className="container-jawn-leader-board-card" key={hero.winCount}>
                            <table className="leader-board-table">
                                <tbody>
                                    <tr>
                                        <td className="row-jawn">Hero:</td>
                                        <td className="data-jawn-win">{hero.name}</td>
                                    </tr>
                                    <tr>
                                        <td className="row-jawn">Wins:</td>
                                        <td className="data-jawn-win">{hero.winCount}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    ))}
                </div>
                <button className={classNames('nav-link', 'btn')} id="leader-board-ok-btn" onClick={() => handleNavigation("/")}>Ok</button>
            </div>
        </div>
    );
  };
  
  export default LeaderBoard;
  