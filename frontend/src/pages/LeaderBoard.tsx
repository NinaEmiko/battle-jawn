import { useEffect, useState } from "react";
import "../styling/LeaderBoard.css";
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
            <PageName props={"Leader Board"} />
            <Display>
                <div className="parent-jawn">
                    <div className="child-jawn">

                        {heroList.length > 0 &&

                            <div className="leader-board-header-jawn">
                                <div className="number-symbol">
                                    #{currentHeroIndex + 1}
                                </div>
                                <div className="leader-board-hero-name-level">
                                    <div className="leader-board-hero-name">
                                        {heroList[currentHeroIndex].name}
                                    </div>
                                    <p className="leader-board-hero-level">{heroList[currentHeroIndex].role} </p>
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
                    <p className="leaderboard-howto">Press up or down to scroll through the top 5 heroes.</p>
                </div>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleNavigation("/")}
                leftBtnBottomText="Exit"
                handleClickRightBtnTop={() => previousHero()}
                rightBtnTopText="Up"
                handleClickRightBtnBottom={() => nextHero()}
                rightBtnBottomText="Down"
            />
        </Container>
    )
}
  
  export default LeaderBoard;
  