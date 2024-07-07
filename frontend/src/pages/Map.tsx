import { useEffect, useRef, useState } from 'react';
import { Stage, Layer, Rect, Circle } from 'react-konva';
import { DOORS, OBSTACLES } from '../helpers/constants';
import '../styling/Map.css'
import Controls from '../components/Controls';
import Display from '../components/Display';
import PageName from '../components/PageName';
import Container from '../components/Container';
import Cookies from 'js-cookie';

const Map = ({props}:{props:any}) => {
    const [player, setPlayer] = useState({ x: 835, y: 972});
    const [startPositionSet, setStartPositionSet] = useState(false);
    const playerSize = 10;
    const moveSpeed = 10; 
    const mapSize = 4500; 
    const moveInterval = useRef<NodeJS.Timeout | null>(null);
    const obstacles = OBSTACLES.STARTING_MAP_OBSTACLES;
    const borders = OBSTACLES.STARTING_MAP_BORDER;
    const shop = DOORS.STORE;
    const arena = DOORS.ARENA;

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
                        newY = Math.min(prev.y + moveSpeed, mapSize - playerSize);
                        break;
                    case 'left':
                        newX = Math.max(prev.x - moveSpeed, 0);
                        break;
                    case 'right':
                        newX = Math.min(prev.x + moveSpeed, mapSize - playerSize);
                        break;
                    default:
                        break;
                }

                // Check collision with shop
                if (
                    newX < shop.x + shop.width &&
                    newX + playerSize > shop.x &&
                    newY < shop.y + shop.height &&
                    newY + playerSize > shop.y
                ) {
                    handleStore(props.heroId);
                } else if (
                    newX < arena.x + arena.width &&
                    newX + playerSize > arena.x &&
                    newY < arena.y + arena.height &&
                    newY + playerSize > arena.y
                ) {
                    props.setIsVisible("open-battle", props.heroId);
                }

                // Check collision with obstacles
                const collision = obstacles.some((obstacle) =>
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
        // if (props.heroDead) {
        //     setPlayer(graveStone);
        //     //rest hero
        // }
    }, []);

    const handlePrevScreen = () => {
        if (!startPositionSet) {

            if (props.prevScreen === "Heroes"){
                checkCoordinates();
                setStartPositionSet(true);
            } else if (props.prevScreen === "Inventory") {
                checkCoordinates();
                setStartPositionSet(true);
            } else if (props.prevScreen === "Shop"){
                setPlayer(prev => ({
                    x: 835,
                    y: 972
                }));
                setStartPositionSet(true);
            } else if (props.prevScreen === "Battle"){
                setPlayer(prev => ({
                    x: 720,
                    y: 975
                }));
                setStartPositionSet(true);
            }
        }
    }

    const checkCoordinates = () => {
        const storedCoordinates = Cookies.get('coordinates');
        if (storedCoordinates) {
            setPlayer(JSON.parse(storedCoordinates));        
        }
    }
  
    const handleBackButtonClick = () => {
        Cookies.set('coordinates', JSON.stringify(player)); 
        props.setIsVisible("exit-map", props.heroId)
    }
    const handleInventory = (id: number) => {
        Cookies.set('coordinates', JSON.stringify(player)); 
        props.setIsVisible("open-inventory", id);
    }
    const handleStore = (id: number) => {
        props.setIsVisible("open-store", id);
    }

    // console.log("Position: " + player.x / 2 + ", " + player.y / 2)

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
                <div className="container-map-jawn"
                                    style={{
                                        position: 'relative',
                                        overflow: 'hidden',
                                        width: '100%',
                                        height: '100%',
                                        border: '1px solid black',
                                    }}>
                    <div
                        className="map"
                        style={{
                            position: 'absolute',
                            top: `calc(50% - ${player.y}px)`,
                            left: `calc(50% - ${player.x}px)`,
                        }}
                        
                    >

                        <Stage width={mapSize} height={mapSize}>
                            <Layer>
                                <Circle
                                    x={player.x}
                                    y={player.y}
                                    radius={playerSize}
                                    fill="red"
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
                                {borders.map((border, index) => (
                                    <Rect
                                        key={index}
                                        x={border.x}
                                        y={border.y}
                                        width={border.width}
                                        height={border.height}
                                        // fill="blue"
                                    />
                                ))}
                                <Rect
                                    x={arena.x}
                                    y={arena.y}
                                    width={arena.width}
                                    height={arena.height}
                                    // fill="yellow"
                                />
                                <Rect
                                    x={shop.x}
                                    y={shop.y}
                                    width={shop.width}
                                    height={shop.height}
                                    // fill="yellow"
                                />
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