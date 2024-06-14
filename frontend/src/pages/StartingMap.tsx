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
    const [offset, setOffset] = useState<{ x: number, y: number }>({ x: 0, y: 0 });
    const playerSize = 20;
    const stageSize = 500;
    const playerCenterX = stageSize / 2;
    const playerCenterY = stageSize / 2;
    const playerRadius = playerSize / 2;
    const obstacles = OBSTACLES.STARTING_MAP;
    const southEntrance = ENTRANCES.STARTING_MAP_SOUTH;
    const northEntrance = ENTRANCES.STARTING_MAP_NORTH;
    const westEntrance = ENTRANCES.STARTING_MAP_WEST;
    const eastEntrance = ENTRANCES.STARTING_MAP_EAST;
    const shop = ENTRANCES.STARTING_MAP_SHOP;
    const moveInterval = useRef<NodeJS.Timeout | null>(null);

    const isColliding = (newOffsetX: number, newOffsetY: number) => {
        return obstacles.some((obstacle) => {
          return (
            playerCenterX + playerRadius > obstacle.x + newOffsetX &&
            playerCenterX - playerRadius < obstacle.x + obstacle.width + newOffsetX &&
            playerCenterY + playerRadius > obstacle.y + newOffsetY &&
            playerCenterY - playerRadius < obstacle.y + obstacle.height + newOffsetY
          );
        });
      };

    const enterShop = (newOffsetX: number, newOffsetY: number) => {
        return (
            playerCenterX + playerRadius > shop.x + newOffsetX &&
            playerCenterX - playerRadius < shop.x + shop.width + newOffsetX &&
            playerCenterY + playerRadius > shop.y + newOffsetY &&
            playerCenterY - playerRadius < shop.y + shop.height + newOffsetY
        );
    };

    const enterBattle = (newOffsetX: number, newOffsetY: number) => {
        const newTargets = [southEntrance, northEntrance, westEntrance, eastEntrance];
        return newTargets.some((target) => {
          return (
            playerCenterX + playerRadius > target.x + newOffsetX &&
            playerCenterX - playerRadius < target.x + target.width + newOffsetX &&
            playerCenterY + playerRadius > target.y + newOffsetY &&
            playerCenterY - playerRadius < target.y + target.height + newOffsetY
          );
        });
      };

    const startMoving = (dx: number, dy: number) => {
        if (moveInterval.current) return;

        moveInterval.current = setInterval(() => {
            setOffset((prevState) => {
                const newOffsetX = prevState.x + dx;
                const newOffsetY = prevState.y + dy;
                if (!isColliding(newOffsetX, newOffsetY)) {
                  if (enterShop(newOffsetX, newOffsetY)) {
                    handleStore(props.heroId);
                  }
                  if (enterBattle(newOffsetX, newOffsetY)) {
                    props.setIsVisible("open-battle", props.heroId);
                }
                  return { x: newOffsetX, y: newOffsetY };
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
                    <div className="starting-map"
                    >
                        <Stage width={stageSize} height={stageSize}>
                            <Layer>
                                <Circle
                                    x={stageSize / 2}
                                    y={stageSize / 2}
                                    width={playerSize}
                                    height={playerSize}
                                    radius={playerSize / 2}
                                    fill="red"
                                />
                                    <Rect 
                                        x={southEntrance.x + offset.x}
                                        y={southEntrance.y + offset.y}
                                        width={southEntrance.width}
                                        height={southEntrance.height}
                                        fill="yellow"
                                    />
                                    <Rect 
                                        x={northEntrance.x + offset.x}
                                        y={northEntrance.y + offset.y}
                                        width={northEntrance.width}
                                        height={northEntrance.height}
                                        fill="yellow"
                                    />
                                    <Rect 
                                        x={westEntrance.x + offset.x}
                                        y={westEntrance.y + offset.y}
                                        width={westEntrance.width}
                                        height={westEntrance.height}
                                        fill="yellow"
                                    />
                                    <Rect 
                                        x={eastEntrance.x + offset.x}
                                        y={eastEntrance.y + offset.y}
                                        width={eastEntrance.width}
                                        height={eastEntrance.height}
                                        fill="yellow"
                                    />
                                    <Rect 
                                        x={shop.x + offset.x}
                                        y={shop.y + offset.y}
                                        width={shop.width}
                                        height={shop.height}
                                        fill="yellow"
                                    />
                                {obstacles.map((obstacle, index) => (
                                    <Rect
                                        key={index}
                                        x={obstacle.x + offset.x}
                                        y={obstacle.y + offset.y}
                                        width={obstacle.width}
                                        height={obstacle.height}
                                        fill="blue"
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