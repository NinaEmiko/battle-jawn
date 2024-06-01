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
                    {heroList.map((hero) => (
                        <div className="leader-board-jawn" key={hero.winCount}>
                            <div className="leader-board-hero-name">
                                {hero.name}
                            </div>
                            <div className="value-jawn">
                                {hero.winCount} wins
                            </div>
                        </div>
                    ))}
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={() => handleNavigation("/")}>Back</button>                    </div>
                    <div className="controls-right">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}
  
  export default LeaderBoard;
  