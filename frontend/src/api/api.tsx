import axios from 'axios';

const baseURL = import.meta.env.VITE_REACT_APP_URL;

export const buyItems = async (id: number, item: string) => {
    try {
        const response = await axios.post(`${baseURL}/api/store/buy`, {
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
        const response = await axios.post(`${baseURL}/api/store/sell`, {
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

export const fetchHero = async (id: number) => {
    try {
        const response = await axios.get(`${baseURL}/api/hero/${id}` )
        return response.data;
    } catch (error) {
        console.error('Error retrieving hero data:', error);
        throw error;
      }
}

export const fetchInventory = async (id: number) => {
    try {
        const response = await axios.get(`${baseURL}/api/inventory/${id}` )
        return response.data;
    } catch (error) {
        console.error('Error retrieving inventory data:', error);
        throw error;
    }
}