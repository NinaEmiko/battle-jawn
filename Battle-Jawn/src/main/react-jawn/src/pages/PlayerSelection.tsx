import { useEffect, useState } from "react";
import PlayerTips from "../components/PlayerTips";
import UserPromptText from "../components/UserPromptText";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function PlayerSelection() {
  const [role, setRole] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    if (role !== '') {
    axios.post('http://localhost:8080/api/toon', { role })
      .then((response) => {
        const id = response.data.id;
        localStorage.setItem('toonId', id);
        console.log('Toon created successfully:', response.data);
        navigate("/battle-screen");
      })
      .catch((error) => {
        console.error('Error creating toon:', error);
      });
    }
  })

  return (
      <div className="container">
        <PlayerTips />
        <div>
          <div className="user-prompt-wrapper">
            <UserPromptText text="Choose a Class: "></UserPromptText>
            <div className="btn-grid" id="option-buttons">
              <button onClick={() => setRole("Tank")} className="btn" id="button1">Tank</button>
              <button onClick={() => setRole("Healer")} className="btn" id="button2">Healer</button>
              <button onClick={() => setRole("DPS")} className="btn" id="button3">DPS</button>
              <button onClick={() => setRole("Caster")} className="btn" id="button4">Caster</button>
            </div>
          </div>
        </div>
      </div>
    );
}

export default PlayerSelection;