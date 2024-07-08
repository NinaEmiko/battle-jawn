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
    x: 825,
    y: 950,
    width: 20,
    height: 10
},
  ARENA: {
    x: 715,
    y: 955,
    width: 20,
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
    // { x: 165, y: 330, width: 290, height: 100 },
    // { x: 165, y: 200, width: 65, height: 130 },
    // { x: 60, y: 90, width: 185, height: 110 },
    // { x: 285, y: 90, width: 185, height: 135 },
    // { x: 330, y: 30, width: 150, height: 135 },
    // { x: 280, y: 0, width: 150, height: 35 },
    // { x: 0, y: 0, width: 30, height: 500 },
    // { x: 0, y: 0, width: 500, height: 35 },
    // { x: 0, y: 240, width: 110, height: 10 },
    // { x: 100, y: 240, width: 10, height: 230 },
    // { x: 325, y: 245, width: 55, height: 40 },
    // { x: 380, y: 245, width: 55, height: 40 },
  ],
  STARTING_MAP_BORDER: [
    {x: 0, y: 0, width: 10, height: 1600},
    {x: 0, y: 0, width: 2400, height: 10},
    {x: 2400, y: 0, width: 10, height: 1600},
    {x: 0, y: 1600, width: 2400, height: 10}
  ],
  STARTING_MAP_OBSTACLES: [
    // {x: 50, y: 50, width: 10, height: 10},

    {x: 0, y: 0, width: 10, height: 1600},//MAP BORDER
    {x: 0, y: 0, width: 2400, height: 10},//MAP BORDER
    {x: 2400, y: 0, width: 10, height: 1600},//MAP BORDER
    {x: 0, y: 1600, width: 2400, height: 10},//MAP BORDER
    {x: 2320, y: 975, width: 80, height: 10},//SOUTH EAST FOREST HORIZONTAL BORDERS
    {x: 2240, y: 1040, width: 80, height: 10},//x: -80, y: + 65
    {x: 2160, y: 1105, width: 80, height: 10},
    {x: 2080, y: 1170, width: 80, height: 10},
    {x: 2000, y: 1235, width: 80, height: 10},    
    {x: 1920, y: 1300, width: 80, height: 10},    
    {x: 1840, y: 1365, width: 80, height: 10},    
    {x: 1680, y: 1430, width: 160, height: 10},  //
    {x: 1760, y: 1430, width: 10, height: 130},//SOUTH EAST FOREST VERTICAL BORDERS
    {x: 1840, y: 1365, width: 10, height: 80},
    {x: 1920, y: 1300, width: 10, height: 80},
    {x: 2000, y: 1235, width: 10, height: 80},
    {x: 2080, y: 1170, width: 10, height: 80},
    {x: 2160, y: 1105, width: 10, height: 80},
    {x: 2240, y: 1040, width: 10, height: 80},
    {x: 2320, y: 975, width: 10, height: 80},
    {x: 1325, y: 1555, width: 450, height: 10}, //BOTTOM TREE LINE
    {x: 1280, y: 1235, width: 45, height: 375}, //SOUTHERN RIVER
    {x: 515, y: 1565, width: 775, height: 10}, //BOTTOM TREE RIGHT
    {x: 515, y: 1565, width: 10, height: 45}, //BOTTOM TREE RIGHT VERTICAL
    {x: 235, y: 1565, width: 225, height: 10}, //BOTTOM TREE LEFT
    {x: 450, y: 1565, width: 10, height: 45}, //BOTTOM TREE LEFT VERTICAL
    {x: 225, y: 1270, width: 10, height: 330}, //BOTTOM TREE CLIFF
    {x: 480, y: 1265, width: 10, height: 240}, //GARDEN FENCE VERTICAL
    {x: 235, y: 1360, width: 250, height: 15}, //GARDIN FENCE HORIZONTAL
    {x: 0, y: 1270, width: 225, height: 10}, //BOTTOM LEFT CLIFF HORIZONTAL

    //TOWN RESIDENCES
    {x: 75, y: 85, width: 185, height: 65}, //100 FIRST STREET HOUSES
    {x: 285, y: 85, width: 185, height: 65}, //200 FIRST STREET HOUSES
    {x: 75, y: 180, width: 185, height: 90}, //100 SECOND STREET HOUSES
    {x: 285, y: 180, width: 185, height: 90}, //200 SECOND STREET HOUSES
    {x: 75, y: 315, width: 185, height: 107}, //100 THIRD STREET HOUSES
    {x: 285, y: 315, width: 185, height: 90}, //200 THIRD STREET HOUSES
    {x: 75, y: 450, width: 185, height: 107}, //100 FORTH STREET HOUSES
    {x: 285, y: 450, width: 185, height: 90}, //200 FORTH STREET HOUSES
    {x: 75, y: 590, width: 185, height: 107}, //100 FIFTH STREET HOUSES
    {x: 285, y: 590, width: 185, height: 90}, //200 FIFTH STREET HOUSES
    {x: 75, y: 725, width: 185, height: 107}, //100 SIXTH STREET HOUSES
    {x: 285, y: 725, width: 185, height: 90}, //200 SIXTH STREET HOUSES
    {x: 75, y: 860, width: 185, height: 107}, //100 SEVENTH STREET HOUSES
    {x: 285, y: 860, width: 185, height: 90}, //200 SEVENTH STREET HOUSES
    {x: 489, y: 860, width: 185, height: 90}, //300 SEVENTH STREET HOUSES
    {x: 75, y: 995, width: 185, height: 107}, //100 EIGTH STREET HOUSES
    {x: 285, y: 995, width: 185, height: 90}, //200 EIGTH STREET HOUSES
    {x: 489, y: 995, width: 185, height: 90}, //300 EIGTH STREET HOUSES
    {x: 700, y: 995, width: 185, height: 90}, //400 EIGTH STREET HOUSES
    {x: 75, y: 1130, width: 185, height: 107}, //100 NINTH STREET HOUSES
    {x: 285, y: 1130, width: 185, height: 90}, //200 NINTH STREET HOUSES
    {x: 489, y: 1130, width: 185, height: 90}, //300 NINTH STREET HOUSES
    {x: 700, y: 1130, width: 185, height: 90}, //400 NINTH STREET HOUSES

    {x: 815, y: 890, width: 65, height: 65}, // SHOP
    {x: 700, y: 915, width: 52, height: 40},


    {x: 0, y: 350, width: 30, height: 925}, //BOTTOM LEFT OBSTACLES TREES & WATER
    {x: 0, y: 0, width: 30, height: 320}, //TOP LEFT TREES

    {x: 515, y: 0, width: 45, height: 560}, //NORTHERN RIVER
    {x: 515, y: 585, width: 45, height: 240}, //NORTHERN RIVER BOTTOM
    {x: 515, y: 780, width: 435, height: 45}, //NORTHERN RIVER BOTTOM HORIZONTAL
    {x: 1325, y: 1235, width: 375, height: 25}, //SOUTHERN FARM TOP TREELINE
    {x: 1325, y: 1185, width: 355, height: 15}, //SOUTHERN FARM TOP TREELINE 2
    {x: 1680, y: 1240, width: 15, height: 95}, //SOUTHERN FARM TOP ROCKLINE
    {x: 1680, y: 1370, width: 15, height: 60}, //SOUTHERN FARM BOTTOM ROCKLINE
    {x: 1520, y: 1240, width: 70, height: 95}, //SOUTHERN FARM HOUSE
    {x: 1490, y: 1320, width: 80, height: 15}, //SOUTHERN FARM HOUSE FENCE


    {x: 1840, y: 0, width: 10, height: 945}, //EAST ENTRANCE PLATEAU RIGHT CLIFF
    {x: 2360, y: 675, width: 10, height: 270}, //EAST ENTRANCE TREELINE
    {x: 2223, y: 927, width: 185, height: 17}, //EAST ENTRANCE RIGHT FENCE
    {x: 1850, y: 927, width: 325, height: 17}, //EAST ENTRANCE LEFT FENCE
    {x: 1850, y: 705, width: 375, height: 15}, //EAST ENTRANCE BOTTOM LEFT CLIFF
    {x: 2270, y: 705, width: 130, height: 17},
    {x: 2345, y: 245, width: 15, height: 460}, //EAST ENTRANCE PLATEAU EAST CLIFF
    {x: 2360, y: 0, width: 10, height: 250}, //EAST ENTRANCE PLATEAU EAST TREELINE
    {x: 1840, y: 65, width: 570, height: 15},
    {x: 1920, y: 320, width: 320, height: 240}, //EAST ENTRANCE PLATEAU FOREST

    {x: 1550, y: 320, width: 300, height: 17}, //NORTHERN FARM HORIZONTAL RIGHT FENCE
    {x: 1415, y: 320, width: 105, height: 17},//NORTHERN FARM HORIZONTAL LEFT FENCE
    {x: 1415, y: 0, width: 10, height: 325}, //NORTHERN FARM VERTICAL FENCE
    {x: 1415, y: 10, width: 425, height: 21}, //NORTHERN FARM TREELINE
    {x: 1415, y: 10, width: 100, height: 95}, //NORTHERN FARM HOUSE
    {x: 1485, y: 30, width: 51, height: 42}, //NORTHERN FARM HOUSE TREE


    {x: 500, y: 0, width: 135, height: 20},
    {x: 635, y: 0, width: 10, height: 75},
    {x: 636, y: 64, width: 780, height: 15}, //NORTHERN CLIFF

    {x: 612, y: 285, width: 435, height: 25}, //NORTHERN TOP TREELINE
    {x: 660, y: 350, width: 395, height: 33}, //NORTHERN BOTTOM TREELINE
    {x: 515, y: 585, width: 160, height: 33}, //NORTHERN RIVER TOP TREELINE
    {x: 515, y: 525, width: 115, height: 33}, //NORTHERN RIVER BOTTOM TREELINE
    {x: 1040, y: 350, width: 17, height: 160}, //ROCKLINE

    {x: 612, y: 285, width: 20, height: 270}, //NORTHERN RIVER WEST TREELINE
    {x: 660, y: 457, width: 25, height: 160}, //NORTHERN RIVER EAST TREELINE

    //LAKE

    {x: 945, y: 780, width: 10, height: 240}, //WEST BORDER SOUTH OF NORTHERN RIVER
    {x: 1155, y: 1175, width: 175, height: 30}, //SOUTH BORDER WEST OF SOUTHERN BRIDGE
    {x: 1535, y: 865, width: 10, height: 130}, //EAST BORDER
    {x: 1525, y: 985, width: 10, height: 80}, //EAST BORDER
    {x: 1640, y: 615, width: 10, height: 150}, //EAST BORDER
    {x: 1365, y: 520, width: 135, height: 17}, //NORTH BORDER
    {x: 1180, y: 560, width: 115, height: 17}, //NORTH BORDER
    {x: 1000, y: 505, width: 105, height: 17}, //NORTH BORDER
    {x: 1130, y: 550, width: 50, height: 17}, //NORTH BORDER
    {x: 900, y: 720, width: 10, height: 100}, //WEST BORDER

    {x: 1545, y: 855, width: 10, height: 10},
    {x: 1555, y: 845, width: 10, height: 10},
    {x: 1565, y: 835, width: 10, height: 10},
    {x: 1575, y: 825, width: 10, height: 10},
    {x: 1585, y: 815, width: 10, height: 10},
    {x: 1595, y: 805, width: 10, height: 10},
    {x: 1605, y: 795, width: 10, height: 10},
    {x: 1615, y: 785, width: 10, height: 10},
    {x: 1625, y: 775, width: 10, height: 10},
    {x: 1635, y: 765, width: 10, height: 10},

    {x: 1400, y: 1175, width: 10, height: 10},
    {x: 1410, y: 1165, width: 10, height: 10},
    {x: 1420, y: 1155, width: 10, height: 10},
    {x: 1430, y: 1145, width: 10, height: 10},
    {x: 1440, y: 1135, width: 10, height: 10},
    {x: 1450, y: 1125, width: 10, height: 10},
    {x: 1460, y: 1115, width: 10, height: 10},
    {x: 1470, y: 1105, width: 10, height: 10},
    {x: 1480, y: 1095, width: 10, height: 10},
    {x: 1490, y: 1085, width: 10, height: 10},
    {x: 1500, y: 1075, width: 10, height: 10},
    {x: 1510, y: 1065, width: 10, height: 10},
    {x: 1520, y: 1055, width: 10, height: 10},

    {x: 1635, y: 605, width: 10, height: 10},
    {x: 1625, y: 595, width: 10, height: 10},
    {x: 1615, y: 585, width: 10, height: 10},
    {x: 1595, y: 575, width: 20, height: 10},
    {x: 1575, y: 565, width: 20, height: 10},
    {x: 1555, y: 555, width: 20, height: 10},
    {x: 1535, y: 545, width: 20, height: 10},
    {x: 1515, y: 535, width: 20, height: 10},
    {x: 1495, y: 525, width: 20, height: 10},

    {x: 1345, y: 525, width: 20, height: 10},
    {x: 1325, y: 535, width: 20, height: 10},
    {x: 1305, y: 545, width: 20, height: 10},
    {x: 1285, y: 555, width: 20, height: 10},

    {x: 1100, y: 520, width: 20, height: 10},
    {x: 1110, y: 530, width: 20, height: 10},
    {x: 1120, y: 540, width: 20, height: 10},

    {x: 985, y: 520, width: 20, height: 10},
    {x: 975, y: 530, width: 20, height: 10},
    {x: 965, y: 540, width: 20, height: 10},
    {x: 945, y: 550, width: 20, height: 10},
    {x: 925, y: 560, width: 20, height: 10},
    {x: 905, y: 570, width: 20, height: 10},
    {x: 885, y: 580, width: 20, height: 10},
    {x: 870, y: 590, width: 20, height: 10},
    {x: 860, y: 600, width: 20, height: 10},
    {x: 840, y: 610, width: 40, height: 10},

    {x: 840, y: 615, width: 10, height: 65},
    {x: 850, y: 665, width: 10, height: 35},
    {x: 860, y: 685, width: 10, height: 35},
    {x: 860, y: 720, width: 40, height: 10},

    {x: 955, y: 1020, width: 10, height: 20},
    {x: 965, y: 1040, width: 10, height: 20},
    {x: 975, y: 1060, width: 10, height: 10},
    {x: 985, y: 1070, width: 10, height: 10},
    {x: 995, y: 1080, width: 10, height: 20},
    {x: 1005, y: 1090, width: 10, height: 20},
    {x: 1015, y: 1100, width: 10, height: 10},
    {x: 1025, y: 1110, width: 10, height: 10},
    {x: 1035, y: 1120, width: 30, height: 10},
    {x: 1065, y: 1130, width: 30, height: 10},
    {x: 1095, y: 1140, width: 20, height: 10},
    {x: 1105, y: 1150, width: 20, height: 10},
    {x: 1125, y: 1160, width: 10, height: 10},
    {x: 1135, y: 1170, width: 10, height: 10},
    {x: 1145, y: 1180, width: 10, height: 10},
  ]
};


export const ENTRANCES: { [key: string]: any } = {
  STARTING_MAP_SOUTH: { x: 15, y: 400, width: 30, height: 5 },
  STARTING_MAP_NORTH: { x: 15, y: 45, width: 30, height: 5 },
  STARTING_MAP_WEST: { x: 0, y: 150, width: 5, height: 75 },
  STARTING_MAP_EAST: { x: 345, y: 335, width: 5, height: 40 },
  STARTING_MAP_SHOP: { x: 190, y: 175, width: 20, height: 5 },
};