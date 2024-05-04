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
  HERO_MOVE: `${BASE_URL}/api/hero-move`,
  ENEMY_MOVE: `${BASE_URL}/api/enemy-move`,
  CREATE_NEW_BATTLE_SESSION: `${BASE_URL}/api/battle-session/create`,
  FETCH_ENEMY: `${BASE_URL}/api/enemy/`,
  FETCH_BATTLE_HISTORY_MESSAGE: `${BASE_URL}/api/battle-history-message/`,
  END_BATTLE_SESSION: `${BASE_URL}/api/battle-session/end`,
  FETCH_BATTLE_SESSION: `${BASE_URL}/api/battle-session/`,
};