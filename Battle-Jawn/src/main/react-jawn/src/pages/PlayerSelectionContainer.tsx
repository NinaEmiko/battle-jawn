import UserPromptText from "../components/UserPromptText";
import "../styling/Container.css";
import PlayerTips from "../components/PlayerTips";
import { ChangeEvent, useState } from "react";
import axios from "axios";

function PlayerSelectionContainer() {

  const [heroName, setHeroName] = useState("");
  const [role, setRole] = useState("");
  const [accountId, setAccountId] = useState(1);

  const createHero = () => {
    axios.post('http://localhost:8080/api/hero', { name: heroName, role: role, accountId: accountId, })
      .then((response) => {
        console.log('Hero created successfully:', response.data);
      })
      .catch((error) => {
        console.error('Error creating hero:', error);
      });
  };

  const handleHeroNameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setHeroName(event.target.value);
  }

  const handleRoleChange = (chosenRole: string) => {
    setRole(chosenRole);
    console.log("Role chosen successfully: " + chosenRole)
  }

  return (
    <div className="container">
      <PlayerTips />
      <div>
        <div className="user-prompt-wrapper">
          <UserPromptText text="Create A Hero"></UserPromptText>
          <UserPromptText text="Hero Name: "></UserPromptText>
          <input className="input" type="text"
            placeholder="Enter Hero Name"
            value={heroName}
            onChange={handleHeroNameChange}></input>
          <UserPromptText text="Choose a Class: "></UserPromptText>
          <div className="btn-grid" id="option-buttons">
            <button onClick={() => handleRoleChange("Tank")} className="btn" id="button1">Tank</button>
            <button onClick={() => handleRoleChange("Healer")} className="btn" id="button2">Healer</button>
            <button onClick={() => handleRoleChange("Caster")} className="btn" id="button3">Caster</button>
            <button onClick={() => handleRoleChange("DPS")} className="btn" id="button4">DPS</button>
          </div>
          <button onClick={createHero} className="btn" id="button5">Create Hero</button>
        </div>
      </div>
    </div>
  );
}

export default PlayerSelectionContainer;
