import "../styling/MyHeroes.css";
import { determineMaxExperience, determineNumerator } from "../helpers/experience_helper";

const Hero = ({props}:{props:any}) => {

    return (
        <div>
            <div className="hero-name-level">
                <div className="hero-name">
                    {props.name}
                </div>
                <div className="hero-level">
                  Lvl {props.level} {props.role}
                </div>
            </div>
            <table className="my-heroes-table">
                <tbody>
                    <tr>
                        <td className="row-jawn">Health:</td>
                        <td className="data-jawn" id="health-jawn">{props.health} / {props.maxHealth}</td>
                    </tr>

                    {props.role == "Tank" &&

                        <tr>
                            <td className="row-jawn">
                                Power:
                            </td>
                            <td className="data-jawn" id="resource-jawn">
                                {props.resource} / {props.maxResource}
                            </td>
                        </tr>
                    }

                    {props.role == "Healer" &&

                    <tr>
                        <td className="row-jawn">
                            Spirit:
                        </td>
                        <td className="data-jawn" id="resource-jawn">
                            {props.resource} / {props.maxResource}
                        </td>
                    </tr>
                    }

                    {props.role == "Caster" &&

                        <tr>
                            <td className="row-jawn">
                                Magic:
                            </td>
                            <td className="data-jawn" id="resource-jawn">
                                {props.resource} / {props.maxResource}
                            </td>
                        </tr>
                    }

                    {props.role == "DPS" &&

                        <tr>
                            <td className="row-jawn">
                                Energy:
                            </td>
                            <td className="data-jawn" id="resource-jawn">
                                {props.resource} / {props.maxResource}
                            </td>
                        </tr>
                    }

                    <tr>
                        <td className="row-jawn">
                            Coins:
                        </td>
                        <td className="data-jawn" id="health-jawn">
                            {props.coins}
                        </td>
                    </tr>
                </tbody>
            </table>

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
                            <td className="stats-table-data">{props.winCount}</td>
                            <td className="stats-table-data">{props.lossCount}</td>
                            <td className="stats-table-data">{props.runCount}</td>
                            <td className="stats-table-data">{props.winStreak}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div className="experience-bar-container">
                <progress className='experience-bar' value={determineNumerator(props.level, props.experience)} max={determineMaxExperience(props.level)}></progress>
                <span className="experience-fraction">
                    {determineNumerator(props.level, props.experience)}/{determineMaxExperience(props.level)}
                </span>
            </div>
        </div>
    )
}
export default Hero;