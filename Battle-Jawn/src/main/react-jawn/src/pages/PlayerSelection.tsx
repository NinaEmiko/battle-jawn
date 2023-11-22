import { useState } from "react";
import PlayerTips from "../components/PlayerTips";
import UserPromptText from "../components/UserPromptText";
import axios from "axios";

function PlayerSelection({roleChosen}:{roleChosen: any}) {
  const [role, setRole] = useState('');
  const [chosenRole, setChosenRole] = useState('');

  const handleClickBegin = () => {
      if (role !== '') {
          axios.post('http://localhost:8080/api/hero', { role })
                .then((response) => {
                  roleChosen(response.data.id);
                  console.log('Hero created successfully: ', response.data);
                })
                .catch((error) => {
                  console.error('Error creating hero:', error);
                });
      } else {
        setChosenRole('You have not yet chosen a class!')
      }
  }

  const handleClickButtonOne = () => {
  setRole("Tank")
  setChosenRole("You have chosen to be a Tank")
  }

  const handleClickButtonTwo = () => {
    setRole("Healer")
    setChosenRole("You have chosen to be a Healer")
    }

  const handleClickButtonThree = () => {
    setRole("DPS")
    setChosenRole("You have chosen to be a DPS")
    }

  const handleClickButtonFour = () => {
    setRole("Caster")
    setChosenRole("You have chosen to be a Caster")
    }

  return (
      <div className="container">
        <PlayerTips />
        <div>
          <div className="user-prompt-wrapper">
            <UserPromptText text="Choose a Class: "></UserPromptText>
            {chosenRole && (
            <p>{chosenRole}</p>
            )}
            <div className="btn-grid" id="option-buttons">
              <button onClick={handleClickButtonOne} className="btn" id="button1">Tank</button>
              <button onClick={handleClickButtonTwo} className="btn" id="button2">Healer</button>
              <button onClick={handleClickButtonThree} className="btn" id="button3">DPS</button>
              <button onClick={handleClickButtonFour} className="btn" id="button4">Caster</button>
            </div>
            <button onClick={handleClickBegin} className="btn" id="button5">Begin</button>
          </div>
        </div>
      </div>
    );
}

export default PlayerSelection;