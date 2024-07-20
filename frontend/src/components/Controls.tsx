import "./styles/Controls.css";

import React from 'react';

type ControlsProps = {
  handleClickLeftBtnTop?: () => void;
  handleClickLeftBtnMiddle?: () => void;
  handleClickLeftBtnBottom?: () => void;
  handleClickRightBtnTop?: () => void;
  handleClickRightBtnLeft?: () => void;
  handleClickRightBtnCenter?: () => void;
  handleClickRightBtnRight?: () => void;
  handleClickRightBtnBottom?: () => void;
  leftBtnTopText?: string;
  leftBtnMiddleText?: string;
  leftBtnBottomText?: string;
  rightBtnTopText?: string;
  rightBtnLeftText?: string;
  rightBtnCenterText?: string;
  rightBtnRightText?: string;
  rightBtnBottomText?: string;
  startMoving?: (direction: string) => void;
  stopMoving?: () => void;
};

const Controls: React.FC<ControlsProps> = ({
  handleClickLeftBtnTop,
  handleClickLeftBtnMiddle,
  handleClickLeftBtnBottom,
  handleClickRightBtnTop,
  handleClickRightBtnLeft,
  handleClickRightBtnCenter,
  handleClickRightBtnRight,
  handleClickRightBtnBottom,
  leftBtnTopText = "",
  leftBtnMiddleText = "",
  leftBtnBottomText = "",
  rightBtnTopText = "",
  rightBtnLeftText = "",
  rightBtnCenterText = "",
  rightBtnRightText = "",
  rightBtnBottomText = "",
  startMoving,
  stopMoving,
}) => {
  return (
    <div className="controls-jawn">
      <div className="controls-left">
        <button className="controls-btn" onClick={handleClickLeftBtnTop}>
          {leftBtnTopText}
        </button>
        <button className="controls-btn" onClick={handleClickLeftBtnMiddle}>
          {leftBtnMiddleText}
        </button>
        <button className="controls-btn" onClick={handleClickLeftBtnBottom}>
          {leftBtnBottomText}
        </button>
      </div>
      <div className="controls-right">
        <button className="controls-btn" 
        onClick={handleClickRightBtnTop}
        onMouseDown={() => startMoving && startMoving('up')}
        onMouseUp={stopMoving}
        onMouseLeave={stopMoving}
        onTouchStart={() => startMoving && startMoving('up')}
        onTouchEnd={stopMoving}
        >
          {rightBtnTopText}
        </button>
        <button className="controls-btn"
        onClick={handleClickRightBtnLeft}
        onMouseDown={() => startMoving && startMoving('left')}
        onMouseUp={stopMoving}
        onMouseLeave={stopMoving}
        onTouchStart={() => startMoving && startMoving('left')}
        onTouchEnd={stopMoving}
        >
          {rightBtnLeftText}
        </button>
        <button className="controls-btn" onClick={handleClickRightBtnCenter}>
          {rightBtnCenterText}
        </button>
        <button className="controls-btn"
        onClick={handleClickRightBtnRight}
        onMouseDown={() => startMoving && startMoving('right')}
        onMouseUp={stopMoving}
        onMouseLeave={stopMoving}
        onTouchStart={() => startMoving && startMoving('right')}
        onTouchEnd={stopMoving}
        >
          {rightBtnRightText}
        </button>
        <button className="controls-btn"
        onClick={handleClickRightBtnBottom}
        onMouseDown={() => startMoving && startMoving('down')}
        onMouseUp={stopMoving}
        onMouseLeave={stopMoving}
        onTouchStart={() => startMoving && startMoving('down')}
        onTouchEnd={stopMoving}
        >
          {rightBtnBottomText}
        </button>
      </div>
    </div>
  );
};

export default Controls;
