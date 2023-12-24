import axios from "axios";
import { useEffect, useState } from "react";

const LeaderBoard = ({props}:{props:any}) => {
    const [heroList, setHeroList] = useState([]);

    const fetchHeroes = async () => {
        try {
            const response = await
            axios.get('http://localhost:8080/api/hero/list')
            setHeroList(response.data);
            } catch (error) {
            console.error('Error fetching Hero data: ', error)
            }
        }
      
    useEffect(() => {
        fetchHeroes();
    }, [])

    return (
        <div className="container-jawn-login-form">
            <h1 className="title-jawn">Leader Board</h1>
            <div className="">
                {heroList.map((hero) => (
                    <div className="container-jawn-hero-card" key={hero.winCount}>
                        <table>
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
        </div>
    );
  };
  
  export default LeaderBoard;
  