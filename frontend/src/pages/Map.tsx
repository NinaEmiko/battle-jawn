import { useEffect, useRef, useState } from 'react';
import { Stage, Layer, Rect, Circle } from 'react-konva';
import { DOORS, OBSTACLES } from '../helpers/constants';
import '../styling/Map.css'
import Controls from '../components/Controls';
import Display from '../components/Display';
import PageName from '../components/PageName';
import Container from '../components/Container';

const Map = ({props}:{props:any}) => {
    const [player, setPlayer] = useState({ x: 290, y: 270});
    const [startPositionSet, setStartPositionSet] = useState(false);
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
                props.setIsVisible("open-battle", props.heroId);
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
        handlePrevScreen(props.prevScreen);
        return () => {
        if (moveInterval.current) {
            clearInterval(moveInterval.current);
        }
        };
    }, []);
    console.log("Player position: y - " + player.y + ", x - " + player.x)
    console.log("startPositionSet: " + startPositionSet)


    const handlePrevScreen = () => {
        if (!startPositionSet) {

            if (props.prevScreen === "Heroes"){
                setStartPositionSet(true);
            } else if (props.prevScreen === "Inventory") {
                setStartPositionSet(true);
            } else if (props.prevScreen === "Shop"){
                setPlayer(prev => ({
                    x: 340,
                    y: 300
                }));
                setStartPositionSet(true);
            } else if (props.prevScreen === "Battle"){
                setPlayer(prev => ({
                    x: 410,
                    y: 300
                }));
                setStartPositionSet(true);
            }
        }
    }
  
    const handleBackButtonClick = () => {
        props.setIsVisible("exit-map", props.heroId)
    }
    const handleInventory = (id: number) => {
        props.setIsVisible("open-inventory", id);
    }
    const handleStore = (id: number) => {
        props.setIsVisible("open-store", id);
    }

    return (
        <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Close</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">Play</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
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
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button 
                            className="controls-btn"
                            onClick={() => handleInventory(props.heroId)}>
                                Bag
                            </button>
                        <button className="controls-btn"></button>
                        <button 
                            className="controls-btn"
                            onClick={() => handleBackButtonClick()}>
                                Back
                        </button>                    
                    </div>
                    <div className="controls-right">
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving('up')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('up')}
                            onTouchEnd={stopMoving}>
                                Up
                        </button>
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving('left')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('left')}
                            onTouchEnd={stopMoving}>
                                Left
                        </button>
                        <button 
                            className="controls-btn"
                            >
                                OK
                        </button>
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving('right')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('right')}
                            onTouchEnd={stopMoving}>
                                Right
                        </button>
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving('down')}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving('down')}
                            onTouchEnd={stopMoving}>
                                Down
                        </button>
                    </div>
                </>
            </Controls>
        </Container>
    );
};

export default Map;