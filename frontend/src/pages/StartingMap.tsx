import { useEffect, useRef, useState } from 'react';
import { Stage, Layer, Rect, Circle } from 'react-konva';
import { ENTRANCES, OBSTACLES } from '../helpers/constants';
import '../styling/StartingMap.css'
import Controls from '../components/Controls';
import Display from '../components/Display';
import PageName from '../components/PageName';
import Container from '../components/Container';
import Cookies from 'js-cookie';

const StartingMap = ({props}:{props:any}) => {
    const [player, setPlayer] = useState({ x: 200, y: 200});
    const playerSize = 20;
    const obstacles = OBSTACLES.STARTING_MAP;
    const southEntrance = ENTRANCES.STARTING_MAP_SOUTH;
    const northEntrance = ENTRANCES.STARTING_MAP_NORTH;
    const westEntrance = ENTRANCES.STARTING_MAP_WEST;
    const eastEntrance = ENTRANCES.STARTING_MAP_EAST;
    const shop = ENTRANCES.STARTING_MAP_SHOP;
    const moveInterval = useRef<NodeJS.Timeout | null>(null);

    const isColliding = (newX: number, newY: number) => {
        const playerRadius = playerSize / 2;
        return obstacles.some((obstacle) => {
          return (
            newX + playerRadius > obstacle.x &&
            newX - playerRadius < obstacle.x + obstacle.width &&
            newY + playerRadius > obstacle.y &&
            newY - playerRadius < obstacle.y + obstacle.height
          );
        });
      };

    const enterShop = (newX: number, newY: number) => {
        const playerRadius = playerSize / 2;
        return (
            newX + playerRadius > shop.x &&
            newX - playerRadius < shop.x + shop.width &&
            newY + playerRadius > shop.y &&
            newY - playerRadius < shop.y + shop.height
        );
    };

    const enterBattle = (newX: number, newY: number) => {
        const playerRadius = playerSize / 2;
        const newTargets = [southEntrance, northEntrance, westEntrance, eastEntrance];
        return newTargets.some((target) => {
          return (
            newX + playerRadius > target.x &&
            newX - playerRadius < target.x + target.width &&
            newY + playerRadius > target.y &&
            newY - playerRadius < target.y + target.height
          );
        });
      };

    const startMoving = (dx: number, dy: number) => {
        if (moveInterval.current) return;

        moveInterval.current = setInterval(() => {
            setPlayer((prevState) => {
            const newX = prevState.x + dx;
            const newY = prevState.y + dy;
            if (!isColliding(newX, newY)) {
                if (enterShop(newX, newY)) {
                    handleStore(props.heroId);
                    console.log("shop entered")
                }
                if (enterBattle(newX, newY)) {
                    props.setIsVisible("open-battle", props.heroId);
                    console.log("battle entered")
                }
                return { x: newX, y: newY };
            } 
            return prevState;
            });
        }, 50);
    };
    
    const stopMoving = () => {
        if (moveInterval.current) {
            clearInterval(moveInterval.current);
            moveInterval.current = null;
        }
    };

    const handleBackButtonClick = () => {
        // Cookies.set('coordinates', JSON.stringify(player)); 
        props.setIsVisible("exit-map", props.heroId)
    }
    const handleInventory = (id: number) => {
        // Cookies.set('coordinates', JSON.stringify(player)); 
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
                    <div className="page-name-txt">Lala Land</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
                <div className="container-map-jawn">
                    <div className="starting-map">
                        <Stage width={500} height={500}>
                            <Layer>
                                <Circle
                                    x={player.x}
                                    y={player.y}
                                    width={playerSize}
                                    height={playerSize}
                                    radius={playerSize / 2}
                                    fill="red"
                                />
                                    <Rect 
                                        x={southEntrance.x}
                                        y={southEntrance.y}
                                        width={southEntrance.width}
                                        height={southEntrance.height}
                                        // fill="yellow"
                                    />
                                    <Rect 
                                        x={northEntrance.x}
                                        y={northEntrance.y}
                                        width={northEntrance.width}
                                        height={northEntrance.height}
                                        // fill="yellow"
                                    />
                                    <Rect 
                                        x={westEntrance.x}
                                        y={westEntrance.y}
                                        width={westEntrance.width}
                                        height={westEntrance.height}
                                        // fill="yellow"
                                    />
                                    <Rect 
                                        x={eastEntrance.x}
                                        y={eastEntrance.y}
                                        width={eastEntrance.width}
                                        height={eastEntrance.height}
                                        // fill="yellow"
                                    />
                                    <Rect 
                                        x={shop.x}
                                        y={shop.y}
                                        width={shop.width}
                                        height={shop.height}
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
                            className="controls-btn" onClick={() => handleInventory(props.heroId)}>
                                Bag
                            </button>
                        <button className="controls-btn"></button>
                        <button 
                            className="controls-btn" onClick={() => handleBackButtonClick()}>
                                Back
                        </button>                    
                    </div>
                    <div className="controls-right">
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving(0, -3.5)}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving(0, -3.5)}
                            onTouchEnd={stopMoving}
                            >
                                Up
                        </button>
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving(-3.5, 0)}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving(-3.5, 0)}
                            onTouchEnd={stopMoving}
                            >
                                Left
                        </button>
                        <button 
                            className="controls-btn"
                            >
                                OK
                        </button>
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving(3.5, 0)}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving(3.5, 0)}
                            onTouchEnd={stopMoving}
                            >
                                Right
                        </button>
                        <button 
                            className="controls-btn"
                            onMouseDown={() => startMoving(0, 3.5)}
                            onMouseUp={stopMoving}
                            onMouseLeave={stopMoving}
                            onTouchStart={() => startMoving(0, 3.5)}
                            onTouchEnd={stopMoving}
                            >
                                Down
                        </button>
                    </div>
                </>
            </Controls>
        </Container>
    );
};

export default StartingMap;