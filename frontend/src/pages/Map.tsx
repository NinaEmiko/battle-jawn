import { useEffect, useRef, useState } from 'react';
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
    const playerSize = 20;
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
                    Cookies.set('coordinates' + props.heroId, JSON.stringify(player)); 
                    props.setIsVisible("Battle", props.heroId);
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
        handlePrevScreen();
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
            checkCoordinates();
            setStartPositionSet(true);
        }
    }

    const checkCoordinates = () => {
        const storedCoordinates = Cookies.get('coordinates' + props.heroId);
        if (storedCoordinates) {
            setPlayer(JSON.parse(storedCoordinates));        
        }
    }
  
    const handleBackButtonClick = () => {
        Cookies.set('coordinates' + props.heroId, JSON.stringify(player)); 
        props.setIsVisible("Heroes", props.heroId)
    }
    const handleInventory = (id: number) => {
        Cookies.set('coordinates' + props.heroId, JSON.stringify(player)); 
        props.setIsVisible("Inventory", id);
    }
    const handleStore = (id: number) => {
        Cookies.set('coordinates' + props.heroId, JSON.stringify(player)); 
        props.setIsVisible("Shop", id);
    }

    return (
        <Container>
            <PageName props={{title: "Play", currentUser: props.currentUser, toggleNav:props.toggleNav}} />
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
                        <div className="stage" style={{ width: mapSize, height: mapSize }}>
                            {/* Player */}
                            <div
                                className="circle"
                                style={{
                                    position: 'absolute',
                                    left: player.x,
                                    top: player.y,
                                    width: playerSize,
                                    height: playerSize,
                                    backgroundColor: 'black',
                                    borderRadius: '50%',
                                }}
                            >
                            </div>

                            {/* Obstacles */}
                            {obstacles.map((obstacle, index) => (
                                <div
                                    key={index}
                                    className="rect"
                                    style={{
                                        position: 'absolute',
                                        left: obstacle.x,
                                        top: obstacle.y,
                                        width: obstacle.width,
                                        height: obstacle.height,
                                        // backgroundColor: 'blue',
                                    }}
                                >
                                </div>
                            ))}

                            {/* Borders */}
                            {borders.map((border, index) => (
                                <div
                                    key={index}
                                    className="rect"
                                    style={{
                                        position: 'absolute',
                                        left: border.x,
                                        top: border.y,
                                        width: border.width,
                                        height: border.height,
                                        // backgroundColor: 'blue',
                                    }}
                                >
                                </div>
                            ))}

                            {/* Arena */}
                            <div
                                className="rect"
                                style={{
                                    position: 'absolute',
                                    left: arena.x,
                                    top: arena.y,
                                    width: arena.width,
                                    height: arena.height,
                                    // backgroundColor: 'yellow',
                                }}
                            >
                            </div>

                            {/* Shop */}
                            <div
                                className="rect"
                                style={{
                                    position: 'absolute',
                                    left: shop.x,
                                    top: shop.y,
                                    width: shop.width,
                                    height: shop.height,
                                    // backgroundColor: 'yellow',
                                }}
                            >
                            </div>
                        </div>
                    </div>
                </div>
            </Display>
            <Controls
                handleClickLeftBtnTop={() => handleInventory(props.heroId)}
                leftBtnTopText="Bag"
                handleClickLeftBtnBottom={() => handleBackButtonClick()}
                leftBtnBottomText="Back"
                rightBtnTopText="ᐃ"
                rightBtnLeftText="ᐊ"
                rightBtnRightText="ᐅ"
                rightBtnBottomText="ᐁ"
                startMoving={startMoving}
                stopMoving={stopMoving}
            />
        </Container>
    );
};

export default Map;