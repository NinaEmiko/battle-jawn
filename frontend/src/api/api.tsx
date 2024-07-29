import axios from 'axios';
import { ENDPOINTS } from '../helpers/constants';

export const buyItems = async (id: number, item: string) => {
    try {
        const response = await axios.post(`${ENDPOINTS.BUY}`, {
            heroId: id,
            item: item,
            quantity: 1
        });
        return response.data;
    } catch (error) {
      console.error('Error buying items:', error);
      throw error;
    }
};

export const sellItems = async (id: number, item: string) => {
    try {
        const response = await axios.post(`${ENDPOINTS.SELL}`, {
            heroId: id,
            item: item,
            quantity: 1
         });
        return response.data;
    } catch (error) {
        console.error('Error selling items:', error);
        throw error;
    }
};

export const fetchBattleSession = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_BATTLE_SESSION}${id}` )
        return response.data;
    } catch (error) {
        console.error('Error retrieving battle session data:', error);
        throw error;
      }
}

export const fetchHero = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_HERO}${id}` )
        return response.data;
    } catch (error) {
        console.error('Error retrieving hero data:', error);
        throw error;
      }
}

export const fetchInventory = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_INVENTORY}${id}`)
        return response.data;
    } catch (error) {
        console.error('Error retrieving inventory data:', error);
        throw error;
    }
}

export const fetchLeaderboard = async () => {
    try {
        const response = await axios.get(`${ENDPOINTS.HIGH_SCORE}`)
        return response.data;
    } catch (error) {
        console.error('Error fetching Hero data: ', error)
    }
}

export const fetchLoot = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_LOOT}${id}`)
        return response.data;
    } catch (error) {
        console.error('Error fetching loot data: ', error)
    }
}

export const fetchEmptySlots = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_EMPTY_SLOTS}${id}`)
        return response.data;
    } catch (error) {
        console.error('Error fetching slot data: ', error)
    }
}

export const selectLootCall = async (id: number, selectedItems: string) => {
    try {
        axios.post(`${ENDPOINTS.SELECT_LOOT}${id}`, selectedItems, {
            headers: {
            'Content-Type': 'application/json'
            }})
    } catch (error) {
        console.error('Error selecting loot: ', error)
    }
}

export const fetchPlayerTips = async () => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_PLAYER_TIPS}`)
        return response.data;
    } catch (error) {
        console.error('Error fetching player tips: ', error)
    }
}

export const fetchHeroes = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_HEROES}${id}`)
        return response.data;
    } catch (error) {
        console.error('Error fetching Hero data: ', error)
    }
}

export const restHero = async (id: any) => {
    try {
        await axios.post(`${ENDPOINTS.REST_HERO}${id}`)
    } catch (error) {
        console.error('Error while resting hero: ', error)
    }
};

export const deleteHero = async (id: any) => {
    try {
        await axios.delete(`${ENDPOINTS.DELETE_HERO}${id}`)
    } catch (error) {
        console.error('Error deleting hero: ', error)
    }
}

export const usePotion = async (id: number, index: number) => {
    try {
        const response = await axios.post(`${ENDPOINTS.USE_POTION}${id}`,{
            slot: index
        })
        return response.data;
    } catch (error) {
        console.error('Error occured while using potion: ', error)
    }
}

export const useWater = async (id: number, index: number) => {
    try {
        const response = await axios.post(`${ENDPOINTS.USE_WATER}${id}`,{
            slot: index
        })
        return response.data;
    } catch (error) {
        console.error('Error occured while using water: ', error)
    }
}

export const heroMove = async (move: string, battleSessionId: number) => {
    try {
        const response = await axios.post(`${ENDPOINTS.HERO_MOVE}`, {
            move: move,
            battleSessionId: battleSessionId
            })
        return response.data;
    } catch (error) {
        console.error('Error occurred while trying to use: ' + move + " ", error)
    }
}

export const enemyMove = async (battleSessionId: number) => {
    try {
        const response = await axios.post(`${ENDPOINTS.ENEMY_MOVE}`, {
            battleSessionId: battleSessionId
            })
        return response.data;
    } catch (error) {
        console.error('Error fetching enemy move data:', error)
    }
}

export const createNewBattleSession = async (heroId: number) => {
    try {
        const response = await axios.post(`${ENDPOINTS.CREATE_NEW_BATTLE_SESSION}`, {
            heroId: heroId
            })
        return response.data;
    } catch (error) {
        console.error('Error fetching battle session data:', error)
    }
}

export const fetchEnemy = async (id: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_ENEMY}${id}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching enemy data:', error)
    }
}

export const fetchBattleHistoryMessage = async (battleSessionId: number) => {
    try {
        const response = await axios.get(`${ENDPOINTS.FETCH_BATTLE_HISTORY_MESSAGE}${battleSessionId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching battle history data:', error)
    }
}

export const endBattleSession = async (battleSessionId: number, battleResult: string) => {
    try {
        const response = await axios.post(`${ENDPOINTS.END_BATTLE_SESSION}`, {
            battleSessionId: battleSessionId, 
            battleResult: battleResult 
        });
        return response.data;
    } catch (error) {
        console.error('Error processing end of battle:', error)
    }
}

export const activateTalent = async (id: number, talent: string) => {
    try {
        const response = await axios.post(`${ENDPOINTS.ACTIVATE_TALENT}`, {
            heroId: id, 
            talent: talent 
        });
        return response.data;
    } catch (error) {
        console.error('Error activating talent:', error)
    }
}

export const resetTalents = async (id: number) => {
    try {
        const response = await axios.post(`${ENDPOINTS.RESET_TALENTS}`, {
            heroId: id
        });
        return response.data;
    } catch (error) {
        console.error('Error resetting talents:', error)
    }
}

export const newHero = async (id: number, heroName: string, role: string) => {
    try {
        const response = await axios.post(`${ENDPOINTS.NEW_HERO}`, {
            userAccountId: id,
            heroName: heroName,
            role: role
        });
        return response.data;
    } catch (error) {
        console.error('Error creating hero:', error);
    }
}