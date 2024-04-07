import axios from "axios";
import { useEffect, useState } from "react";
import classNames from 'classnames';

const Store = ({props}:{props:any}) => {
    const [heroList, setHeroList] = useState([]);

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

    return (
        <div className="container-jawn-login-form">
            <h1 className="title-jawn">Store</h1>
            <div className="">
                {heroList.map((hero) => (
                    <div className="container-jawn-hero-card" key={hero.coins}>
                        <div className="store-hero-name">
                            {hero.name}
                        </div>
                        <div className="store-hero-coins">
                            {hero.coins} coins
                        </div>
                        <div>
                                {hero.role !="Healer" &&
                        <table>
                            <tbody>
                                
                                <tr>
                                    <td className="row-jawn">Potion</td>
                                    <td className="data-jawn-win">1 coin</td>
                                </tr>
                                <tr>
                                    <td className="row-jawn"></td>
                                    <td className="buy-btn"><button className="buy-btn">Buy</button></td>
                                </tr>
                            
                            </tbody>
                        </table>
                        }
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
  };
  
  export default Store;
  