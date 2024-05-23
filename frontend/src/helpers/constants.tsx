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
};