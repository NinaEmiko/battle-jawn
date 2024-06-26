const BASE_URL = import.meta.env.VITE_REACT_APP_URL;

export const ENDPOINTS = {
  BUY: `${BASE_URL}/api/store/buy`,
  SELL: `${BASE_URL}/api/store/sell`,
  FETCH_HERO: `${BASE_URL}/api/hero/`,
  FETCH_HEROES: `${BASE_URL}/api/hero/list/`,
  FETCH_INVENTORY: `${BASE_URL}/api/inventory/`,
  HIGH_SCORE: `${BASE_URL}/api/hero/list/high-score`,
  FETCH_LOOT: `${BASE_URL}/api/loot/`,
  FETCH_EMPTY_SLOTS: `${BASE_URL}/api/inventory/slots/`,
  SELECT_LOOT: `${BASE_URL}/api/inventory/add/`,
  FETCH_PLAYER_TIPS: `${BASE_URL}/api/player-tip/random`,
  REST_HERO: `${BASE_URL}/api/hero/rest/`,
  DELETE_HERO: `${BASE_URL}/api/hero/delete/`,
  USE_POTION: `${BASE_URL}/api/inventory/potion/`,
  USE_WATER: `${BASE_URL}/api/inventory/water/`,
  HERO_MOVE: `${BASE_URL}/api/hero-move`,
  ENEMY_MOVE: `${BASE_URL}/api/enemy-move`,
  CREATE_NEW_BATTLE_SESSION: `${BASE_URL}/api/battle-session/create`,
  FETCH_ENEMY: `${BASE_URL}/api/enemy/`,
  FETCH_BATTLE_HISTORY_MESSAGE: `${BASE_URL}/api/battle-history-message/`,
  END_BATTLE_SESSION: `${BASE_URL}/api/battle-session/end`,
  FETCH_BATTLE_SESSION: `${BASE_URL}/api/battle-session/`,
};

export const DOORS = {
  STORE: {
    x: 325,
    y: 280,
    width: 25,
    height: 10
},
  ARENA: {
    x: 400,
    y: 280,
    width: 25,
    height: 10
  },
};

// export const STARTING_POINTS = {
//   GRAVESTONE: {
//     x: 325,
//     y: 280,
//     width: 25,
//     height: 10
//   }
// }

export const OBSTACLES = {
  MAP_1: [
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
  ],
  STARTING_MAP: [
    { x: 45, y: 375, width: 500, height: 30 },
    { x: 270, y: 150, width: 150, height: 15 },
    { x: 270, y: 325, width: 140, height: 15 },
    { x: 175, y: 325, width: 65, height: 15 },
    { x: 175, y: 225, width: 110, height: 25 },
    { x: 45, y: 45, width: 500, height: 15 },
    { x: 0, y: 45, width: 15, height: 115 },
    { x: 270, y: 150, width: 15, height: 100 },
    { x: 0, y: 220, width: 15, height: 185 },
    { x: 90, y: 225, width: 15, height: 125 },
    { x: 90, y: 225, width: 85, height: 100 },
    { x: 45, y: 80, width: 175, height: 95 },
    { x: 335, y: 45, width: 15, height: 280 },
  ],
};

export const ENTRANCES: { [key: string]: any } = {
  STARTING_MAP_SOUTH: { x: 15, y: 400, width: 30, height: 5 },
  STARTING_MAP_NORTH: { x: 15, y: 45, width: 30, height: 5 },
  STARTING_MAP_WEST: { x: 0, y: 150, width: 5, height: 75 },
  STARTING_MAP_EAST: { x: 345, y: 335, width: 5, height: 40 },
  STARTING_MAP_SHOP: { x: 190, y: 175, width: 20, height: 5 },
};