import axios from '../utils/axios'
export function getUserInfo() {
    return axios.get('/user/info');
}

export function EditUserInfo(params) {
    return axios.post('/user/info', params);
}

export function EditAvatar(params) {
    return axios.post('/user/update', params);
}

export function login(params) {
    return axios.post('/user/login', params);
}

export function logout() {
    return axios.post('/user/logout');
}

export function register(params) {
    return axios.post('/user/register', params);
}

export function getVisitorList() {
    return axios.get('/user/vlist');
}
