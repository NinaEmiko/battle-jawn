import React from "react";
import "../styling/UserPromptText.css";

interface UserPromptTextProp {
  text: string;
}

const UserPromptText: React.FC<UserPromptTextProp> = ({ text }) => {
  return <div className="userPrompt">{text}</div>;
};

export default UserPromptText;
