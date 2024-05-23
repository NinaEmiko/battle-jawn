import { useEffect, useRef, useState } from 'react';
import { Stage, Layer, Rect, Circle } from 'react-konva';
import "../styling/Map.css";
import Store from "./Store";
import Inventory from "./Inventory";

const Map = ({props}:{props:any}) => {
    const [player, setPlayer] = useState({ x: 210, y: 210});
    const [storeActive, setStoreActive] = useState(false);
    const [inventoryActive, setInventoryActive] = useState(false);
    const playerSize = 10;
    const boxSize = 420;
    const moveSpeed = 5; 
    const mapSize = 840; 
    const moveInterval = useRef<NodeJS.Timeout | null>(null);

    const targetSection = {
        x: 325,
        y: 280,
        width: 25,
        height: 10
    };

    const arena = {
        x: 400,
        y: 280,
        width: 25,
        height: 10
    };

    const obstacles = [
        { x: 165, y: 330, width: 290, height: 100 },
        { x: 165, y: 200, width: 65, height: 130 },
        { x: 60, y: 90, width: 185, height: 110 },
        { x: 285, y: 90, width: 185, height: 135 },
        { x: 330, y: 30, width: 150, height: 135 },
        { x: 280, y: 0, width: 150, height: 35 },
        { x: 0, y: 0, width: 30, height: 500 },
        { x: 0, y: 0, width: 500, height: 35 },
        { x: 0, y: 240, width: 110, height: 10 },
        { x: 100, y: 240, width: 10, height: 230 },
        { x: 325, y: 245, width: 55, height: 40 },
        { x: 380, y: 245, width: 55, height: 40 },
      ];

    const handleSubComponentButtonClick = (component: string) => {
        if (component === "store"){
        setStoreActive(false);
        stopMoving();
        } else if (component === "inventory") {
        setInventoryActive(false);
        stopMoving();
        }
    }

    const startMoving = (direction: string) => {
        if (moveInterval.current) {
        clearInterval(moveInterval.current);
        }
        moveInterval.current = setInterval(() => {
        setPlayer((prev) => {
            let newX = prev.x;
            let newY = prev.y;

            switch (direction) {
            case 'up':
                newY = Math.max(prev.y - moveSpeed, 0);
                break;
            case 'down':
                newY = Math.min(prev.y + moveSpeed, boxSize - playerSize);
                break;
            case 'left':
                newX = Math.max(prev.x - moveSpeed, 0);
                break;
            case 'right':
                newX = Math.min(prev.x + moveSpeed, boxSize - playerSize);
                break;
            default:
                break;
            }

            if (
                newX < targetSection.x + targetSection.width &&
                newX + playerSize > targetSection.x &&
                newY < targetSection.y + targetSection.height &&
                newY + playerSize > targetSection.y
            ) {
                setStoreActive(true);
            } else if (
                newX < arena.x + arena.width &&
                newX + playerSize > targetSection.x &&
                newY < arena.y + arena.height &&
                newY + playerSize > targetSection.y
            ) {
                props.setIsVisible("battle");
            }

        const collision = obstacles.some(obstacle => 
            newX < obstacle.x + obstacle.width &&
            newX + playerSize > obstacle.x &&
            newY < obstacle.y + obstacle.height &&
            newY + playerSize > obstacle.y
          );
  
          if (collision) {
            return prev; 
          }

            return { x: newX, y: newY };
        });
        }, 100);
    };

    const stopMoving = () => {
        if (moveInterval.current) {
        clearInterval(moveInterval.current);
        moveInterval.current = null;
        }
    };

    useEffect(() => {
        return () => {
        if (moveInterval.current) {
            clearInterval(moveInterval.current);
        }
        };
    }, []);

    useEffect(() => {
        if (!storeActive) {
        setPlayer(prev => ({
            x: 325,
            y: 300
        }));
        }
    }, [storeActive]);

    const handleInventory = () => {
        setInventoryActive(true);
    }  
  
    const handleBackButtonClick = () => {
        props.setIsVisible("exit-btn-map")
    }

return (
        <div className="home-background-jawn">

            {storeActive && 
                <Store props={{heroId:props.heroId, setIsVisible: handleSubComponentButtonClick}} />
            }

            {inventoryActive &&
                <Inventory props={{heroId:props.heroId, setIsVisible: handleSubComponentButtonClick}} />
            }

            {!storeActive && !inventoryActive && (

                <>
                    <div className="container-map-jawn">
                        <div
                            className="map"
                            style={{
                            top: -player.y + boxSize / 2 - playerSize / 2,
                            left: -player.x + boxSize / 2 - playerSize / 2,
                            }}
                        >

                            <Stage width={mapSize} height={mapSize}>
                                <Layer>
                                    <Circle
                                        x={player.x}
                                        y={player.y}
                                        width={playerSize}
                                        height={playerSize}
                                        radius={playerSize}
                                        fill="red"
                                    />
                                    <Rect
                                        x={targetSection.x}
                                        y={targetSection.y}
                                        width={targetSection.width}
                                        height={targetSection.height}
                                        // fill="yellow"
                                    />
                                    <Rect
                                        x={arena.x}
                                        y={arena.y}
                                        width={arena.width}
                                        height={arena.height}
                                        // fill="yellow"
                                    />
                                    {obstacles.map((obstacle, index) => (
                                        <Rect
                                            key={index}
                                            x={obstacle.x}
                                            y={obstacle.y}
                                            width={obstacle.width}
                                            height={obstacle.height}
                                            // fill="blue"
                                        />
                                    ))}
                                </Layer>
                            </Stage>

                        </div>
                    </div>

                    <div className="controls">
                        <button className="direction-btn"
                            onMouseDown={() => startMoving('up')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('up')}
                            onTouchEnd={stopMoving}
                        >
                            Up
                        </button>
                        <button className="direction-btn"
                            onMouseDown={() => startMoving('left')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('left')}
                            onTouchEnd={stopMoving}
                        >
                            Left
                        </button>
                        <button className="direction-btn"
                            onMouseDown={() => startMoving('down')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('down')}
                            onTouchEnd={stopMoving}
                        >
                            Down
                        </button>
                        <button className="direction-btn"
                            onMouseDown={() => startMoving('right')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('right')}
                            onTouchEnd={stopMoving}
                        >
                            Right
                        </button>
                        
                        <button onClick={() => handleInventory()} className="direction-btn">Inventory</button>
                    </div>
                </>
            )}
        </div>
    );
};

export default Map;