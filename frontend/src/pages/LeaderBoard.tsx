import { useEffect, useState } from "react";
import "../styling/LeaderBoard.css";
import classNames from 'classnames';
import { useNavigate } from "react-router-dom";
import { fetchLeaderboard } from "../api/api";
import Container from "../components/Container";
import PageName from "../components/PageName";
import Display from "../components/Display";
import Controls from "../components/Controls";

const LeaderBoard = ({props}:{props:any}) => {
    const [heroList, setHeroList] = useState([]);
    const [currentHeroIndex, setCurrentHeroIndex] = useState(0);

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };
    
    const fetchHeroes = async () => {
        const data = await fetchLeaderboard()
        setHeroList(data);
    }

    const previousHero = () => {
        if(currentHeroIndex == 0) {
          setCurrentHeroIndex(heroList.length - 1)
        } else {
          setCurrentHeroIndex(currentHeroIndex - 1);
        }
      };
    
      const nextHero = () => {
        if(currentHeroIndex == heroList.length - 1) {
          setCurrentHeroIndex(0)
        } else {
          setCurrentHeroIndex(currentHeroIndex + 1);
        }
      };
      
    useEffect(() => {
        fetchHeroes();
    }, [])
    
    return (        
        <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Close</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">Leader Board</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
            <div className="parent-jawn">
                <div className="child-jawn">

                {heroList.length > 0 &&

                <div className="hero-header-jawn">
                    <div className="leader-board-hero-name-level">
                    <div className="leader-board-hero-name">#{currentHeroIndex + 1} {heroList[currentHeroIndex].name} </div>
                    <p className="leader-board-hero-level"> Lvl {heroList[currentHeroIndex].level} {heroList[currentHeroIndex].role} </p>
                </div>

                <div className="table-container">
                <table className="stats-table">
                    <tbody className="stats-table-body">
                    <tr className="stats-table-row">
                        <td className="stats-table-data">Won</td>
                        <td className="stats-table-data">Lost</td>
                        <td className="stats-table-data">Ran</td>
                        <td className="stats-table-data">Streak</td>
                    </tr>
                    <tr className="stats-table-row">
                        <td className="stats-table-data">{heroList[currentHeroIndex].winCount}</td>
                        <td className="stats-table-data">{heroList[currentHeroIndex].lossCount}</td>
                        <td className="stats-table-data">{heroList[currentHeroIndex].runCount}</td>
                        <td className="stats-table-data">{heroList[currentHeroIndex].winStreak}</td>
                    </tr>
                    </tbody>
                </table>
                </div>
                </div>
                }
                </div>
                </div>
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={() => handleNavigation("/")}>Back</button>                    </div>
                    <div className="controls-right">
                        <button className="controls-btn" onClick={() => previousHero()}>Up</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={() => nextHero()}>Down</button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}
  
  export default LeaderBoard;
  