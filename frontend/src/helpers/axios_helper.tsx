import axios from 'axios';

export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token');
};

export const setAuthHeader = (token: any) => {
    window.localStorage.setItem('auth_token', token);
};

axios.defaults.headers.post['Content-Type'] = 'application/json';

export const request = (method: any, url: any, data: any) => {

    let headers = {};
    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = {'Authorization': `Bearer ${getAuthToken()}`};
    }

    return axios({
        method: method,
        url: url,
        headers: headers,
        data: data});
};

export const isAuthenticated = () => {
    const token = getAuthToken();
    return token !== null; // Return true if token exists, false otherwise
  };
