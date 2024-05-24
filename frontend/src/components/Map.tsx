import { useEffect, useRef, useState } from 'react';
import { Stage, Layer, Rect, Circle } from 'react-konva';
import "../styling/Map.css";
import { DOORS, OBSTACLES } from '../helpers/constants';

const Map = ({props}:{props:any}) => {
    const [player, setPlayer] = useState({ x: 210, y: 210});
    const [storeActive, setStoreActive] = useState(false);
    const playerSize = 10;
    const boxSize = 420;
    const moveSpeed = 5; 
    const mapSize = 840; 
    const moveInterval = useRef<NodeJS.Timeout | null>(null);
    const storeDoor = DOORS.STORE;
    const arenaDoor = DOORS.ARENA;
    const obstacles = OBSTACLES.MAP_1;

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
                newX < storeDoor.x + storeDoor.width &&
                newX + playerSize > storeDoor.x &&
                newY < storeDoor.y + storeDoor.height &&
                newY + playerSize > storeDoor.y
            ) {
                handleStore(props.heroId);
            } else if (
                newX < arenaDoor.x + arenaDoor.width &&
                newX + playerSize > storeDoor.x &&
                newY < arenaDoor.y + arenaDoor.height &&
                newY + playerSize > storeDoor.y
            ) {
                props.setIsVisible("battle", props.heroId);
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
  
    const handleBackButtonClick = () => {
        props.setIsVisible("exit-btn-map", props.heroId)
    }
    const handleInventory = (id: number) => {
        props.setIsVisible("open-inventory-map", id);
    }
    const handleStore = (id: number) => {
        props.setIsVisible("open-store-map", id);
    }

return (
        <div className="home-background-jawn">
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
                                    x={storeDoor.x}
                                    y={storeDoor.y}
                                    width={storeDoor.width}
                                    height={storeDoor.height}
                                    // fill="yellow"
                                />
                                <Rect
                                    x={arenaDoor.x}
                                    y={arenaDoor.y}
                                    width={arenaDoor.width}
                                    height={arenaDoor.height}
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
                <button onClick={() => handleInventory(props.heroId)} className="action-btn">Bag</button>
                <button onClick={() => handleBackButtonClick()} className="action-btn">Exit</button>



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


                </div>              
            </>
        </div>
    );
};

export default Map;